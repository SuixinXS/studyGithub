package com.jkoss.biz;

import java.util.List;

import com.jkoss.pojo.Coolregister;
import com.jkoss.pojo.Employ;
import com.jkoss.pojo.Execute;
import com.jkoss.pojo.Goodinfo;
import com.jkoss.pojo.vo.CoolregisterVo;
import com.jkoss.pojo.vo.CoolregisterVoCtm;
import com.jkoss.tool.Page;

public interface IColdRegBiz {
//订单
	String addColdReg(Coolregister cReg,Goodinfo good);
	List<CoolregisterVoCtm>lsCold(Page<CoolregisterVoCtm> page);
	String  zf(Coolregister record);
	CoolregisterVoCtm selColdByRid(Integer rid);
	int updateByPrimaryKeySelective(Coolregister record);
	
//入库	
	List<CoolregisterVo> lsEnCold(Page<CoolregisterVo> page);
	//叉车手
	List<Employ> findAllCCS();	
	  List<CoolregisterVo>showReg();
	String addEnCold(CoolregisterVo c);
	CoolregisterVo enCold1(Integer rid);
    String upEnCold2(CoolregisterVo c);
    
    String delEnCold(Integer eid);
//出库
    String checkOutByRid(Integer rid);
    
    String addOutColdReg(Integer regist_id,Integer emp_id);
    
    String delOutColdReg(Integer exe_id);
    
//调度
    List<CoolregisterVo>ddSelByEmpId(Page<CoolregisterVo> page);
	String finshEnReg(Execute e);
	String finshOutReg(Execute e);
}
