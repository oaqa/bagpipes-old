package edu.cmu.lti.oaqa.components;

public abstract class UIMAComponent<I> extends ExecutableComponent<I> {

	protected String className;

	public UIMAComponent(String className) {
		this.className = className;
	}
	
	

}
