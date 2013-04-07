package edu.cmu.lti.oaqa.cse.configuration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public abstract class ComponentDescriptor {
	private String className;
	private Map<String, Parameter> paramMap;

	public ComponentDescriptor(String className) {
		this.className = className;
		paramMap = new HashMap<String, Parameter>();
	}

	public String getClassName() {
		return this.className;
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

	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}
		if (obj == null || obj.getClass() != this.getClass()) {
			return false;
		}

		return equals((ComponentDescriptor) obj);

	}

	public boolean equals(ComponentDescriptor cd) {
		for (String name : this.paramMap.keySet()) {
			Parameter thisParam = this.getParam(name);
			Parameter otherParam = cd.getParam(name);
			if (otherParam != null && thisParam.equals(otherParam))
				continue;
			else
				return false;
		}
		return true && this.className.equals(cd.className);
	}
	
	public String toString(){
		return "className: " + className + " "  + "Parameters:" +   paramMap + " ";
	}
}
