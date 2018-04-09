package com.kygo.job.scheduler;

public class JobInfo {
	
	private BaseJob job;
	
	private String jobGroupName;
	
	private String cronExpression;

	public BaseJob getJob() {
		return job;
	}

	public void setJob(BaseJob job) {
		this.job = job;
	}

	public String getJobGroupName() {
		return jobGroupName;
	}

	public void setJobGroupName(String jobGroupName) {
		this.jobGroupName = jobGroupName;
	}

	public String getCronExpression() {
		return cronExpression;
	}

	public void setCronExpression(String cronExpression) {
		this.cronExpression = cronExpression;
	}
	
}
