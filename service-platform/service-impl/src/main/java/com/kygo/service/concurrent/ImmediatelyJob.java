package com.kygo.service.concurrent;

import com.kygo.common.utils.DateUtil;
import com.kygo.service.concurrent.task.TimedTrigger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 执行即时任务
 * 
 * @author Jxf
 *
 */
@Component
public class ImmediatelyJob {
	
	private Logger logger = LoggerFactory.getLogger(getClass().getSimpleName());
	
	private int executeDelaySeconds = 2;
	
	private static int CORE_POOL_SIZE = 20;
	private static int MAXIMUM_POOL_SIZE = 40;
	private static long keepAliveSeconds = 60;
	
	private static ConcurrentTaskScheduler scheduler;
	
	static {
		
		ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(CORE_POOL_SIZE);
		executor.setMaximumPoolSize(MAXIMUM_POOL_SIZE);
		executor.setKeepAliveTime(keepAliveSeconds, TimeUnit.SECONDS);
		scheduler = new ConcurrentTaskScheduler(executor);
	}
	
	public void schedule(Runnable task, Trigger trigger) {
		printExecutorInfo();
		scheduler.schedule(task, trigger);
	}
	
	public void execute(Runnable task) {
		printExecutorInfo();
		scheduler.execute(task);
	}
	
	public void executeSoonLater(Runnable task) {
		printExecutorInfo();
		TimedTrigger trigger = new TimedTrigger(DateUtil.getDateLater(Calendar.SECOND, executeDelaySeconds));
		scheduler.schedule(task, trigger);
	}
	
	public ConcurrentTaskScheduler getScheduler() {
		printExecutorInfo();
		return scheduler;
	}
	
	private void printExecutorInfo() {
		logger.debug("线程池 - " + scheduler.getConcurrentExecutor().toString().toString());
	}
}
