package com.jkoss.biz;

import java.util.List;
import com.jkoss.pojo.Customer;
import com.jkoss.pojo.Customer_type;
import com.jkoss.pojo.vo.CustomerEx;
import com.jkoss.tool.Page;

public interface ICtmBiz {
	
	List<CustomerEx> lsCtmAll(Page<CustomerEx> page,CustomerEx c);
	String stopCstm(Integer ctm_id);
	String insertSelective(CustomerEx record);
	CustomerEx selCtmById(Integer cid);
	String updateByPrimaryKeySelective(CustomerEx record);

    String checkCtmLogin(String ctm_login);
    String checkCtmLogin1(Integer ctmId);
}
