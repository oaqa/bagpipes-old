package edu.cmu.lti.oaqa.cse.space.uima;

import org.apache.uima.jcas.JCas;

public class FakeUimaComponent extends UimaComponent {

	public FakeUimaComponent(String className){
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
