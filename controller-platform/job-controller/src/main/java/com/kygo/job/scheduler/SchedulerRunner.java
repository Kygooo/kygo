package com.kygo.job.scheduler;

import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SchedulerRunner {

	private Logger logger = LoggerFactory.getLogger(getClass().getSimpleName());

	private Scheduler scheduler;
	
	public void addJob(JobInfo jobInfo) throws Exception{
		addJob(jobInfo.getJob(), jobInfo.getJobGroupName(), jobInfo.getCronExpression());
	}
	
	public void addJob(BaseJob job, String jobGroupName, String cronExpression) throws Exception {
		// 启动调度器
		scheduler.start();
		
		SubmitOfferJob offerJob = (SubmitOfferJob)job;
		logger.debug("offerJob = {}, {} ", offerJob.getInsureTaskService(), offerJob.getMongoService());
		
		String jobClassName = job.getClass().getName();
		// 构建job信息
		JobDetail jobDetail = JobBuilder.newJob(job.getClass()).withIdentity(jobClassName, jobGroupName).build();
		// 表达式调度构建器(即任务执行的时间)
		CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression);
		// 按新的cronExpression表达式构建一个新的trigger
		CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(jobClassName, jobGroupName).withSchedule(scheduleBuilder).build();
		try {
			scheduler.scheduleJob(jobDetail, trigger);

		} catch (SchedulerException e) {
			logger.error("创建定时任务失败" + e);
			throw new Exception("创建定时任务失败", e);
		}
	}

	/*private static BaseJob getClass(String classname) throws Exception {
		Class<?> class1 = Class.forName(classname);
		return (BaseJob) class1.newInstance();
	}*/

	public Scheduler getScheduler() {
		return scheduler;
	}

	public void setScheduler(Scheduler scheduler) {
		this.scheduler = scheduler;
	}

}
