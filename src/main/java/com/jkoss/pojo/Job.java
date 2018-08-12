package com.jkoss.pojo;

public class Job {
    private Integer job_id;

    private String job_name;

    public Integer getJob_id() {
        return job_id;
    }

    public void setJob_id(Integer job_id) {
        this.job_id = job_id;
    }

    public String getJob_name() {
        return job_name;
    }

    public void setJob_name(String job_name) {
        this.job_name = job_name == null ? null : job_name.trim();
    }
}