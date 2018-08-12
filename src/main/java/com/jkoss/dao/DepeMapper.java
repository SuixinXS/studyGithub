package com.jkoss.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.jkoss.pojo.Depe;

public interface DepeMapper {
    int deleteByPrimaryKey(Integer depe_id);

    int insert(Depe record);

    int insertSelective(Depe record);

    Depe selectByPrimaryKey(Integer depe_id);

    int updateByPrimaryKeySelective(Depe record);

    int updateByPrimaryKey(Depe record);

    @Select("select * from depe")
	List<Depe> findAll();
}