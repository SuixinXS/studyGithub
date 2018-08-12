package com.jkoss.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jkoss.biz.FreezerImpleBiz;
import com.jkoss.pojo.Areainfo;
import com.jkoss.pojo.Cabin;
import com.jkoss.pojo.Depot;
import com.jkoss.pojo.vo.DepotEx;
import com.jkoss.tool.Page;
import com.jkoss.util.Constant;

@Controller
@RequestMapping("resource")
public class FreezerAction {
	@Autowired
	FreezerImpleBiz freezeBiz;
	
	/**
	 *  冷库信息管理
	 **/

	// 冷库区域管理································
	/* 查出所有冷库区域并跳转到冷库区域页面 */
	@RequestMapping("lsare")
	public String lsAre(HttpServletRequest req, String msg) {
		req.setAttribute("arealist", freezeBiz.lsArea());
		req.setAttribute("remsg", msg);
		return "/resources/listArea.jsp";
	}

	/* 编辑更新冷库区域 */
	@ResponseBody
	@RequestMapping("selarebyid")
	public Areainfo selarebyid(HttpServletRequest req, Integer area_id) {
		return freezeBiz.selectByPrimaryKey(area_id);
	}

	@RequestMapping("upare")
	public String upAre(HttpServletRequest req, Areainfo record) {
		int d = freezeBiz.updateByPrimaryKeySelective(record);
		String msg = null;
		if (d > 0) {
			msg = "更新成功";
		} else {
			msg = "更新失败";
		}
		return lsAre(req, msg);
	}

	/* 新增冷库区域 */
	@RequestMapping("inare")
	public String inAre(HttpServletRequest req, Areainfo record) {
		int d = freezeBiz.insertSelective(record);
		String msg = null;
		if (d > 0) {
			msg = "新增成功";
		} else {
			msg = "新增失败";
		}
		return lsAre(req, msg);
	}
//删除冷库区域
	@RequestMapping("delare")
	public String delAre(HttpServletRequest req, Integer area_id) {
		int d = freezeBiz.deleteByPrimaryKey(area_id);
		String msg = null;
		if (d > 0) {
			msg = "刪除成功";
		} else {
			msg = "刪除失败";
		}
		return lsAre(req, msg);
	}
	//查重名
	@ResponseBody
	@RequestMapping("rename")
	public String reName( String area_name) {
	
		if(freezeBiz.selAreaByName(area_name)!=null) {
			return "名字已存在";
		}
		return "名字不存在";
	}
	
	// 冷库区域管理································

	// 冷库卡位管理································

	// 卡位列表
	@RequestMapping("lscabin")
	public String lsCabin(HttpServletRequest req, Page<Cabin> page, Integer aid, String msg) {
		if (page == null) {
			page = new Page<Cabin>();
		}
		if (aid == null) {
			if (req.getSession().getAttribute(Constant.SESSION_AID_KEY) == null) {
				page.getParams().put("aid", null);

			} else {
				page.getParams().put("aid", req.getSession().getAttribute(Constant.SESSION_AID_KEY));

			}
		} else {
			page.getParams().put("aid", aid);
			req.getSession().setAttribute(Constant.SESSION_AID_KEY, aid);
		}

		req.setAttribute("remsg", msg);
		req.setAttribute("cabinlist", freezeBiz.lsCabin(page));
		req.setAttribute("arealist", freezeBiz.lsArea());
		return "/resources/listCabin.jsp";
	}

//卡位增加
	@RequestMapping("incabin")
	public String inCabin(HttpServletRequest req, Cabin c) {
		return lsCabin(req, null, null, freezeBiz.insertSelective(c));
	}

//卡位删除
	@RequestMapping("delcabin")
	public String delCabin(HttpServletRequest req, Integer cid) {
		return lsCabin(req, null, null, freezeBiz.deleteCabinByPrimaryKey(cid));
	}

//卡位更新:1按id搜索放入表单  2接收信息进行修改
	@ResponseBody
	@RequestMapping("selcabinbycid")
	public Cabin selcabinbycid(HttpServletRequest req, Integer cid) {

		return freezeBiz.selectCabinByPrimaryKey(cid);
	}

	@RequestMapping("upcabin")
	public String upCabin(HttpServletRequest req, Cabin c) {
		return lsCabin(req, new Page<Cabin>(), null, freezeBiz.updateByPrimaryKeySelective(c));
	}

	/*
	 * @RequestMapping("intest") public void intest() { Cabin record = new Cabin();
	 * Depot d=new Depot(); for(int i = 52; i <= 60; i++) { record.setCab_id(i);
	 * record.setCab_number(1); freezeBiz.updateByPrimaryKeySelective(record);
	 * 
	 * d.setCab_id(i); d.setDep_director("李大爷"); d.setDep_name("西卡");
	 * freezeBiz.insertSelective(d); }
	 */

	/*
	 * for (int i = 0; i <= 50; i++) { record.setArea_id(1); record.setCab_size(2);
	 * record.setCab_no("东" + i); record.setCab_number(0);
	 * record.setCab_address("东区"); record.setCoolno(0); record.setNotcoolno(0);
	 * freezeBiz.insertSelective(record);
	 * 
	 * }
	 * 
	 * }
	 */

	// 冷库卡位管理································

	// 冷库仓库管理································

	// 仓库列表
	@RequestMapping("lsdepot")
	public String lsDepot(HttpServletRequest req, Page<DepotEx> page, String msg, Integer cid) {
		if (page == null) {
			page = new Page<DepotEx>();		
		}

		if (cid != null) {

			page.getParams().put("cid", cid);
		} else {
			page.getParams().put("cid", null);
		}

		req.setAttribute("remsg", msg);
		req.setAttribute("arealist", freezeBiz.lsArea());
		req.setAttribute("depotlist", freezeBiz.lsDepot(page));
		req.setAttribute("cablist", freezeBiz.lsAll());
		return "/resources/listDepot.jsp";
	}
//按地区ID异步加载卡位列表
	@ResponseBody
	@RequestMapping("lscabinbyaid")
	public List<Cabin> lsCabinByAid(Integer area_id) {
		return freezeBiz.lsCabinByAid(area_id);
	}
//增加仓库
	@RequestMapping("indepot")
	public String inDepot(HttpServletRequest req, Depot d) {

		return lsDepot(req, null, freezeBiz.insertSelective(d), null);
	}
//删除仓库
	@RequestMapping("deldepot")
	public String delDepot(HttpServletRequest req, Integer d_id) {

		return lsDepot(req, null, freezeBiz.deleteDepotByPrimaryKey(d_id), null);
	}
//对仓库进行更新
	@ResponseBody
	@RequestMapping("updepot1")
	public Depot upDepot1(Integer d_id) {
		return freezeBiz.selDepotByDid(d_id);
	}
	
	@RequestMapping("updepot2")
	public String upDepot2(HttpServletRequest req,Depot record) {
		
		return lsDepot(req, null, freezeBiz.updateByPrimaryKeySelective(record), null);
	}
	
/*	show卡位*/
	@RequestMapping("showCab")
	public String showCab(HttpServletRequest req,Page<Cabin>page,Integer area_id) {
		
		page.setPageSize(6);
		page.getParams().put("area_id", area_id);
		req.setAttribute("arealist", freezeBiz.lsArea());
		req.setAttribute("lsCab", freezeBiz.selAllCabADep(page));	
		req.setAttribute("aid", area_id);	
		return "/resources/showCab.jsp";
		
	}

}
