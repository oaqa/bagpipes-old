package edu.cmu.lti.oaqa.cse.configuration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.common.collect.Maps;

public abstract class ComponentDescriptor implements Paramable {
	protected String className;
	protected Map<String, Parameter> paramMap;

	protected Map<String, IntegerParameter> iParamMap;
	protected Map<String, DoubleParameter> dParamMap;
	protected Map<String, StringParameter> sParamMap;

	public ComponentDescriptor(String className) {
		this.className = className;
		paramMap = Maps.newHashMap();
		iParamMap = Maps.newHashMap();
		dParamMap = Maps.newHashMap();
		sParamMap = Maps.newHashMap();
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
			Object value = paramMap.get(key);
			addParam(new Parameter(key, value));
			addToTypedMap(key,value);
		}

	}

	public void addToTypedMap(String key, Object value) {
		if (value instanceof Integer)
			iParamMap.put(key, Parameter.newParameter(key, (Integer) value));
		if (value instanceof Double)
			dParamMap.put(key, Parameter.newParameter(key, (Double) value));
		if (value instanceof String)
			addToParams(key, (String) value);
	}

	public void addParam(Parameter param) {
		paramMap.put(param.getName(), param);
	}

	public Parameter getParam(String name) {
		return paramMap.get(name);
	}

	public Map<String, Parameter> getParamMap() {
		return Maps.newHashMap(paramMap);
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
		return this.className.equals(cd.className);
	}

	public String toString() {
		return "className: " + className + " " + "Parameters:" + paramMap + " ";
	}

	public void addToParams(String name, Integer i) {
		iParamMap.put(name, Parameter.newParameter(name, i));
	};

	public void addToParams(String name, Double d) {
		dParamMap.put(name, Parameter.newParameter(name, d));
	}

	public void addToParams(String name, String s) {
		sParamMap.put(name, Parameter.newParameter(name, s));
	}

	public Integer getInteger(String key) {
		return iParamMap.get(key).getVal();
	}

	public Double getDouble(String key) {
		return dParamMap.get(key).getVal();
	}

	public String getString(String key) {
		return sParamMap.get(key).getVal();
	}

}
