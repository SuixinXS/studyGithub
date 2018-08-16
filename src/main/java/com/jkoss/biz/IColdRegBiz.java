package com.jkoss.biz;

import java.util.List;

import com.jkoss.pojo.Coolregister;
import com.jkoss.pojo.Depot;
import com.jkoss.pojo.Employ;
import com.jkoss.pojo.Execute;
import com.jkoss.pojo.Goodinfo;
import com.jkoss.pojo.vo.CoolregisterVo;
import com.jkoss.pojo.vo.CoolregisterVoCtm;
import com.jkoss.pojo.vo.CustomerEx;
import com.jkoss.tool.Page;

public interface IColdRegBiz {
//订单
	String addColdReg(Coolregister cReg,Goodinfo good);
	List<CoolregisterVoCtm>lsCold(Page<CoolregisterVoCtm> page,CoolregisterVoCtm coolReg);
	String  zf(Coolregister record);
	CoolregisterVoCtm selColdByRid(Integer rid);
	int updateByPrimaryKeySelective(Coolregister record);
	CustomerEx valiCtm(String ctm_login);
	
//入库	
	List<CoolregisterVo> lsEnCold(Page<CoolregisterVo> page,CoolregisterVo coolEn);
	//验证仓库地址
	String valiDepAddress(int cid,String dAddress);
	//叉车手
	List<Employ> findAllCCS();	
	
	List<CoolregisterVo>showReg();
	
	String addEnCold(CoolregisterVo c);
	CoolregisterVo enCold1(Integer rid);
    String upEnCold2(CoolregisterVo c);
    
    String delEnCold(Integer eid);
//出库
    String checkOutByRid(Integer rid);
    //显示木有作废，且已经完成入库的订单
    List<CoolregisterVo> showFinEnReg();
    
    String addOutColdReg(Integer regist_id,Integer emp_id);
    
    String delOutColdReg(Integer exe_id);
    
//调度
    List<CoolregisterVo>ddSelByEmpId(Page<CoolregisterVo> page,CoolregisterVo ddCool);
	String finshEnReg(Execute e);
	String finshOutReg(Execute e);
}
