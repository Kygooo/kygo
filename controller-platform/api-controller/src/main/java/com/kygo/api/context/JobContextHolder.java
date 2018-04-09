package com.kygo.api.context;

import org.springframework.core.NamedThreadLocal;

public class JobContextHolder {
	
	public static String JOB_NAME_KEY = "job_name";
	
	private static final ThreadLocal<JobAttributes> hlThreadLocal = new NamedThreadLocal<JobAttributes>("HL attributes");
	
	public static void resetHLAttributes() {
		hlThreadLocal.remove();
	}
	
	public static void setHLAttributes(JobAttributes attributes) {
		if (attributes == null) {
			resetHLAttributes();
		} else {
			hlThreadLocal.set(attributes);
		}
	}
	
	public static JobAttributes getHLAttributes() {
		JobAttributes attributes = hlThreadLocal.get();
		
		if (attributes == null) {
			attributes = new JobAttributes();
			hlThreadLocal.set(attributes);
		}
		return attributes;
	}
}
