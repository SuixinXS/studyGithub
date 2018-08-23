package com.jkoss.biz;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jkoss.dao.CustomerMapper;
import com.jkoss.dao.Customer_typeMapper;
import com.jkoss.pojo.Customer;
import com.jkoss.pojo.Customer_type;
import com.jkoss.pojo.vo.CustomerEx;
import com.jkoss.tool.Page;

@Component
public class CtmImplBiz implements ICtmBiz {
	@Autowired
	CustomerMapper customerDao;
	@Autowired
	Customer_typeMapper customer_typeDao;
	
	
	
	@Override
	public List<CustomerEx> lsCtmAll(Page<CustomerEx> page,CustomerEx c) {	
		if(c!=null) {
		page.getParams().put("ctm_id", c.getCtm_id());
		page.getParams().put("company_type", c.getCompany_type());		
		page.getParams().put("ctm_state",c.getCtm_state());
		page.getParams().put("ctm_type",c.getCtm_type());
		page.getParams().put("pay_type",c.getPay_type());}
		return customerDao.lsCtmAll(page);
	}
	
	public String stopCstm(Integer ctm_id) {

		if (customerDao.stopCstm(ctm_id) > 0) {
			return "停用成功";
		}
		return "停用失败";
	}
	
	public String insertSelective(CustomerEx record) {

		int p = customerDao.insertSelective(record);
		Customer_type c=new Customer_type(null,record.getCtm_id(),record.getCtm_type(),record.getPay_type());
		int p1 = customer_typeDao.insertSelective(c);
		if (p > 0 && p1 > 0) {
			return "增加成功";
		}

		return "增加失败";
	}

	@Override
	public CustomerEx selCtmById(Integer cid) {
		// TODO Auto-generated method stub
		return customerDao.selCtmById(cid);
	}

	@Override
	public String updateByPrimaryKeySelective(CustomerEx record) {
		// TODO Auto-generated method stub
		int p=customerDao.updateByPrimaryKeySelective(record);
		Customer_type c=new Customer_type(null,record.getCtm_id(),record.getCtm_type(),record.getPay_type());
		int p1=customer_typeDao.updateByCtm_id(c);
		
		return "修改成功";
	}

	@Override
	public String checkCtmLogin(String ctm_login) {

		if(customerDao.selCtmByLogin(ctm_login)!=null) {
			return "重名";
		}
		return "可用";
	}
	
	
	public String checkCtmLogin1(Integer ctmId) {

		
		return customerDao.selectByPrimaryKey(ctmId).getCtm_login();
	}

	
	 
	 
	/*public List<Customer> lsCtmAll(Page<Customer> page, Customer c) {
		
		 page.getParams().put("ctm_id", c.getCtm_id());
		  page.getParams().put("ctm_state", 1); page.getParams().put("ctmtype_id",
		 c.getCtmtype_id());
		return customerDao.lsCtmAll(page);
	}
*/





}
