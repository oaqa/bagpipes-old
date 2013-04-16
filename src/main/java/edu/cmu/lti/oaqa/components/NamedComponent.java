package edu.cmu.lti.oaqa.components;

import org.apache.uima.jcas.JCas;

import edu.cmu.lti.oaqa.components.ExecutableComponent;

public abstract class NamedComponent<I> extends ExecutableComponent<I> {

	protected String className;

	public NamedComponent(String className) {
		this.className = className;
	}
	
	public String toString(){
		return className;
	}
	@Override
	public String getClassName(){
		return className;
	}
	
	

}
