package com.jkoss.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.jkoss.pojo.Power;

public interface PowerMapper {
    int deleteByPrimaryKey(Integer power_id);

    int insert(Power record);

    int insertSelective(Power record);

    Power selectByPrimaryKey(Integer power_id);

    int updateByPrimaryKeySelective(Power record);

    int updateByPrimaryKey(Power record);
    
    List<Power> selectByJob_id(Integer job_id);

    @Select("SELECT * from power where power.powerURL is not NULL")
	List<Power> findAll();
    
    @Select("select * from power where rfloor=#{prtNo}")
	Power selectByRfloor(@Param("prtNo") int prtNo);
}