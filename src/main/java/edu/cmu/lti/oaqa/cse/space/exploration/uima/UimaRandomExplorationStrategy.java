package edu.cmu.lti.oaqa.cse.space.exploration.uima;

import org.apache.uima.jcas.JCas;

import edu.cmu.lti.oaqa.cse.configuration.ExplorerDescriptor;
import edu.cmu.lti.oaqa.cse.space.exploration.RandomExplorationStrategy;
import edu.cmu.lti.oaqa.cse.space.uima.UimaComponent;

public class UimaRandomExplorationStrategy extends
		RandomExplorationStrategy<JCas, UimaComponent> {

	public UimaRandomExplorationStrategy(ExplorerDescriptor explorerDesc){
		super(explorerDesc);
		
	}

}
