package com.jkoss.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.jkoss.pojo.Areainfo;

public interface AreainfoMapper {
  
	int insert(Areainfo record);
	
    int updateByPrimaryKey(Areainfo record);

 /*   使用到的*/
    int insertSelective(Areainfo record);
    Areainfo selectByPrimaryKey(Integer area_id);
    int deleteByPrimaryKey(Integer area_id);
	int updateByPrimaryKeySelective(Areainfo record);
	
	@Select("select * from areainfo")
	List<Areainfo> lsArea();
	
	@Select("select * from areainfo where area_name=#{area_name}")
	Areainfo selAreaByName(String area_name);

}