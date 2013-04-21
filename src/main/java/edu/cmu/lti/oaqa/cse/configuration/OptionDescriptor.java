package edu.cmu.lti.oaqa.cse.configuration;

import java.util.Map;

/**
 * An object encompassing a single component of a phase within a
 * pipeline, i.e. an option of a phase.
 */
public final class OptionDescriptor extends ComponentDescriptor {

	public OptionDescriptor(String className) {
		super(className);
	}
	
	public OptionDescriptor(String className,Map<String,Object> resMap) {
		super(className,resMap);
	}
	
	
	public String toString(){
		return "Class: " + className + "\nParams: " + paramMap;
	}

	
	
	
	

}
