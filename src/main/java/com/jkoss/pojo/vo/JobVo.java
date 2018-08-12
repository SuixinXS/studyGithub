package com.jkoss.pojo.vo;

import java.util.List;

import com.jkoss.pojo.Job;
import com.jkoss.pojo.Power;

public class JobVo extends Job{

	private List<Power> powers;

	public List<Power> getPowers() {
		return powers;
	}

	public void setPowers(List<Power> powers) {
		this.powers = powers;
	}
	
	
}
