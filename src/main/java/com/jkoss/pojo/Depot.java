package com.jkoss.pojo;

public class Depot {
    private Integer dep_id;

	private Integer cab_id;

	private String dep_address;

	private String dep_name;

	private Float dep_capacity;

	private Integer dep_state;

	private String dep_director;

	public Integer getDep_id() {
		return dep_id;
	}

	public void setDep_id(Integer dep_id) {
		this.dep_id = dep_id;
	}

	public Integer getCab_id() {
		return cab_id;
	}

	public void setCab_id(Integer cab_id) {
		this.cab_id = cab_id;
	}

	public String getDep_address() {
		return dep_address;
	}

	public void setDep_address(String dep_address) {
		this.dep_address = dep_address == null ? null : dep_address.trim();
	}

	public String getDep_name() {
		return dep_name;
	}

	public void setDep_name(String dep_name) {
		this.dep_name = dep_name == null ? null : dep_name.trim();
	}

	public Float getDep_capacity() {
		return dep_capacity;
	}

	public void setDep_capacity(Float dep_capacity) {
		this.dep_capacity = dep_capacity;
	}

	public Integer getDep_state() {
		return dep_state;
	}

	public void setDep_state(Integer dep_state) {
		this.dep_state = dep_state;
	}

	public String getDep_director() {
		return dep_director;
	}

	public void setDep_director(String dep_director) {
		this.dep_director = dep_director == null ? null : dep_director.trim();
	}

	@Override
	public String toString() {
		return "Depot [dep_id=" + dep_id + ", cab_id=" + cab_id + ", dep_address=" + dep_address + ", dep_name="
				+ dep_name + ", dep_capacity=" + dep_capacity + ", dep_state=" + dep_state + ", dep_director="
				+ dep_director + "]";
	}

	
}