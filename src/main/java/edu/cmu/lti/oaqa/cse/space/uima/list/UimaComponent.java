package edu.cmu.lti.oaqa.cse.space.uima.list;

import java.util.List;

import org.apache.uima.jcas.JCas;

import edu.cmu.lti.oaqa.components.ExecutableComponent;

public abstract class UimaComponent extends ExecutableComponent<List<JCas>> {

	protected String className;

	public UimaComponent(String className) {
		this.className = className;
	}
	
	public String getClassName(){
		return className;
	}
	
	public String toString(){
		return className;
	}
	
	

}
