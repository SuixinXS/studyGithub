package com.jkoss.pojo;

import java.util.Date;

public class Employ {
    private Integer emp_id;

    private Integer exe_id;

    private Integer depe_id;

    private Integer job_id;

    private String emp_name;

    private Integer emp_sex;

    private String emp_login;

    private String emp_pass;

    private String emp_phone;

    private Integer emp_state;

    private String identitily;

    private String address;

    private String school;

    private Date afterschool;

    private Date intime;

    private String emp_remark;

    private Float salary;

    public Integer getEmp_id() {
        return emp_id;
    }

    public void setEmp_id(Integer emp_id) {
        this.emp_id = emp_id;
    }

    public Integer getExe_id() {
        return exe_id;
    }

    public void setExe_id(Integer exe_id) {
        this.exe_id = exe_id;
    }

    public Integer getDepe_id() {
        return depe_id;
    }

    public void setDepe_id(Integer depe_id) {
        this.depe_id = depe_id;
    }

    public Integer getJob_id() {
        return job_id;
    }

    public void setJob_id(Integer job_id) {
        this.job_id = job_id;
    }

    public String getEmp_name() {
        return emp_name;
    }

    public void setEmp_name(String emp_name) {
        this.emp_name = emp_name == null ? null : emp_name.trim();
    }

    public Integer getEmp_sex() {
        return emp_sex;
    }

    public void setEmp_sex(Integer emp_sex) {
        this.emp_sex = emp_sex;
    }

    public String getEmp_login() {
        return emp_login;
    }

    public void setEmp_login(String emp_login) {
        this.emp_login = emp_login == null ? null : emp_login.trim();
    }

    public String getEmp_pass() {
        return emp_pass;
    }

    public void setEmp_pass(String emp_pass) {
        this.emp_pass = emp_pass == null ? null : emp_pass.trim();
    }

    public String getEmp_phone() {
        return emp_phone;
    }

    public void setEmp_phone(String emp_phone) {
        this.emp_phone = emp_phone == null ? null : emp_phone.trim();
    }

    public Integer getEmp_state() {
        return emp_state;
    }

    public void setEmp_state(Integer emp_state) {
        this.emp_state = emp_state;
    }

    public String getIdentitily() {
        return identitily;
    }

    public void setIdentitily(String identitily) {
        this.identitily = identitily == null ? null : identitily.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school == null ? null : school.trim();
    }

    public Date getAfterschool() {
        return afterschool;
    }

    public void setAfterschool(Date afterschool) {
        this.afterschool = afterschool;
    }

    public Date getIntime() {
        return intime;
    }

    public void setIntime(Date intime) {
        this.intime = intime;
    }

    public String getEmp_remark() {
        return emp_remark;
    }

    public void setEmp_remark(String emp_remark) {
        this.emp_remark = emp_remark == null ? null : emp_remark.trim();
    }

    public Float getSalary() {
        return salary;
    }

    public void setSalary(Float salary) {
        this.salary = salary;
    }
}