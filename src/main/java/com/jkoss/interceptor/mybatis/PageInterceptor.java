package com.jkoss.interceptor.mybatis;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.scripting.defaults.DefaultParameterHandler;

import com.jkoss.tool.Page;
import com.jkoss.tool.ReflectUtil;

 
@Intercepts({ @Signature(method = "prepare", type = StatementHandler.class, args = { Connection.class ,Integer.class}) })
public class PageInterceptor implements Interceptor {

	private String databaseType; 

 
	public Object intercept(Invocation invocation) throws Throwable {
	
		 RoutingStatementHandler handler = (RoutingStatementHandler) invocation
				.getTarget();
		 StatementHandler delegate = (StatementHandler) ReflectUtil
				.getFieldValue(handler, "delegate");
		 BoundSql boundSql = delegate.getBoundSql();
		 Object obj = boundSql.getParameterObject();
		 if (obj instanceof Page<?>) {
			Page<?> page = (Page<?>) obj;
		 	MappedStatement mappedStatement = (MappedStatement) ReflectUtil
					.getFieldValue(delegate, "mappedStatement");
			 Connection connection = (Connection) invocation.getArgs()[0];
			 String sql = boundSql.getSql();
			 this.setTotalRecord(page, mappedStatement, connection);
			 String pageSql = this.getPageSql(page, sql);
			 ReflectUtil.setFieldValue(boundSql, "sql", pageSql);
		}
		return invocation.proceed();
	}

	 
	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

 
	public void setProperties(Properties properties) {
		this.databaseType = properties.getProperty("databaseType");
	}

 
	private String getPageSql(Page<?> page, String sql) {
		StringBuffer sqlBuffer = new StringBuffer(sql);
		if ("mysql".equalsIgnoreCase(databaseType)) {
			return getMysqlPageSql(page, sqlBuffer);
		} else if ("oracle".equalsIgnoreCase(databaseType)) {
			return getOraclePageSql(page, sqlBuffer);
		}
		return sqlBuffer.toString();
	}

	 
	private String getMysqlPageSql(Page<?> page, StringBuffer sqlBuffer) {
		int offset = (page.getPageNo() - 1) * page.getPageSize();
		sqlBuffer.append(" limit ").append(offset).append(",")
				.append(page.getPageSize());
		return sqlBuffer.toString();
	}

 
	private String getOraclePageSql(Page<?> page, StringBuffer sqlBuffer) {
	 	int offset = (page.getPageNo() - 1) * page.getPageSize() + 1;
		sqlBuffer.insert(0, "select u.*, rownum r from (")
				.append(") u where rownum < ")
				.append(offset + page.getPageSize());
		sqlBuffer.insert(0, "select * from (").append(") where r >= ")
				.append(offset);
		 
		return sqlBuffer.toString();
	}
 
	private void setTotalRecord(Page<?> page, MappedStatement mappedStatement,
			Connection connection) {
		 BoundSql boundSql = mappedStatement.getBoundSql(page);
		 String sql = boundSql.getSql();
		 String countSql = this.getCountSql(sql);
		 List<ParameterMapping> parameterMappings = boundSql
				.getParameterMappings();
		 BoundSql countBoundSql = new BoundSql(
				mappedStatement.getConfiguration(), countSql,
				parameterMappings, page);
		 ParameterHandler parameterHandler = new DefaultParameterHandler(
				mappedStatement, page, countBoundSql);
		 PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = connection.prepareStatement(countSql);
		 	parameterHandler.setParameters(pstmt);
		 	rs = pstmt.executeQuery();
			if (rs.next()) {
				int totalRecord = rs.getInt(1);
			 	page.setTotalRecord(totalRecord);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	private String getCountSql(String sql) {

		return "select count(1) from (" + sql + ") xs";
	}

	

}