package com.jkoss.pojo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class Coolregister {
    private Integer regist_id;

    private Integer exe_id;

    private Integer area_id;

    private Integer cab_id;

    private Integer dep_id;

    private Integer good_id;

    private Integer ctm_id;

    private Date regist_begin;

    private Integer regist_state;//0  登记 1  已安排打冷 2  中断 3  结束打冷 4  作废 

    private Integer regist_paystate;//'0 未付  1 已付',

    private Float regist_cost;
  /*  @DateTimeFormat(pattern = "yyyy-MM-dd") */
    private Date regist_paytime;
    
    private Date regist_end;

    private Float termperature;

    private Integer cooltype;//0  付费打冷 1  预付打冷 2 后付打冷 3 补时'

    private String regist_remark;

    private String operater;//0 出库1入库

    private String phone;

    public Integer getRegist_id() {
        return regist_id;
    }

    public void setRegist_id(Integer regist_id) {
        this.regist_id = regist_id;
    }

    public Integer getExe_id() {
        return exe_id;
    }

    public void setExe_id(Integer exe_id) {
        this.exe_id = exe_id;
    }

    public Integer getArea_id() {
        return area_id;
    }

    public void setArea_id(Integer area_id) {
        this.area_id = area_id;
    }

    public Integer getCab_id() {
        return cab_id;
    }

    public void setCab_id(Integer cab_id) {
        this.cab_id = cab_id;
    }

    public Integer getDep_id() {
        return dep_id;
    }

    public void setDep_id(Integer dep_id) {
        this.dep_id = dep_id;
    }

    public Integer getGood_id() {
        return good_id;
    }

    public void setGood_id(Integer good_id) {
        this.good_id = good_id;
    }

    public Integer getCtm_id() {
        return ctm_id;
    }

    public void setCtm_id(Integer ctm_id) {
        this.ctm_id = ctm_id;
    }

    public Date getRegist_begin() {
        return regist_begin;
    }

    public void setRegist_begin(Date regist_begin) {
        this.regist_begin = regist_begin;
    }

    public Integer getRegist_state() {
        return regist_state;
    }

    public void setRegist_state(Integer regist_state) {
        this.regist_state = regist_state;
    }

    public Integer getRegist_paystate() {
        return regist_paystate;
    }

    public void setRegist_paystate(Integer regist_paystate) {
        this.regist_paystate = regist_paystate;
    }

    public Float getRegist_cost() {
        return regist_cost;
    }

    public void setRegist_cost(Float regist_cost) {
        this.regist_cost = regist_cost;
    }

    public Date getRegist_paytime() {
        return regist_paytime;
    }

    public void setRegist_paytime(Date regist_paytime) {
        this.regist_paytime = regist_paytime;
    }

    public Date getRegist_end() {
        return regist_end;
    }

    public void setRegist_end(Date regist_end) {
        this.regist_end = regist_end;
    }

    public Float getTermperature() {
        return termperature;
    }

    public void setTermperature(Float termperature) {
        this.termperature = termperature;
    }

    public Integer getCooltype() {
        return cooltype;
    }

    public void setCooltype(Integer cooltype) {
        this.cooltype = cooltype;
    }

    public String getRegist_remark() {
        return regist_remark;
    }

    public void setRegist_remark(String regist_remark) {
        this.regist_remark = regist_remark == null ? null : regist_remark.trim();
    }

    public String getOperater() {
        return operater;
    }

    public void setOperater(String operater) {
        this.operater = operater == null ? null : operater.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

	@Override
	public String toString() {
		return "Coolregister [regist_id=" + regist_id + ", exe_id=" + exe_id + ", area_id=" + area_id + ", cab_id="
				+ cab_id + ", dep_id=" + dep_id + ", good_id=" + good_id + ", ctm_id=" + ctm_id + ", regist_begin="
				+ regist_begin + ", regist_state=" + regist_state + ", regist_paystate=" + regist_paystate
				+ ", regist_cost=" + regist_cost + ", regist_paytime=" + regist_paytime + ", regist_end=" + regist_end
				+ ", termperature=" + termperature + ", cooltype=" + cooltype + ", regist_remark=" + regist_remark
				+ ", operater=" + operater + ", phone=" + phone + "]";
	}
    
    
}