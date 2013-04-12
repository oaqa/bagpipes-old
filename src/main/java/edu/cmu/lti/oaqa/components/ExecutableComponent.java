package edu.cmu.lti.oaqa.components;

public abstract class ExecutableComponent<I> {
	
	public abstract void init();
	public abstract I execute(I input) throws Exception;
	
	

}
