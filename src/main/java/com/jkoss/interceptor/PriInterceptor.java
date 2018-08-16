package com.jkoss.interceptor;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;


import com.jkoss.pojo.vo.EmployVo;
import com.jkoss.util.Constant;

public class PriInterceptor implements HandlerInterceptor{

	ArrayList<String> passed = new ArrayList<>();
	
	public ArrayList<String> getPassed() {
		return passed;
	}

	public void setPassed(ArrayList<String> passed) {
		this.passed = passed;
	}

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean preHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2) throws Exception {
		// TODO Auto-generated method stub
		String url = arg0.getRequestURI();
		for(String url2 : passed) {
			if(url.indexOf(url2)>0) {
				return true;
			}
		}
		EmployVo employVo = (EmployVo) arg0.getSession().getAttribute(Constant.SESSION_USER_KEY);
		if(employVo!=null) {
			return true;
		}
		
		arg0.getRequestDispatcher("/index.jsp").forward(arg0, arg1);
        return false;
		
	}

}