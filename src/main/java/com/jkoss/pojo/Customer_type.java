package com.jkoss.pojo;

public class Customer_type {
    private Integer ctmtype_id;

    private Integer ctm_id;

    private Integer ctm_type;

    private Integer pay_type;
    
    
    
    

    public Customer_type() {
		super();
	}

	public Customer_type(Integer ctmtype_id, Integer ctm_id, Integer ctm_type, Integer pay_type) {
		super();
		this.ctmtype_id = ctmtype_id;
		this.ctm_id = ctm_id;
		this.ctm_type = ctm_type;
		this.pay_type = pay_type;
	}

	public Integer getCtmtype_id() {
        return ctmtype_id;
    }
   
    public void setCtmtype_id(Integer ctmtype_id) {
        this.ctmtype_id = ctmtype_id;
    }

    public Integer getCtm_id() {
        return ctm_id;
    }

    public void setCtm_id(Integer ctm_id) {
        this.ctm_id = ctm_id;
    }

    public Integer getCtm_type() {
        return ctm_type;
    }

    public void setCtm_type(Integer ctm_type) {
        this.ctm_type = ctm_type;
    }

    public Integer getPay_type() {
        return pay_type;
    }

    public void setPay_type(Integer pay_type) {
        this.pay_type = pay_type;
    }

	@Override
	public String toString() {
		return "Customer_type [ctmtype_id=" + ctmtype_id + ", ctm_id=" + ctm_id + ", ctm_type=" + ctm_type
				+ ", pay_type=" + pay_type + "]";
	}
    
}