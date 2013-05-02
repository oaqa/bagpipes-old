package edu.cmu.lti.oaqa.components;

import java.util.List;

import org.apache.uima.jcas.JCas;

import edu.cmu.lti.oaqa.cse.component.uima.UimaComponent;

public class Annotator extends UimaComponent {

	public Annotator(String className) {
		super(className);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<JCas> thisExecute(List<JCas> input) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getClassName() {
		// TODO Auto-generated method stub
		return className;
	}

	@Override
	protected List<JCas> cloneInput(List<JCas> input) {
		// TODO Auto-generated method stub
		return null;
	}


	
	
	
}
