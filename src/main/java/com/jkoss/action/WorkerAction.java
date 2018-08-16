package com.jkoss.action;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jkoss.biz.IEmployBiz;
import com.jkoss.pojo.Employ;
import com.jkoss.pojo.Job;
import com.jkoss.pojo.vo.EmployVo;
import com.jkoss.tool.Page;

@Controller
@RequestMapping("/wk")
public class WorkerAction {

	@Autowired
	private IEmployBiz epBiz;
	/////————————————————》员工管理
	
	///列出员工信息
	@RequestMapping("/lsEp")
	public String listEmploy(HttpServletRequest req,Page<EmployVo> page,Integer wk_dp_id) {
		if(page==null) {
			page = new Page<EmployVo>();
		}
		if(req.getServletContext().getAttribute("wkLsEp")!=null) {
			req.getServletContext().removeAttribute("wkLsEp");
		}
		req.getServletContext().setAttribute("wkLsDp",epBiz.listDepe());
		
		if(wk_dp_id==null) {
			if(req.getSession().getAttribute("wk_dp_id")!=null) {
				Integer wk_dp_id2= (Integer) req.getSession().getAttribute("wk_dp_id");
				page.getParams().put("wk_dp_id", wk_dp_id2);
				req.setAttribute("wkLsEp", epBiz.listEmployByDp_id(page));
			}else {
			
				req.setAttribute("wkLsEp", epBiz.listEmploy(page));
				System.out.println( epBiz.listEmploy(page).get(0).getJob());
			}
			
		}else if(wk_dp_id==0) {
			req.getSession().removeAttribute("wk_dp_id");
			req.setAttribute("wkLsEp",epBiz.listEmploy(page));
		}else {
			
			req.getSession().setAttribute("wk_dp_id", wk_dp_id);
			page.getParams().put("wk_dp_id", wk_dp_id);
			req.setAttribute("wkLsEp", epBiz.listEmployByDp_id(page));
		}
		return "listEmp.jsp";
	}
	///批量员工数据
	@RequestMapping("/addXnEp")
	public String addXnEp(HttpServletRequest req,int num) {
		req.setAttribute("msg",epBiz.addXnEp(num));
		return "lsEp.do" ;
	}
	///获取工作角色表信息（job）
	@RequestMapping("/lsJb")
	@ResponseBody
	public List<Job> listJob(){
		return epBiz.listJob();
	}
	///增加一条员工
	@RequestMapping("/addEp")
	public String addEp(HttpServletRequest req,Employ employ) {
		req.setAttribute("msg", epBiz.addOneEp(employ));
		return "lsEp.do";
	}
	///查找员工信息  by emp_id
	@RequestMapping("/slEp")
	@ResponseBody
	public Employ slEp(HttpServletRequest req,Integer wk_ep_id) {
		return epBiz.selectEpById(wk_ep_id);
	}
	///修改一条员工信息
	@RequestMapping("/UpEp")
	public String UpEp(HttpServletRequest req,Employ employ) {
		System.out.println("修改一条员工"+employ.toString());
		req.setAttribute("msg", epBiz.UpdateEpById(employ));	
		return "lsEp.do";
	}
	///修改账号状态 
	@RequestMapping("/upEpLgState")
	@ResponseBody
	public String upEpLgState(Integer wk_ep_id,Integer emp_state) {
	    System.out.println(wk_ep_id);
	    System.out.println(emp_state);
	    Employ employ = new Employ();
	    employ.setEmp_id(wk_ep_id);
	    employ.setEmp_state(emp_state);
	    String msg=epBiz.UpdateEpById(employ);
	    System.out.println(msg);
	    return msg;
	   // req.setAttribute("msg", );
	}
	///员工姓名模糊查询
	@RequestMapping("/slktNa")
	public String slktNa(String wk_ep_name,Page<EmployVo> page,HttpServletRequest req) {
		page.getParams().put("wk_ep_name", wk_ep_name);
		req.setAttribute("wkLsEp", epBiz.selectEpByLkName(page));
		return "listEmp.jsp";
		
	}

	///异步验证用户账号是否同名
	@RequestMapping("/yanzhenLg")
	@ResponseBody
    public int yanzhenLgNa(String emp_login) {
		System.out.println(epBiz.selectEpByLg(emp_login));
		return epBiz.selectEpByLg(emp_login);
	}
	/////《——————————————————————
}
