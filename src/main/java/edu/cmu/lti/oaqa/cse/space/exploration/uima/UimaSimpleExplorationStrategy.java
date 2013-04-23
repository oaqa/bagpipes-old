package edu.cmu.lti.oaqa.cse.space.exploration.uima;

import java.util.List;

import org.apache.uima.jcas.JCas;

import edu.cmu.lti.oaqa.cse.configuration.ExplorerDescriptor;
import edu.cmu.lti.oaqa.cse.space.exploration.TSimpleExplorationStrategy;
import edu.cmu.lti.oaqa.cse.space.uima.list.UimaComponent;

public class UimaSimpleExplorationStrategy extends
		TSimpleExplorationStrategy<List<JCas>, UimaComponent> {
	
	public UimaSimpleExplorationStrategy(ExplorerDescriptor explorerDesc){
		super(explorerDesc);
		
	}

}
