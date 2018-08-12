package com.jkoss.action;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import com.jkoss.biz.IJobBiz;
import com.jkoss.pojo.Job;
import com.jkoss.pojo.vo.JobVo;
import com.jkoss.tool.Page;

@Controller
@RequestMapping("/sys")
public class SysAction {

	@Autowired
	private IJobBiz jbBiz;
	
	//列出所有角色权限信息
	@RequestMapping("/lsJb")
	public String lsJb(HttpServletRequest req,Page<JobVo> page) {
		req.setAttribute("listJob",jbBiz.listJob(page));
		req.setAttribute("allPower", jbBiz.listPower());
		return "listJobs.jsp";
	}
	//修改角色权限
	@RequestMapping("/upJbPr")
	public String upJb(HttpServletRequest req,Integer job_id,int[] power_id) {
		req.setAttribute("msg", jbBiz.upDataJobPower(job_id,power_id));
		return "lsJb.do";
		
	}
	//异步验证工作角色是否同名
	@RequestMapping("/yzJbNa")
	@ResponseBody
	public String yzJbNa(String job_name) {
		String msg = jbBiz.yanZhenJobName(job_name);
		return msg;
		
	}
	//增加一个角色
	@RequestMapping("addJb")
	public String addJb(HttpServletRequest req,Job job,int[] power_id) {
		req.setAttribute("msg", jbBiz.addJob(job,power_id));
		return "lsJb.do";
		
	}
	//删除一个角色
	@RequestMapping("deleJb")
	public String deleJb(HttpServletRequest req,Integer job_id) {
		req.setAttribute("msg", jbBiz.deleteJob(job_id));
		return "lsJb.do";
	}
	@RequestMapping("slctLKJbNa")
	public String slctLKJbNa(HttpServletRequest req,String jbname,Page<JobVo> page) {
		page.getParams().put("jbname", jbname);
		req.setAttribute("listJob", jbBiz.selectLikeJobName(page));
		req.setAttribute("allPower", jbBiz.listPower());
		return  "listJobs.jsp";
	}
}
