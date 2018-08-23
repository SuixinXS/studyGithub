package com.jkoss.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jkoss.biz.IEmployBiz;
import com.jkoss.bo.DTreePriNode;
import com.jkoss.dao.EmployMapper;
import com.jkoss.pojo.Employ;
import com.jkoss.pojo.vo.EmployVo;
import com.jkoss.util.Constant;
@Controller
@RequestMapping("fz")
public class LoginAction {
	
	@Autowired 
	private IEmployBiz epBiz;
	
	@RequestMapping("lgn")
	public String 登录(HttpServletRequest req,String lgnName, String pwdword) {	
		EmployVo employVo = epBiz.selectEpByLgPw(lgnName,pwdword);		
		if (employVo!=null&&employVo.getEmp_state()==1) {
			req.getSession().setAttribute(Constant.SESSION_USER_KEY,employVo);
			return "/main.jsp";
		}
		/*if (lgnName.equals("admin") && pwdword.equals("admin")) {  
			req.getSession().setAttribute(Constant.SESSION_USER_KEY,"测试用户");
			return "/main.jsp";
		}*/
		req.setAttribute("msg", "登录失败");
		return "/index.jsp";
	} 	
	//动态生成权限表
	@RequestMapping("/genPw")
	@ResponseBody
	public Map<Integer,DTreePriNode > genTress(HttpServletRequest req){
		EmployVo employVo = (EmployVo) req.getSession().getAttribute(Constant.SESSION_USER_KEY);
		if(employVo==null) {
			return null;
		}
		return epBiz.generatePower(employVo);
	}
	
	//账号登出
	@RequestMapping("/lgout")
    public String lgout(HttpServletRequest req) {
		req.getSession().removeAttribute(Constant.SESSION_USER_KEY);
		return "/index.jsp";
	}
	
	
	
}
