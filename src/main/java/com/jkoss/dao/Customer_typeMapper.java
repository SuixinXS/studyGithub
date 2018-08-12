package com.jkoss.dao;

import org.apache.ibatis.annotations.Select;

import com.jkoss.pojo.Customer_type;

public interface Customer_typeMapper {
    int deleteByPrimaryKey(Integer ctmtype_id);

    int insert(Customer_type record);

    int insertSelective(Customer_type record);

    Customer_type selectByPrimaryKey(Integer ctmtype_id);

    int updateByPrimaryKeySelective(Customer_type record);

    int updateByPrimaryKey(Customer_type record);
    
    int updateByCtm_id(Customer_type c);
    
  /*  @Select("select * from customer_type where ctm_id=#{ctm_id}")
    Customer_type selectByPrimaryKey1(Integer ctm_id);*/
}