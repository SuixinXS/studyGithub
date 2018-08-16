package com.jkoss.biz;




import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.jkoss.dao.JobMapper;
import com.jkoss.dao.PowerMapper;
import com.jkoss.pojo.Job;
import com.jkoss.pojo.Power;
import com.jkoss.pojo.vo.JobVo;
import com.jkoss.tool.Page;

@Component
public class JobImpl implements IJobBiz{

	@Autowired
	private JobMapper jbDao;
	@Autowired
	private PowerMapper prDao;
	@Override
	public List<JobVo> listJob(Page<JobVo> page) {
		// TODO Auto-generated method stub
		return jbDao.findJobVoAll(page);
	}
	@Override
	public List<Power> listPower() {
		// TODO Auto-generated method stub
		return prDao.findAll();
	}
	@Override
	public String upDataJobPower(Integer job_id, int[] power_id) {
		// TODO Auto-generated method stub
		//先删除所有有关的权限
		jbDao.deleteJob2rightByjob_id(job_id);
		for (int i : power_id) {
			jbDao.insertJob2rightByjob_id(job_id,i);
			
		}
		return "修改成功";
	}
	@Override
	public String yanZhenJobName(String job_name) {
		// TODO Auto-generated method stub
		Job job = jbDao.selectJobName(job_name);
		if(job!=null||job_name.equals("")){
			return "0";
		}else {
			return "1";
		}
	}
	@Override
	@Transactional
	public String addJob(Job job, int[] power_id) {
		// TODO Auto-generated method stub
		//增加Job表
		int i=0;
		if (job.getJob_name().equals("")||jbDao.selectJobName(job.getJob_name())!=null) {
			return "添加失败";
		}else {
			i=jbDao.insert(job);
			Job job2 = jbDao.selectJobName(job.getJob_name());
			if(power_id!=null) {
				for (int j : power_id) {
					  i = jbDao.insertJob2rightByjob_id(job2.getJob_id(),j);
					}
			}
			return i==1?"添加成功":"添加失败";
		}
	
	}
	@Override
	public String deleteJob(Integer job_id) {
		// TODO Auto-generated method stub
		//删除 job2right表
		int i = jbDao.deleteJob2rightByjob_id(job_id);
		//删除 job表
	    i = jbDao.deleteByPrimaryKey(job_id);
		return i==1?"删除成功":"删除失败";
	}
	@Override
	public List<JobVo> selectLikeJobName(Page<JobVo> page) {
		// TODO Auto-generated method stub
		return jbDao.selectByLikeJobName(page);
	}
	
	
	

}
