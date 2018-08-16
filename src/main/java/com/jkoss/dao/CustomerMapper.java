package com.jkoss.dao;

import java.util.List;

import org.apache.ibatis.annotations.ResultMap;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.jkoss.pojo.Customer;
import com.jkoss.pojo.vo.CustomerEx;
import com.jkoss.tool.Page;

public interface CustomerMapper {
	int deleteByPrimaryKey(Integer ctm_id);

	int insert(Customer record);

	int insertSelective(Customer record);

	Customer selectByPrimaryKey(Integer ctm_id);

	int updateByPrimaryKeySelective(Customer record);

	int updateByPrimaryKey(Customer record);
		
	List<CustomerEx> lsCtmAll(Page<CustomerEx> page);

	@Update("update customer set ctm_state=0 where ctm_id=#{ctm_id}")
	int stopCstm(Integer ctm_id);
	
	@Select("select a.*,b.ctm_type,b.pay_type from customer as a left join customer_type as b on a.ctm_id=b.ctm_id where a.ctm_id=#{cid}")
	CustomerEx selCtmById(Integer cid);
	
	@Select("select * from customer where ctm_login=#{ctm_login}")
	CustomerEx selCtmByLogin(String ctm_login);
	
}