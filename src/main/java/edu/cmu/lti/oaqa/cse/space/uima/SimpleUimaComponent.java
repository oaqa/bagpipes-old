package edu.cmu.lti.oaqa.cse.space.uima;

import org.apache.uima.jcas.JCas;

public class SimpleUimaComponent extends UimaComponent {

	public SimpleUimaComponent(String className){
		super(className);
	}
	
	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public JCas execute(JCas input) {
		return null;
	}
	
}
