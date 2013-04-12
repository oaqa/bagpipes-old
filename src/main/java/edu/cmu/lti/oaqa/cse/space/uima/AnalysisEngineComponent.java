package edu.cmu.lti.oaqa.cse.space.uima;

import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.analysis_engine.AnalysisEngineDescription;
import org.apache.uima.jcas.JCas;
import org.uimafit.factory.AnalysisEngineFactory;

public class AnalysisEngineComponent extends UimaComponent {

  private final AnalysisEngineDescription descriptor;
  
	public AnalysisEngineComponent(AnalysisEngineDescription descriptor){
		super(descriptor.getMetaData().getName());
		this.descriptor = descriptor;
	}
	
	@Override
	public void init() {
		// TODO Auto-generated method stub
	}

	@Override
	public JCas execute(JCas input) throws Exception {
	  AnalysisEngine ae = AnalysisEngineFactory.createAggregate(descriptor);
	  ae.process(input);
		return input;
	}
	
}
