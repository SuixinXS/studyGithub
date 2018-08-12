package com.jkoss.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.jkoss.pojo.Cabin;
import com.jkoss.tool.Page;

public interface CabinMapper {


    int insert(Cabin record);

    int insertSelective(Cabin record);

    Cabin selectByPrimaryKey(Integer cab_id);

    int updateByPrimaryKeySelective(Cabin record);

    int updateByPrimaryKey(Cabin record);
    
    @Select("select * from cabin")
    List<Cabin>findAllCabin(Page<Cabin>page);
    
    @Select("select * from cabin where area_id=#{params.aid}")
    List<Cabin>findAllCabinByAid(Page<Cabin>page);
    
    int deleteByPrimaryKey(Integer cab_id);
    @Select("select * from cabin where area_id=#{area_id}")
    List<Cabin>lsCabinByAid(Integer area_id);
    
    @Select("select * from cabin")
    List<Cabin>lsAll();
    
    List<Cabin>selAllCabADep(Page<Cabin>page);
    
}