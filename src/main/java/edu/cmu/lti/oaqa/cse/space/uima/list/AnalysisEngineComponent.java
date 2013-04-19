package edu.cmu.lti.oaqa.cse.space.uima.list;

import java.util.List;

import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.analysis_engine.AnalysisEngineDescription;
import org.apache.uima.jcas.JCas;
import org.uimafit.factory.AnalysisEngineFactory;

public class AnalysisEngineComponent extends UimaComponent {

  private final AnalysisEngineDescription descriptor;

  public AnalysisEngineComponent(AnalysisEngineDescription descriptor, String className) {
    super(className);
    this.descriptor = descriptor;
  }

  @Override
  public void init() {
    // TODO Auto-generated method stub
  }

  @Override
  public List<JCas> execute(List<JCas> input) throws Exception {
    for (JCas cas : input) {
      AnalysisEngine ae = AnalysisEngineFactory.createAggregate(descriptor);
      ae.process(cas);
    }
    return input;
  }

}
