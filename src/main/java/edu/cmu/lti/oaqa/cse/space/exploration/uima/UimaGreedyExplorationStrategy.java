package edu.cmu.lti.oaqa.cse.space.exploration.uima;


import org.apache.uima.jcas.JCas;

import edu.cmu.lti.oaqa.cse.configuration.ExplorerDescriptor;
import edu.cmu.lti.oaqa.cse.space.exploration.GreedyExplorationStrategy;
import edu.cmu.lti.oaqa.cse.space.uima.UimaComponent;

public class UimaGreedyExplorationStrategy extends
		GreedyExplorationStrategy<JCas, UimaComponent> {
	
	public UimaGreedyExplorationStrategy(ExplorerDescriptor explorerDesc){
		super(explorerDesc);
		
	}

}