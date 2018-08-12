package com.jkoss.pojo;

public class Power {
    private Integer power_id;

    private String power_name;

    private String powerURL;

    private Integer rfloor;

    private Integer parentid;

    public Integer getPower_id() {
        return power_id;
    }

    public void setPower_id(Integer power_id) {
        this.power_id = power_id;
    }

    public String getPower_name() {
        return power_name;
    }

    public void setPower_name(String power_name) {
        this.power_name = power_name == null ? null : power_name.trim();
    }

    public String getPowerURL() {
        return powerURL;
    }

    public void setPowerURL(String powerURL) {
        this.powerURL = powerURL == null ? null : powerURL.trim();
    }

    public Integer getRfloor() {
        return rfloor;
    }

    public void setRfloor(Integer rfloor) {
        this.rfloor = rfloor;
    }

    public Integer getParentid() {
        return parentid;
    }

    public void setParentid(Integer parentid) {
        this.parentid = parentid;
    }
}