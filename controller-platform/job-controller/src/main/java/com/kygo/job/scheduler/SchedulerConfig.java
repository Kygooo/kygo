package com.kygo.job.scheduler;

import org.quartz.Scheduler;
import org.quartz.ee.servlet.QuartzInitializerListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import java.io.IOException;
import java.util.Properties;

@Configuration
public class SchedulerConfig {

	@Autowired
	private SpringJobFactory springJobFactory;
	
	@Autowired
	private SubmitOfferJob submitOfferJob;

	@Bean
	public SchedulerFactoryBean schedulerFactoryBean() throws IOException {
		SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
		schedulerFactoryBean.setJobFactory(springJobFactory);
		schedulerFactoryBean.setQuartzProperties(quartzProperties());
		return schedulerFactoryBean;
	}

	@Bean
	public Properties quartzProperties() throws IOException {
		PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
		propertiesFactoryBean.setLocation(new ClassPathResource("/quartz.properties"));
		// 在quartz.properties中的属性被读取并注入后再初始化对象
		propertiesFactoryBean.afterPropertiesSet();
		return propertiesFactoryBean.getObject();
	}

	/*
	 * quartz初始化监听器
	 */
	@Bean
	public QuartzInitializerListener executorListener() {
		return new QuartzInitializerListener();
	}

	/*
	 * 通过SchedulerFactoryBean获取Scheduler的实例
	 */
	@Bean(name = "Scheduler")
	public Scheduler scheduler() throws IOException {
		Scheduler scheduler = schedulerFactoryBean().getScheduler();
		return scheduler;
	}

	@Bean
	public SchedulerRunner runner(@Qualifier("Scheduler") Scheduler scheduler) throws Exception {
		SchedulerRunner schedulerRunner = new SchedulerRunner();
		schedulerRunner.setScheduler(scheduler);
		JobInfo jobInfo = new JobInfo();
		jobInfo.setCronExpression("0/30 * * * * ?");
		jobInfo.setJob(submitOfferJob);
		jobInfo.setJobGroupName("offer");
		schedulerRunner.addJob(jobInfo);
		return schedulerRunner;
	}

}
