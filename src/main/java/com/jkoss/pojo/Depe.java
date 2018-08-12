package com.jkoss.pojo;

public class Depe {
    private Integer depe_id;

    private String depe_name;

    private String depe_address;

    private String depe_phone;

    private String depe_emal;

    private Integer depe_state;

    private String depe_remark;

    private String depe_director;

    public Integer getDepe_id() {
        return depe_id;
    }

    public void setDepe_id(Integer depe_id) {
        this.depe_id = depe_id;
    }

    public String getDepe_name() {
        return depe_name;
    }

    public void setDepe_name(String depe_name) {
        this.depe_name = depe_name == null ? null : depe_name.trim();
    }

    public String getDepe_address() {
        return depe_address;
    }

    public void setDepe_address(String depe_address) {
        this.depe_address = depe_address == null ? null : depe_address.trim();
    }

    public String getDepe_phone() {
        return depe_phone;
    }

    public void setDepe_phone(String depe_phone) {
        this.depe_phone = depe_phone == null ? null : depe_phone.trim();
    }

    public String getDepe_emal() {
        return depe_emal;
    }

    public void setDepe_emal(String depe_emal) {
        this.depe_emal = depe_emal == null ? null : depe_emal.trim();
    }

    public Integer getDepe_state() {
        return depe_state;
    }

    public void setDepe_state(Integer depe_state) {
        this.depe_state = depe_state;
    }

    public String getDepe_remark() {
        return depe_remark;
    }

    public void setDepe_remark(String depe_remark) {
        this.depe_remark = depe_remark == null ? null : depe_remark.trim();
    }

    public String getDepe_director() {
        return depe_director;
    }

    public void setDepe_director(String depe_director) {
        this.depe_director = depe_director == null ? null : depe_director.trim();
    }
}