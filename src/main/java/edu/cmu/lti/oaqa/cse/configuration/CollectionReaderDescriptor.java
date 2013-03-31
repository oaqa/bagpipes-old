package edu.cmu.lti.oaqa.cse.configuration;

import java.util.Map;

public class CollectionReaderDescriptor extends ComponentDescriptor {

	public CollectionReaderDescriptor(String className) {
		super(className);
	}
	
	public CollectionReaderDescriptor(String className,Map<String,Object> paramMap) {
		super(className,paramMap);
	}
	
	
}
