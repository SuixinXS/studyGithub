package com.jkoss.pojo;

public class Customer {
    private Integer ctm_id;

    private Integer ctmtype_id;

    private String ctm_name;

    private Integer company_type;

    private String company_name;

    private String ctm_phone;

    private String ctm_sbm;

    private String ctm_address;

    private Float leavemoney;

    private String ctm_login;

    private String ctm_pass;

    private Integer ctm_state;
    


	public Customer() {
		super();
	}

	public Customer(Integer ctm_id, Integer ctmtype_id, String ctm_name, Integer company_type, String company_name,
			String ctm_phone, String ctm_sbm, String ctm_address, Float leavemoney, String ctm_login, String ctm_pass,
			Integer ctm_state) {
		super();
		this.ctm_id = ctm_id;
		this.ctmtype_id = ctmtype_id;
		this.ctm_name = ctm_name;
		this.company_type = company_type;
		this.company_name = company_name;
		this.ctm_phone = ctm_phone;
		this.ctm_sbm = ctm_sbm;
		this.ctm_address = ctm_address;
		this.leavemoney = leavemoney;
		this.ctm_login = ctm_login;
		this.ctm_pass = ctm_pass;
		this.ctm_state = ctm_state;
	}

	public Integer getCtm_id() {
        return ctm_id;
    }

    public void setCtm_id(Integer ctm_id) {
        this.ctm_id = ctm_id;
    }

    public Integer getCtmtype_id() {
        return ctmtype_id;
    }

    public void setCtmtype_id(Integer ctmtype_id) {
        this.ctmtype_id = ctmtype_id;
    }

    public String getCtm_name() {
        return ctm_name;
    }

    public void setCtm_name(String ctm_name) {
        this.ctm_name = ctm_name == null ? null : ctm_name.trim();
    }

    public Integer getCompany_type() {
        return company_type;
    }

    public void setCompany_type(Integer company_type) {
        this.company_type = company_type;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name == null ? null : company_name.trim();
    }

    public String getCtm_phone() {
        return ctm_phone;
    }

    public void setCtm_phone(String ctm_phone) {
        this.ctm_phone = ctm_phone == null ? null : ctm_phone.trim();
    }

    public String getCtm_sbm() {
        return ctm_sbm;
    }

    public void setCtm_sbm(String ctm_sbm) {
        this.ctm_sbm = ctm_sbm == null ? null : ctm_sbm.trim();
    }

    public String getCtm_address() {
        return ctm_address;
    }

    public void setCtm_address(String ctm_address) {
        this.ctm_address = ctm_address == null ? null : ctm_address.trim();
    }

    public Float getLeavemoney() {
        return leavemoney;
    }

    public void setLeavemoney(Float leavemoney) {
        this.leavemoney = leavemoney;
    }

    public String getCtm_login() {
        return ctm_login;
    }

    public void setCtm_login(String ctm_login) {
        this.ctm_login = ctm_login == null ? null : ctm_login.trim();
    }

    public String getCtm_pass() {
        return ctm_pass;
    }

    public void setCtm_pass(String ctm_pass) {
        this.ctm_pass = ctm_pass == null ? null : ctm_pass.trim();
    }

    public Integer getCtm_state() {
        return ctm_state;
    }

    public void setCtm_state(Integer ctm_state) {
        this.ctm_state = ctm_state;
    }


    
}