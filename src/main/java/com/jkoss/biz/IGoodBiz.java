package com.jkoss.biz;

import com.jkoss.pojo.Goodinfo;

public interface IGoodBiz {
	 Goodinfo selectByrid(Integer rid) ;
	 int updateByPrimaryKeySelective(Goodinfo record);
}
