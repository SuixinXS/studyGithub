package com.jkoss.pojo.vo;

import java.util.Date;

import com.jkoss.pojo.Coolregister;

public class CoolregisterVo extends Coolregister {
	private Integer exe_id;
	
	private Date exe_begin;

	private Date exe_end;

	private String jg_name;

	private Integer coldopera;
	
	private String company_name;
    private String ctm_name;
    private String ctm_phone;
    
    private Integer emp_id;
    private String emp_name;
    private String dep_address;
    private String dep_name;
    
    private String good_type;
    private String good_name;
    
    
  
    public String getGood_type() {
		return good_type;
	}

	public void setGood_type(String good_type) {
		this.good_type = good_type;
	}

	public String getGood_name() {
		return good_name;
	}

	public void setGood_name(String good_name) {
		this.good_name = good_name;
	}

	public String getDep_name() {
		return dep_name;
	}

	public void setDep_name(String dep_name) {
		this.dep_name = dep_name;
	}

	public String getDep_address() {
		return dep_address;
	}

	public void setDep_address(String dep_address) {
		this.dep_address = dep_address;
	}

	public Integer getEmp_id() {
		return emp_id;
	}

	public void setEmp_id(Integer emp_id) {
		this.emp_id = emp_id;
	}

	public String getEmp_name() {
		return emp_name;
	}

	public void setEmp_name(String emp_name) {
		this.emp_name = emp_name;
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

	public Integer getExe_id() {
		return exe_id;
	}

	public void setExe_id(Integer exe_id) {
		this.exe_id = exe_id;
	}

	public Date getExe_begin() {
		return exe_begin;
	}

	public void setExe_begin(Date exe_begin) {
		this.exe_begin = exe_begin;
	}

	public Date getExe_end() {
		return exe_end;
	}

	public void setExe_end(Date exe_end) {
		this.exe_end = exe_end;
	}

	public String getJg_name() {
		return jg_name;
	}

	public void setJg_name(String jg_name) {
		this.jg_name = jg_name;
	}

	public Integer getColdopera() {
		return coldopera;
	}

	public void setColdopera(Integer coldopera) {
		this.coldopera = coldopera;
	}

	@Override
	public String toString() {
		return "CoolregisterVo [exe_id=" + exe_id + ", exe_begin=" + exe_begin + ", exe_end=" + exe_end + ", jg_name="
				+ jg_name + ", coldopera=" + coldopera + ", company_name=" + company_name + ", ctm_name=" + ctm_name
				+ ", ctm_phone=" + ctm_phone + ", emp_id=" + emp_id + ", emp_name=" + emp_name + ", dep_address="
				+ dep_address + ", dep_name=" + dep_name + ", good_type=" + good_type + ", good_name=" + good_name
				+ ", getGood_type()=" + getGood_type() + ", getGood_name()=" + getGood_name() + ", getDep_name()="
				+ getDep_name() + ", getDep_address()=" + getDep_address() + ", getEmp_id()=" + getEmp_id()
				+ ", getEmp_name()=" + getEmp_name() + ", getCompany_name()=" + getCompany_name() + ", getCtm_name()="
				+ getCtm_name() + ", getCtm_phone()=" + getCtm_phone() + ", getExe_id()=" + getExe_id()
				+ ", getExe_begin()=" + getExe_begin() + ", getExe_end()=" + getExe_end() + ", getJg_name()="
				+ getJg_name() + ", getColdopera()=" + getColdopera() + ", getRegist_no()=" + getRegist_no()
				+ ", getRegist_id()=" + getRegist_id() + ", getArea_id()=" + getArea_id() + ", getCab_id()="
				+ getCab_id() + ", getDep_id()=" + getDep_id() + ", getGood_id()=" + getGood_id() + ", getCtm_id()="
				+ getCtm_id() + ", getRegist_begin()=" + getRegist_begin() + ", getRegist_state()=" + getRegist_state()
				+ ", getRegist_paystate()=" + getRegist_paystate() + ", getRegist_cost()=" + getRegist_cost()
				+ ", getRegist_paytime()=" + getRegist_paytime() + ", getRegist_end()=" + getRegist_end()
				+ ", getTermperature()=" + getTermperature() + ", getCooltype()=" + getCooltype()
				+ ", getRegist_remark()=" + getRegist_remark() + ", getOperater()=" + getOperater() + ", getPhone()="
				+ getPhone() + ", toString()=" + super.toString() + ", getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + "]";
	}


	

	

	
}
