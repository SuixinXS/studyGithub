package com.jkoss.pojo.vo;

import com.jkoss.pojo.Customer;

public class CustomerEx extends Customer{
    private Integer ctm_type;

    private Integer pay_type;

	public Integer getCtm_type() {
		return ctm_type;
	}

	public void setCtm_type(Integer ctm_type) {
		this.ctm_type = ctm_type;
	}

	public Integer getPay_type() {
		return pay_type;
	}

	public void setPay_type(Integer pay_type) {
		this.pay_type = pay_type;
	}

	

	
	

	public CustomerEx() {
		super();
	}

	public CustomerEx(Integer ctm_id, Integer ctmtype_id, String ctm_name, Integer company_type, String company_name,
			String ctm_phone, String ctm_sbm, String ctm_address, Float leavemoney, String ctm_login, String ctm_pass,
			Integer ctm_state, Integer ctm_type, Integer pay_type) {
		super(ctm_id, ctmtype_id, ctm_name, company_type, company_name, ctm_phone, ctm_sbm, ctm_address, leavemoney,
				ctm_login, ctm_pass, ctm_state);
		this.ctm_type = ctm_type;
		this.pay_type = pay_type;
	}

	@Override
	public String toString() {
		return "CustomerEx [ctm_type=" + ctm_type + ", pay_type=" + pay_type + ", getCtm_type()=" + getCtm_type()
				+ ", getPay_type()=" + getPay_type() + ", getCtm_id()=" + getCtm_id() + ", getCtmtype_id()="
				+ getCtmtype_id() + ", getCtm_name()=" + getCtm_name() + ", getCompany_type()=" + getCompany_type()
				+ ", getCompany_name()=" + getCompany_name() + ", getCtm_phone()=" + getCtm_phone() + ", getCtm_sbm()="
				+ getCtm_sbm() + ", getCtm_address()=" + getCtm_address() + ", getLeavemoney()=" + getLeavemoney()
				+ ", getCtm_login()=" + getCtm_login() + ", getCtm_pass()=" + getCtm_pass() + ", getCtm_state()="
				+ getCtm_state() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}
    
}
