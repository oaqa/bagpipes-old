package edu.cmu.lti.oaqa.components.simple;

import edu.cmu.lti.oaqa.components.NamedComponent;

public class SimpleClassNameAnnotator extends NamedComponent<String> {

	public SimpleClassNameAnnotator(String className){
		super(className);
	}
	
	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String execute(String input) {
		return className;
	}
	
}
