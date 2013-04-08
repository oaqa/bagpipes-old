package edu.cmu.lti.oaqa.cse.configuration;

import java.util.Map;


public final class ConsumerDescriptor extends ComponentDescriptor {

	public ConsumerDescriptor(String className) {
		super(className);
	}
	public ConsumerDescriptor(String className, Map<String, Object> paramMap) {
		super(className,paramMap);
	}
	
}
