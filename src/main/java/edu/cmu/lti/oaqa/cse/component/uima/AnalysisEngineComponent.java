package edu.cmu.lti.oaqa.cse.component.uima;

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
  public List<JCas> cloneInput(List<JCas> input){
			List<JCas> newList = Lists.newArrayList();
			for (JCas cas : input){
				CAS newCas = null;
				try {
					newCas = CasCreationUtils.createCas(Arrays.asList(descriptor.getMetaData()));
				} catch (ResourceInitializationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				CasCopier.copyCas(cas.getCas(), newCas, true);
				try {
					newList.add(newCas.getJCas());
				} catch (CASException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return newList;
		}
  }

  
  



