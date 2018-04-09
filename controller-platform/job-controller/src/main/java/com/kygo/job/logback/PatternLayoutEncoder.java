package com.kygo.job.logback;

import ch.qos.logback.classic.PatternLayout;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.pattern.PatternLayoutEncoderBase;
import com.kygo.job.logback.converter.HttpRequestConverter;
import com.kygo.job.logback.converter.JobNameConverter;

import java.util.HashMap;
import java.util.Map;

public class PatternLayoutEncoder extends PatternLayoutEncoderBase<ILoggingEvent> {
	
	@Override
	public void start() {
		PatternLayout patternLayout = new PatternLayout();
		patternLayout.getInstanceConverterMap().putAll(customerConverterMap());
		patternLayout.setContext(context);
		patternLayout.setPattern(getPattern());
		patternLayout.setOutputPatternAsHeader(outputPatternAsHeader);
		patternLayout.start();
		this.layout = patternLayout;
		super.start();
	}
	
	private Map<String, String> customerConverterMap() {
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("request", HttpRequestConverter.class.getName());
		map.put("job", JobNameConverter.class.getName());

		return map;
	}
}
