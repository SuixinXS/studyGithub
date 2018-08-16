package com.jkoss.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import com.jkoss.pojo.Employ;
import com.jkoss.pojo.vo.EmployVo;
import com.jkoss.tool.Page;

public interface EmployMapper {
    int deleteByPrimaryKey(Integer emp_id);

    int insert(Employ record);

    int insertSelective(Employ record);

    Employ selectByPrimaryKey(Integer emp_id);

    int updateByPrimaryKeySelective(Employ record);

    int updateByPrimaryKey(Employ record);
    List<EmployVo> findAllByDp_id(Page<EmployVo> page);

	List<EmployVo> findAll(Page<EmployVo> page);

	List<EmployVo> selectByLkName(Page<EmployVo> page);

	@Select("select e.*, d.depe_name  from depe d,employ e" + 
			"   where d.depe_id=e.depe_id" +
			"   and emp_login=#{lgnName} and emp_pass=#{pwdword}")
	@ResultMap("BaseResultMap2")
	EmployVo selectByLgPw(@Param("lgnName")String lgnName, @Param("pwdword") String pwdword);
  
    //查全部叉车手，叉车职位id为1
    @Select("select * from employ where job_id=1")
	List<Employ> findAllCCS();
    
    @Select("Select * From employ where emp_login=#{emp_login}")
	Employ selectByLg(@Param("emp_login") String emp_login);
}