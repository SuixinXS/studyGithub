package com.jkoss.pojo;

public class Goodinfo {
    private Integer good_id;

    private Integer regist_id;

    private String good_name;

    private Integer good_type;

    @Override
	public String toString() {
		return "Goodinfo [good_id=" + good_id + ", regist_id=" + regist_id + ", good_name=" + good_name + ", good_type="
				+ good_type + "]";
	}

	public Integer getGood_id() {
        return good_id;
    }

    public void setGood_id(Integer good_id) {
        this.good_id = good_id;
    }

    public Integer getRegist_id() {
        return regist_id;
    }

    public void setRegist_id(Integer regist_id) {
        this.regist_id = regist_id;
    }

    public String getGood_name() {
        return good_name;
    }

    public void setGood_name(String good_name) {
        this.good_name = good_name == null ? null : good_name.trim();
    }

    public Integer getGood_type() {
        return good_type;
    }

    public void setGood_type(Integer good_type) {
        this.good_type = good_type;
    }
}