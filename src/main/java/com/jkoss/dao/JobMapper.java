package com.jkoss.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import com.jkoss.pojo.Job;
import com.jkoss.pojo.vo.JobVo;
import com.jkoss.tool.Page;

public interface JobMapper {
    int deleteByPrimaryKey(Integer job_id);

    int insert(Job record);

    int insertSelective(Job record);

    Job selectByPrimaryKey(Integer job_id);

    int updateByPrimaryKeySelective(Job record);

    int updateByPrimaryKey(Job record);

    @Select("select * from job")
	List<Job> findAll();

	List<JobVo> findJobVoAll(Page<JobVo> page);

	int deleteJob2rightByjob_id(Integer job_id);

	int insertJob2rightByjob_id(Integer job_id, Integer power_id);
	
	Job selectJobName(String job_name);

	List<JobVo> selectByLikeJobName(Page<JobVo> page);
	
	@Select("select * from job where job_id=#{job_id}")
	@ResultMap("com.jkoss.dao.JobMapper.BaseResultMap2")
	JobVo  selectByPrimaryKey2(@Param(value = "job_id") Integer job_id);
}