package com.jkoss.action;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jkoss.biz.CtmImplBiz;
import com.jkoss.pojo.vo.CustomerEx;
import com.jkoss.tool.Page;
import com.jkoss.util.Constant;

@Controller
@RequestMapping("user")
public class CtmAction {
	@Autowired
	CtmImplBiz ctmBiz;

	/**
	 * 客户信息管理
	 **/
	
	//列出客户信息列表，包括分页处理
	@RequestMapping("lsctm")
	public String lsCtm(HttpServletRequest req, Page<CustomerEx> page, CustomerEx c, String msg) {
		if (page == null) {
			page = new Page<CustomerEx>();
		}

		if (c == null) {
			c = new CustomerEx(null, null, null, null, null, null, null, null, null, null, null, null, null, null);
		}

		if (c.getCtm_type() == null) {
			if (req.getSession().getAttribute(Constant.SESSION_SELCSTM_KEY) != null) {
				c = (CustomerEx) req.getSession().getAttribute(Constant.SESSION_SELCSTM_KEY);
			}
		} else {
			req.getSession().setAttribute(Constant.SESSION_SELCSTM_KEY, c);
		}
		req.setAttribute("ctmlist", ctmBiz.lsCtmAll(page, c));
		req.setAttribute("remsg", msg);
		return "/user/listCtm.jsp";

	}
//客户账号停用
	@RequestMapping("stopctm")
	public String stopCtm(HttpServletRequest req, Integer cid) {
		return lsCtm(req, null, null, ctmBiz.stopCstm(cid));

	}
//客户增加
	@RequestMapping("addctm")
	public String addCtm(HttpServletRequest req, CustomerEx record) {
		return lsCtm(req, null, null, ctmBiz.insertSelective(record));

	}
//客户编辑：1 按id找信息加载到前台表单  2，提交信息进行修改
	@ResponseBody
	@RequestMapping("selctm")
	public CustomerEx selCtmById(Integer cid) {
		return ctmBiz.selCtmById(cid);

	}

	@RequestMapping("upctm")
	public String aupCtm(HttpServletRequest req, CustomerEx record) {
		return lsCtm(req, null, null, ctmBiz.updateByPrimaryKeySelective(record));
	}

}
