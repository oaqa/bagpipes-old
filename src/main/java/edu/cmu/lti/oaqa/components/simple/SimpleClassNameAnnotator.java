package edu.cmu.lti.oaqa.components.simple;

import edu.cmu.lti.oaqa.components.UIMAComponent;

public class SimpleClassNameAnnotator extends UIMAComponent<String> {

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
