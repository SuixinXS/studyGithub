package com.jkoss.pojo;

import java.util.ArrayList;
import java.util.List;

public class Cabin {
    private Integer cab_id;

    private String cab_address;

    private Integer cab_size;

    private String cab_remark;

    private Integer cab_state;

    private String cab_no;

    private Integer cab_number;

    private Integer coolno;

    private Integer notcoolno;

    private Integer area_id;
    
    private List<Depot> depot=new ArrayList<>();

	public List<Depot> getDepot() {
		return depot;
	}

	public void setDepot(List<Depot> depot) {
		this.depot = depot;
	}

	public Integer getCab_id() {
        return cab_id;
    }

    public void setCab_id(Integer cab_id) {
        this.cab_id = cab_id;
    }

    public String getCab_address() {
        return cab_address;
    }

    public void setCab_address(String cab_address) {
        this.cab_address = cab_address == null ? null : cab_address.trim();
    }

    public Integer getCab_size() {
        return cab_size;
    }

    public void setCab_size(Integer cab_size) {
        this.cab_size = cab_size;
    }

    public String getCab_remark() {
        return cab_remark;
    }

    public void setCab_remark(String cab_remark) {
        this.cab_remark = cab_remark == null ? null : cab_remark.trim();
    }

    public Integer getCab_state() {
        return cab_state;
    }

    public void setCab_state(Integer cab_state) {
        this.cab_state = cab_state;
    }

    public String getCab_no() {
        return cab_no;
    }

    public void setCab_no(String cab_no) {
        this.cab_no = cab_no == null ? null : cab_no.trim();
    }

    public Integer getCab_number() {
        return cab_number;
    }

    public void setCab_number(Integer cab_number) {
        this.cab_number = cab_number;
    }

    public Integer getCoolno() {
        return coolno;
    }

    public void setCoolno(Integer coolno) {
        this.coolno = coolno;
    }

    public Integer getNotcoolno() {
        return notcoolno;
    }

    public void setNotcoolno(Integer notcoolno) {
        this.notcoolno = notcoolno;
    }

    public Integer getArea_id() {
        return area_id;
    }

    public void setArea_id(Integer area_id) {
        this.area_id = area_id;
    }

	@Override
	public String toString() {
		return "Cabin [cab_id=" + cab_id + ", cab_address=" + cab_address + ", cab_size=" + cab_size + ", cab_remark="
				+ cab_remark + ", cab_state=" + cab_state + ", cab_no=" + cab_no + ", cab_number=" + cab_number
				+ ", coolno=" + coolno + ", notcoolno=" + notcoolno + ", area_id=" + area_id + ", depot=" + depot + "]";
	}
    
    
    
}