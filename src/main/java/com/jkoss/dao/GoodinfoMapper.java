package com.jkoss.dao;

import org.apache.ibatis.annotations.Select;

import com.jkoss.pojo.Goodinfo;

public interface GoodinfoMapper {
    int deleteByPrimaryKey(Integer good_id);

    int insert(Goodinfo record);

    int insertSelective(Goodinfo record);

    Goodinfo selectByPrimaryKey(Integer good_id);

    int updateByPrimaryKeySelective(Goodinfo record);

    int updateByPrimaryKey(Goodinfo record);
    @Select("select * from goodinfo where regist_id=#{rid}")
    Goodinfo selectByrid(Integer rid); 
}