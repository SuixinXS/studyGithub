package com.jkoss.dao;


import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import com.jkoss.pojo.Execute;

public interface ExecuteMapper {
    int deleteByPrimaryKey(Integer exe_id);

    int insert(Execute record);

    int insertSelective(Execute record);

    Execute selectByPrimaryKey(Integer exe_id);

    int updateByPrimaryKeySelective(Execute record);

    int updateByPrimaryKey(Execute record);
    
    @Insert("insert into execute2employ (emp_id,exe_id) values (#{empid},#{exid})")
    int insertExe2Emp(@Param("empid")int empid,@Param("exid")int exid);
    
    @Update("update execute2employ set emp_id=#{empid} where exe_id=#{exid}")
    int upExe2Emp(@Param("empid")int empid,@Param("exid")int exid);
    
    @Delete("delete from execute2employ where exe_id=#{exe_id}")
    int delExe2Emp(@Param("exe_id")int exe_id);
 
}