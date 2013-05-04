package edu.cmu.lti.oaqa.cse.space.exploration.uima;

import java.util.List;

import org.apache.uima.jcas.JCas;

import edu.cmu.lti.oaqa.cse.component.uima.UimaComponent;
import edu.cmu.lti.oaqa.cse.configuration.ExplorerDescriptor;
import edu.cmu.lti.oaqa.cse.space.exploration.GreedyExplorationStrategy;

public class UimaGreedyExplorationStrategy extends
		GreedyExplorationStrategy<List<JCas>, UimaComponent> {
	
	public UimaGreedyExplorationStrategy(ExplorerDescriptor explorerDesc){
		super(explorerDesc);
		
	}

}