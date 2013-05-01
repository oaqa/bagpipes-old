package edu.cmu.lti.oaqa.cse.space.uima.list;

import java.util.Arrays;
import java.util.List;

import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.analysis_engine.AnalysisEngineDescription;
import org.apache.uima.cas.CAS;
import org.apache.uima.cas.CASException;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.util.CasCopier;
import org.apache.uima.util.CasCreationUtils;
import org.uimafit.factory.AnalysisEngineFactory;

import com.google.common.collect.Lists;

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
  public List<JCas> thisExecute(List<JCas> input) throws Exception {
    AnalysisEngine ae = AnalysisEngineFactory.createAggregate(descriptor);
    for (JCas cas : input) {
      ae.process(cas);
    }
    return input;
  }

  @Override
  public List<JCas> cloneInput(List<JCas> input) {
    List<JCas> newList = Lists.newArrayList();
    for (JCas cas : input) {
      try {
        CAS newCas = CasCreationUtils.createCas(Arrays.asList(descriptor.getMetaData()));
        CasCopier.copyCas(cas.getCas(), newCas, true);
        newList.add(newCas.getJCas());
      } catch (Exception e) {
        e.printStackTrace();
      }
      // TODO: It remains unclear when should the CAS be released as it is dependent 
      // on the exploration strategy
      // cas.release();
    }
    return newList;
  }
}
