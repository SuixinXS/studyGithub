package com.jkoss.biz;

import java.util.List;

import com.jkoss.pojo.Job;
import com.jkoss.pojo.Power;
import com.jkoss.pojo.vo.JobVo;
import com.jkoss.tool.Page;

public interface IJobBiz {

	List<JobVo> listJob(Page<JobVo> page);

	List<Power> listPower();

	String upDataJobPower(Integer job_id, int[] power_id);

	String yanZhenJobName(String job_name);

	String addJob(Job job, int[] power_id);

	String deleteJob(Integer job_id);

	List<JobVo> selectLikeJobName(Page<JobVo> page);



	

}
