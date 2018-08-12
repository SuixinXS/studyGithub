package com.jkoss.pojo.vo;

import com.jkoss.pojo.Employ;
import com.jkoss.pojo.Job;

public class EmployVo extends Employ{

	private String depe_name;
	//private String job_name;
	private JobVo job;
	
	
	public JobVo getJob() {
		return job;
	}
	public void setJob(JobVo job) {
		this.job = job;
	}
	public String getDepe_name() {
		return depe_name;
	}
	public void setDepe_name(String depe_name) {
		this.depe_name = depe_name;
	}
	@Override
	public String toString() {
		return "EmployVo [depe_name=" + depe_name + ", job=" + job + ", getJob()=" + getJob() + ", getDepe_name()="
				+ getDepe_name() + ", getEmp_id()=" + getEmp_id() + ", getExe_id()=" + getExe_id() + ", getDepe_id()="
				+ getDepe_id() + ", getJob_id()=" + getJob_id() + ", getEmp_name()=" + getEmp_name() + ", getEmp_sex()="
				+ getEmp_sex() + ", getEmp_login()=" + getEmp_login() + ", getEmp_pass()=" + getEmp_pass()
				+ ", getEmp_phone()=" + getEmp_phone() + ", getEmp_state()=" + getEmp_state() + ", getIdentitily()="
				+ getIdentitily() + ", getAddress()=" + getAddress() + ", getSchool()=" + getSchool()
				+ ", getAfterschool()=" + getAfterschool() + ", getIntime()=" + getIntime() + ", getEmp_remark()="
				+ getEmp_remark() + ", getSalary()=" + getSalary() + ", getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + ", toString()=" + super.toString() + "]";
	}

	
}
