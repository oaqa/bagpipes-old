package edu.cmu.lti.oaqa.cse.space.uima;

import org.apache.uima.jcas.JCas;

import edu.cmu.lti.oaqa.components.ExecutableComponent;

public abstract class UimaComponent extends ExecutableComponent<JCas> {

	protected String className;

	public UimaComponent(String className) {
		this.className = className;
	}
	
	

}
