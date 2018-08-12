package com.jkoss.action;

import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jkoss.biz.ColdRegImplBiz;
import com.jkoss.biz.FreezerImpleBiz;
import com.jkoss.biz.GoodImplBiz;
import com.jkoss.pojo.Coolregister;
import com.jkoss.pojo.Execute;
import com.jkoss.pojo.Goodinfo;
import com.jkoss.pojo.vo.CoolregisterVo;
import com.jkoss.pojo.vo.CoolregisterVoCtm;
import com.jkoss.pojo.vo.EmployVo;
import com.jkoss.tool.Page;
import com.jkoss.util.Constant;

@Controller
@RequestMapping("service")
public class ColdAction {
	@Autowired
	ColdRegImplBiz coldBiz;
	@Autowired
	GoodImplBiz goodBiz;
	@Autowired
	FreezerImpleBiz freezeBiz;

	/* 订单 ················································· */
//订单列表
	@RequestMapping("lsColdReg")
	public String lsColdReg(HttpServletRequest req, Page<CoolregisterVoCtm> page, String msg, Integer rid) {
		if (page == null) {
			page = new Page<CoolregisterVoCtm>();
			page.setPageNo(1);
		}

		page.setPageSize(10);
		page.getParams().put("regist_id", rid);
		req.setAttribute("lsColdReg", coldBiz.lsCold(page));
		req.setAttribute("remsg", msg);
		return "/service/listCoolReg.jsp";

	}

//新增订单：包括存放的物品
	@RequestMapping("addColdReg")
	public String addColdReg(HttpServletRequest req, Coolregister cReg, Goodinfo good) {

		return lsColdReg(req, null, coldBiz.addColdReg(cReg, good), null);

	}

//将订单作废
	@ResponseBody
	@RequestMapping("zfCoolReg")
	public String zfCoolReg(Integer rid) {
		Coolregister record = new Coolregister();
		record.setRegist_id(rid);
		record.setRegist_state(4);
		return coldBiz.zf(record);

	}

//编辑，按rid加载该订单详情
	@ResponseBody
	@RequestMapping("upCoolReg")
	public CoolregisterVoCtm upCoolReg(Integer rid) {

		return coldBiz.selColdByRid(rid);

	}

	@ResponseBody
	@RequestMapping("selgoodbyrid")
	public Goodinfo selgoodbyrid(Integer rid) {
		return goodBiz.selectByrid(rid);

	}

//编辑订单

	@RequestMapping("upCoolReg1")
	public String upCoolReg1(HttpServletRequest req, Coolregister cReg, Goodinfo good) {
		coldBiz.updateByPrimaryKeySelective(cReg);
		goodBiz.updateByPrimaryKeySelective(good);
		return lsColdReg(req, null, "编辑完成", null);

	}

	/* 订单 ················································· */

	/* 入库计划 ················································· */
//入库计划列表
	@RequestMapping("lsEnCold")
	public String lsEnCold(HttpServletRequest req, Page<CoolregisterVo> page, String bTime, String op) {
		// 表示查出为入库计划
		page.getParams().put("coldopera", 0);
		if (bTime == "" || bTime == null || bTime.isEmpty() || bTime == "null") {
			bTime = null;
		} else {
			String time = bTime + " 00:00:00";
			String time1 = bTime + " 23:59:59";
			page.getParams().put("time", time);
			page.getParams().put("time1", time1);
		}
		page.getParams().put("bTime", bTime);
		req.setAttribute("arealist", freezeBiz.lsArea());
		req.setAttribute("cablist", freezeBiz.lsAll());
		req.setAttribute("delist", freezeBiz.lsDepotCid2());
		req.setAttribute("lsEnCold", coldBiz.lsEnCold(page));

		req.setAttribute("ccslist", coldBiz.findAllCCS());
		return "/service/listEnCoolReg.jsp";

	}

//新增入库计划

	@RequestMapping("showReg")
	public String showReg(HttpServletRequest req, CoolregisterVo c) {

		req.setAttribute("lsReg", coldBiz.showReg());
		return "/service/showReg.jsp";
	}
	
	@RequestMapping("addEnCold")
	public String addEnCold(HttpServletRequest req, CoolregisterVo c) {

		coldBiz.addEnCold(c);
		return lsEnCold(req, new Page<CoolregisterVo>(), "null", "null");
	}
//验证订单ID
	@ResponseBody
	@RequestMapping("checkRegId")
	public String checkEnByRegId(HttpServletRequest req,  Integer rid) {
		
		return null;

		
	}
	
//编辑入库计划
	@ResponseBody
	@RequestMapping("upEnCold1")
	public CoolregisterVo upEnCold1(Integer eid) {
		return coldBiz.enCold1(eid);
	}

	@RequestMapping("upEnCold2")
	public String upEnCold2(HttpServletRequest req, CoolregisterVo c) {
		coldBiz.upEnCold2(c);
		return lsEnCold(req, new Page<CoolregisterVo>(), "null", "null");

	}

//删除入库计划
	@RequestMapping("delEnCold")
	public String delEnCold(HttpServletRequest req, Integer eid) {
		coldBiz.delEnCold(eid);
		return lsEnCold(req, new Page<CoolregisterVo>(), "null", "null");

	}

	/* 出库 */
	// 出库计划列表
	@RequestMapping("lsOutCold")
	public String lsOutCold(HttpServletRequest req, Page<CoolregisterVo> page, String bTime, String op) {
		page.getParams().put("coldopera", 1);

		String time = bTime + " 00:00:00";
		String time1 = bTime + " 23:59:59";
		page.getParams().put("time", time);
		page.getParams().put("time1", time1);
		page.getParams().put("bTime", bTime);
		page.getParams().put("op", op);

		req.setAttribute("arealist", freezeBiz.lsArea());
		req.setAttribute("cablist", freezeBiz.lsAll());
		req.setAttribute("delist", freezeBiz.lsDepotCid2());
		req.setAttribute("lsEnCold", coldBiz.lsEnCold(page));
		req.setAttribute("ccslist", coldBiz.findAllCCS());
		return "/service/listOutCoolReg.jsp";

	}

	// 增加出库计划
	// 验证订单和计划
	@ResponseBody
	@RequestMapping("checkByRid")
	public String checkByRid(Integer rid) {
		return coldBiz.checkOutByRid(rid);

	}

	@RequestMapping("addOutColdReg")
	public String addOutColdReg(HttpServletRequest req, Integer regist_id, Integer emp_id) {
		coldBiz.addOutColdReg(regist_id, emp_id);
		return lsOutCold(req, new Page<CoolregisterVo>(),"null", "null");
	}

	@RequestMapping("delOutColdReg")
	public String delOutColdReg(HttpServletRequest req, Integer exe_id) {
		coldBiz.delOutColdReg(exe_id);
		return lsOutCold(req, new Page<CoolregisterVo>(),"null", "null");

	}
	
/*调度*/
	
	@RequestMapping("lsDDCold")
	public String lsDDCold(HttpServletRequest req, Page<CoolregisterVo> page, String bTime) {
		EmployVo employVo=(EmployVo) req.getSession().getAttribute(Constant.SESSION_USER_KEY);
		page.getParams().put("emp_id", employVo.getEmp_id());		
		if (bTime == "" || bTime == null || bTime.isEmpty() || bTime == "null") {
			bTime = null;
		} else {
			String time = bTime + " 00:00:00";
			String time1 = bTime + " 23:59:59";
			page.getParams().put("time", time);
			page.getParams().put("time1", time1);
		}


		req.setAttribute("arealist", freezeBiz.lsArea());
		req.setAttribute("cablist", freezeBiz.lsAll());
		req.setAttribute("delist", freezeBiz.lsDepotCid2());
		
		req.setAttribute("lsDDCold", coldBiz.ddSelByEmpId(page));

		return "/service/listDDCoolReg.jsp";

	}
	
//完成入库
	
	@RequestMapping("finshDDCold")
	public String finshDDCold(HttpServletRequest req,Integer exe_id) {
		Execute e=new Execute();
		e.setExe_id(exe_id);
        e.setExe_end(new Date());
        coldBiz.finshEnReg(e);
		return lsDDCold(req,new Page<CoolregisterVo>(), "null");

	}
	
	@RequestMapping("finshDDOutCold")
	public String finshDDOutCold(HttpServletRequest req,Integer exe_id) {
		Execute e=new Execute();
		e.setExe_id(exe_id);
        e.setExe_end(new Date());
     
        
        coldBiz.finshOutReg(e);
        
		return lsDDCold(req,new Page<CoolregisterVo>(), "null");

	}
}
