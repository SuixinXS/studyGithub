package com.jkoss.pojo;

public class Areainfo {
    private Integer area_id;

	private String area_name;

	private String area_remark;

	public Integer getArea_id() {
		return area_id;
	}

	public void setArea_id(Integer area_id) {
		this.area_id = area_id;
	}

	public String getArea_name() {
		return area_name;
	}

	public void setArea_name(String area_name) {
		this.area_name = area_name == null ? null : area_name.trim();
	}

	public String getArea_remark() {
		return area_remark;
	}

	public void setArea_remark(String area_remark) {
		this.area_remark = area_remark == null ? null : area_remark.trim();
	}

	private Integer ID;

    private String AREANAME;

    private String REMARK;

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getAREANAME() {
        return AREANAME;
    }

    public void setAREANAME(String AREANAME) {
        this.AREANAME = AREANAME == null ? null : AREANAME.trim();
    }

    public String getREMARK() {
        return REMARK;
    }

    public void setREMARK(String REMARK) {
        this.REMARK = REMARK == null ? null : REMARK.trim();
    }
}