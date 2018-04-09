package com.kygo.job.logback.converter;

import ch.qos.logback.classic.pattern.ClassicConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;
import com.kygo.job.context.JobAttributes;
import com.kygo.job.context.JobContextHolder;

public class JobNameConverter extends ClassicConverter {
	
	@Override
	public String convert(ILoggingEvent event) {
		
		JobAttributes attributes = JobContextHolder.getHLAttributes();
		if (attributes == null) {
			return "";
		}
		
		Object jobName = attributes.getAttribute(JobContextHolder.JOB_NAME_KEY);
		if (jobName == null) {
			return "";
		}
		
		StringBuffer sb = new StringBuffer();
		sb.append("[jobName: ")
		  .append(jobName)
		  .append("]");

		return sb.toString();
	}
}
