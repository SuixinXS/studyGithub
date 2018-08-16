package com.jkoss.pojo.vo;

import java.util.Date;

import com.jkoss.pojo.Coolregister;

public class CoolregisterVoCtm extends Coolregister {
	private String company_name;
    private String ctm_name;
    private String ctm_phone;
    private String ctm_login;
    
    
    
    
	public CoolregisterVoCtm() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public String getCtm_login() {
		return ctm_login;
	}


	public void setCtm_login(String ctm_login) {
		this.ctm_login = ctm_login;
	}


	public String getCompany_name() {
		return company_name;
	}
	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}
	public String getCtm_name() {
		return ctm_name;
	}
	public void setCtm_name(String ctm_name) {
		this.ctm_name = ctm_name;
	}
	public String getCtm_phone() {
		return ctm_phone;
	}
	public void setCtm_phone(String ctm_phone) {
		this.ctm_phone = ctm_phone;
	}
	public CoolregisterVoCtm(Integer regist_id, String regist_no, Integer exe_id, Integer area_id, Integer cab_id,
			Integer dep_id, Integer good_id, Integer ctm_id, Date regist_begin, Integer regist_state,
			Integer regist_paystate, Float regist_cost, Date regist_paytime, Date regist_end, Float termperature,
			Integer cooltype, String regist_remark, String operater, String phone, String company_name, String ctm_name,
			String ctm_phone) {
		super(regist_id, regist_no, exe_id, area_id, cab_id, dep_id, good_id, ctm_id, regist_begin, regist_state,
				regist_paystate, regist_cost, regist_paytime, regist_end, termperature, cooltype, regist_remark,
				operater, phone);
		this.company_name = company_name;
		this.ctm_name = ctm_name;
		this.ctm_phone = ctm_phone;
	}
	
	
	
	
    
    
	
}
