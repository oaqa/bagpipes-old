package edu.cmu.lti.oaqa.cse.configuration;

import java.util.HashMap;
import java.util.Map;

public abstract class ComponentDescriptor {
	private String className;
	private Map<String, Parameter> paramMap;

	
	public ComponentDescriptor(String className) {
		this.className = className;
		paramMap = new HashMap<String, Parameter>();
	}

	/*
	 * public ComponentDescriptor(String className, Map<String, Parameter>
	 * paramMap) { this(className); this.paramMap = paramMap; }
	 */
	public ComponentDescriptor(String className, Map<String, Object> paramMap) {
		this(className);
		for (String key : paramMap.keySet()) {
			addParam(new Parameter(key, paramMap.get(key)));
		}

	}


	
	public void addParam(Parameter param) {
		paramMap.put(param.getName(), param);
	}

	public Parameter getParam(String name) {
		return paramMap.get(name);
	}

}
