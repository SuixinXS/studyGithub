package com.jkoss.biz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jkoss.dao.GoodinfoMapper;
import com.jkoss.pojo.Goodinfo;

@Component
public class GoodImplBiz implements IGoodBiz {
@Autowired
GoodinfoMapper goodDao;

@Override
public Goodinfo selectByrid(Integer rid) {
	// TODO Auto-generated method stub
	return goodDao.selectByrid(rid);
}

@Override
public int updateByPrimaryKeySelective(Goodinfo record) {
	// TODO Auto-generated method stub
	return goodDao.updateByPrimaryKeySelective(record);
}

}
