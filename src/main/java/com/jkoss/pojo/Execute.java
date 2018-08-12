package com.jkoss.pojo;

import java.util.Date;

public class Execute {
    private Integer exe_id;

    private Integer regist_id;

    private Date exe_begin;

    private Date exe_end;

    private String jg_name;

    private Integer coldopera;//0 入库  1 出库 2入库作废 3出库作废

    public Integer getExe_id() {
        return exe_id;
    }

    public void setExe_id(Integer exe_id) {
        this.exe_id = exe_id;
    }

    public Integer getRegist_id() {
        return regist_id;
    }

    public void setRegist_id(Integer regist_id) {
        this.regist_id = regist_id;
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
        this.jg_name = jg_name == null ? null : jg_name.trim();
    }

    public Integer getColdopera() {
        return coldopera;
    }

    public void setColdopera(Integer coldopera) {
        this.coldopera = coldopera;
    }
}