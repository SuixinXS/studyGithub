package com.jkoss.action;

import java.util.Date;
import java.util.Random;
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
import com.jkoss.pojo.vo.CustomerEx;
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
	public String lsColdReg(HttpServletRequest req, Page<CoolregisterVoCtm> page, String msg,CoolregisterVoCtm coolReg) {
		if (page == null) {
			page = new Page<CoolregisterVoCtm>();
		}
		page.setPageSize(5);
		if (coolReg != null) {
			if (coolReg.getRegist_no() == null) {
				if (req.getSession().getAttribute(Constant.SESSION_SELENCOLDREG_KEY) != null) {
					coolReg = (CoolregisterVoCtm) req.getSession().getAttribute(Constant.SESSION_SELENCOLDREG_KEY);
				}
			} else {
				req.getSession().setAttribute(Constant.SESSION_SELENCOLDREG_KEY, coolReg);

			}
		}
		req.setAttribute("lsColdReg", coldBiz.lsCold(page, coolReg));
		req.setAttribute("remsg", msg);
		return "/service/listCoolReg.jsp";

	}

	// 显示没有作废的订单
	@RequestMapping("showAvailableReg")
	public String showAvailableReg(HttpServletRequest req, Page<CoolregisterVoCtm> page, String msg,
			CoolregisterVoCtm coolReg) {
		coolReg.setRegist_no("-1");
		coolReg.setRegist_state(0);

		return lsColdReg(req, page, msg, coolReg);
	}

    //新增订单：包括存放的物品
	@ResponseBody
	@RequestMapping("valiCtm")
	public CustomerEx valiCtm(String ctm_login) {
		return coldBiz.valiCtm(ctm_login);
	}

	@RequestMapping("addColdReg")
	public String addColdReg(HttpServletRequest req, Coolregister cReg, Goodinfo good, Page<CoolregisterVoCtm> page) {
		Random rand = new Random();
		cReg.setRegist_no("no" + rand.nextInt(100) + "A" + rand.nextInt(100));

		return lsColdReg(req, page, coldBiz.addColdReg(cReg, good), null);

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

    //编辑，按订单id加载该订单详情
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
	public String upCoolReg1(HttpServletRequest req, Page<CoolregisterVoCtm> page, Coolregister cReg, Goodinfo good) {
		/*
		 * SimpleDateFormat in = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		 * SimpleDateFormat out = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		 */

		coldBiz.updateByPrimaryKeySelective(cReg);
		goodBiz.updateByPrimaryKeySelective(good);
		return lsColdReg(req, page, "编辑完成", null);

	}

/* 订单 ················································· */

/* 入库计划 ················································· */
    //入库计划列表
	@RequestMapping("lsEnCold")
	public String lsEnCold(HttpServletRequest req, Page<CoolregisterVo> page, CoolregisterVo coolEn) {
		// 表示查出为入库计划
		page.getParams().put("coldopera", 0);
		page.setPageSize(5);
		if (coolEn != null) {
			if (coolEn.getJg_name() == null) {
				if (req.getSession().getAttribute(Constant.SESSION_SELENCOLDTIME_KEY) != null) {
					coolEn = (CoolregisterVo) req.getSession().getAttribute(Constant.SESSION_SELENCOLDTIME_KEY);
				}
			} else {
				req.getSession().setAttribute(Constant.SESSION_SELENCOLDTIME_KEY, coolEn);

			}
		}
		req.setAttribute("lsEnCold", coldBiz.lsEnCold(page, coolEn));

		req.setAttribute("arealist", freezeBiz.lsArea());
		req.setAttribute("cablist", freezeBiz.lsAll());
		req.setAttribute("delist", freezeBiz.lsDepotCid2());
		req.setAttribute("lsReg1", coldBiz.showReg());
		req.setAttribute("ccslist", coldBiz.findAllCCS());

		return "/service/listEnCoolReg.jsp";

	}

	// 显示没有完成的入库计划
	@RequestMapping("showNoFinsh")
	public String showNoFinsh(HttpServletRequest req, Page<CoolregisterVo> page) {
		page.getParams().put("noFinshEnReg", "Y");

		return lsEnCold(req, page, null);
	}


	//展示入库计划表
	@RequestMapping("showReg")
	public String showReg(HttpServletRequest req, CoolregisterVo c) {

		req.setAttribute("lsReg", coldBiz.showReg());
		return "/service/showReg.jsp";
	}
    //新增入库计划
	@RequestMapping("addEnCold")
	public String addEnCold(HttpServletRequest req, CoolregisterVo c, Page<CoolregisterVo> page) {

		coldBiz.addEnCold(c);
		return lsEnCold(req, page, null);
	}

//验证仓库位置
	@ResponseBody
	@RequestMapping("valiDepAddress")
	public String valiDepAddress(int cid, String dAddress) {
		return coldBiz.valiDepAddress(cid, dAddress);
	}

//编辑入库计划
	@ResponseBody
	@RequestMapping("upEnCold1")
	public CoolregisterVo upEnCold1(Integer eid) {
		return coldBiz.enCold1(eid);
	}

	@RequestMapping("upEnCold2")
	public String upEnCold2(HttpServletRequest req, CoolregisterVo c, Page<CoolregisterVo> page) {
		coldBiz.upEnCold2(c);
		return lsEnCold(req, page, null);

	}

//删除入库计划
	@RequestMapping("delEnCold")
	public String delEnCold(HttpServletRequest req, Integer eid, Page<CoolregisterVo> page) {
		coldBiz.delEnCold(eid);
		return lsEnCold(req, page, null);

	}
	
	
/* 出库 ·················································*/
	// 出库计划列表
	@RequestMapping("lsOutCold")
	public String lsOutCold(HttpServletRequest req, Page<CoolregisterVo> page, CoolregisterVo coolEn) {
		page.getParams().put("coldopera", 1);
		page.setPageSize(5);
		if (coolEn != null) {
			if (coolEn.getJg_name() == null) {
				if (req.getSession().getAttribute(Constant.SESSION_SELOUTCOLD_KEY) != null) {
					coolEn = (CoolregisterVo) req.getSession().getAttribute(Constant.SESSION_SELOUTCOLD_KEY);
				}
			} else {
				req.getSession().setAttribute(Constant.SESSION_SELOUTCOLD_KEY, coolEn);

			}
		}

		req.setAttribute("arealist", freezeBiz.lsArea());
		req.setAttribute("cablist", freezeBiz.lsAll());
		req.setAttribute("delist", freezeBiz.lsDepotCid2());
		req.setAttribute("lsFinEnReg", coldBiz.showFinEnReg());
		req.setAttribute("lsEnCold", coldBiz.lsEnCold(page, coolEn));
		req.setAttribute("ccslist", coldBiz.findAllCCS());

		return "/service/listOutCoolReg.jsp";

	}

	// 显示没有完成的出库计划
	@RequestMapping("showNoFinshOut")
	public String showNoFinshOut(HttpServletRequest req, Page<CoolregisterVo> page) {
		page.getParams().put("noFinshEnReg", "Y");
		return lsOutCold(req, page, null);
	}

	// 增加出库计划
	// 验证订单和计划
	@ResponseBody
	@RequestMapping("checkByRid")
	public String checkByRid(Integer rid) {
		return coldBiz.checkOutByRid(rid);

	}

	@RequestMapping("addOutColdReg")
	public String addOutColdReg(HttpServletRequest req, Integer regist_id, Integer emp_id, Page<CoolregisterVo> page) {
		coldBiz.addOutColdReg(regist_id, emp_id);
		return lsOutCold(req, page, null);
	}

	@RequestMapping("delOutColdReg")
	public String delOutColdReg(HttpServletRequest req, Integer exe_id, Page<CoolregisterVo> page) {
		coldBiz.delOutColdReg(exe_id);
		return lsOutCold(req, page, null);

	}
/* 出库 ·················································*/
	
	
/* 调度 ·················································*/
    
	@RequestMapping("lsDDCold")
	public String lsDDCold(HttpServletRequest req, Page<CoolregisterVo> page, CoolregisterVo ddCool) {
		EmployVo employVo = (EmployVo) req.getSession().getAttribute(Constant.SESSION_USER_KEY);
		page.getParams().put("depe_name", employVo.getDepe_name());
		page.getParams().put("emp_id", employVo.getEmp_id());
		page.setPageSize(5);

		if (ddCool != null) {
			if (ddCool.getJg_name() == null) {
				if (req.getSession().getAttribute(Constant.SESSION_SELDDCOLD_KEY) != null) {
					ddCool = (CoolregisterVo) req.getSession().getAttribute(Constant.SESSION_SELDDCOLD_KEY);
				}
			} else {
				req.getSession().setAttribute(Constant.SESSION_SELDDCOLD_KEY, ddCool);

			}
		}

		req.setAttribute("arealist", freezeBiz.lsArea());
		req.setAttribute("cablist", freezeBiz.lsAll());
		req.setAttribute("delist", freezeBiz.lsDepotCid2());
		req.setAttribute("lsDDCold", coldBiz.ddSelByEmpId(page, ddCool));

		return "/service/listDDCoolReg.jsp";

	}

    //完成入库
	@RequestMapping("finshDDCold")
	public String finshDDCold(HttpServletRequest req, Integer exe_id,Page<CoolregisterVo> page) {
		Execute e = new Execute();
		e.setExe_id(exe_id);
		e.setExe_end(new Date());
		coldBiz.finshEnReg(e);
		return lsDDCold(req, page, null);

	}
    //完成出库
	@RequestMapping("finshDDOutCold")
	public String finshDDOutCold(HttpServletRequest req, Integer exe_id,Page<CoolregisterVo> page) {
		Execute e = new Execute();
		e.setExe_id(exe_id);
		e.setExe_end(new Date());
		coldBiz.finshOutReg(e);
		return lsDDCold(req, page, null);

	}
	
/* 调度 ·················································*/	
	
/* 缴费 ·················································*/
	/*缴费列表*/
	@RequestMapping("lsFinanceReg")
	public String lsFinanceReg(HttpServletRequest req, Page<CoolregisterVoCtm> page, String msg,CoolregisterVoCtm coolReg) {
		if (page == null) {
			page = new Page<CoolregisterVoCtm>();
		}
		page.setPageSize(5);
		if (coolReg != null) {
			if (coolReg.getRegist_no() == null) {
				if (req.getSession().getAttribute(Constant.SESSION_SELFINANCEREG_KEY) != null) {
					coolReg = (CoolregisterVoCtm) req.getSession().getAttribute(Constant.SESSION_SELFINANCEREG_KEY);
				}
			} else {
				req.getSession().setAttribute(Constant.SESSION_SELFINANCEREG_KEY, coolReg);

			}
		}
		page.getParams().put("financeRegState", "Y");
		req.setAttribute("lsColdReg", coldBiz.lsCold(page, coolReg));
		req.setAttribute("remsg", msg);
		return "/service/listFinanceReg.jsp";

	}
	
	/*按订单id查出与计费相关信息*/
	@ResponseBody
	@RequestMapping("calculationReg")
	public CoolregisterVo calculationReg(Integer rid) {
		return coldBiz.calculationReg(rid);

	}
	/*按订单id更新订单金额*/
	@RequestMapping("calculationReg1")
	public String calculationReg1(HttpServletRequest req,CoolregisterVo c,Page<CoolregisterVoCtm> page) {	
		System.out.println(c);
		coldBiz.calculationReg1(c);
		return lsFinanceReg(req, page, null, null);

	}
	
	@RequestMapping("finshCalculation")
	public String finshCalculation(HttpServletRequest req,Integer rid,Page<CoolregisterVoCtm> page) {	
		coldBiz.finshCalculation(rid);
		return lsFinanceReg(req, page, "完成缴费", null);

	}
	
	
/* 缴费 ·················································*/
}
