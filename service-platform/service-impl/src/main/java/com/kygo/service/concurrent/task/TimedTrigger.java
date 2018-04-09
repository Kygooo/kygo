package com.kygo.service.concurrent.task;

import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.TriggerContext;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class TimedTrigger implements Trigger {
	
	private Date startTime;
	
	private long timeInterval;
	
	private int repeatCount;
	
	/**
	 * 任务已经重复的次数
	 */
	private int repeatTimes = 0;
	
	/**
	 * 执行一次
	 * 
	 * @param startTime
	 *            开始时间，为空表示立即开始
	 */
	public TimedTrigger(Date startTime) {
		this(startTime, 0, TimeUnit.MILLISECONDS, 1);
	}
	
	/**
	 * 以{@link #timeInterval}的时间间隔一直重复执行
	 * 
	 * @param timeInterval
	 *            每次重复的间隔时间
	 * @param unit
	 */
	public TimedTrigger(long timeInterval, TimeUnit unit) {
		this(null, timeInterval, unit, 0);
	}
	
	/**
	 * 以{@link #timeInterval}的时间间隔执行 {@link #repeatCount}次任务
	 * 
	 * @param timeInterval
	 * @param unit
	 * @param repeatCount
	 */
	public TimedTrigger(long timeInterval, TimeUnit unit, int repeatCount) {
		this(null, timeInterval, unit, repeatCount);
	}
	
	/**
	 * 
	 * @param startTime
	 *            开始时间，为空表示立即开始
	 * @param timeInterval
	 *            每次重复的间隔时间
	 * @param unit
	 * @param repeatCount
	 *            重复次数，0表示一直重复，如果需要停止重复，需要调用 {@see ScheduledFuture} 的
	 *            {@link #cancel} 方法
	 */
	public TimedTrigger(Date startTime, long timeInterval, TimeUnit unit, int repeatCount) {
		if (timeInterval < 0 || repeatCount < 0)
			throw new IllegalArgumentException();
		
		this.startTime = startTime;
		this.timeInterval = unit.toMillis(timeInterval);
		this.repeatCount = repeatCount;
	}
	
	@Override
	public Date nextExecutionTime(TriggerContext triggerContext) {
		
		if (repeatCount > 0 && repeatTimes >= repeatCount) {
			return null;
		}
		
		repeatTimes++;
		
		if (triggerContext.lastScheduledExecutionTime() == null) {
			if (startTime == null) {
				return new Date();
			} else {
				return startTime;
			}
		} else {
			return new Date(triggerContext.lastCompletionTime().getTime() + timeInterval);
		}
	}
	
	public long getDelay(TimeUnit unit) {
		return unit.convert(timeInterval, TimeUnit.MILLISECONDS);
	}
	
	public Date getStartTime() {
		return startTime;
	}
	
	public int getRepeatCount() {
		return repeatCount;
	}
	
	public int getRepeatTimes() {
		return repeatTimes;
	}
}
