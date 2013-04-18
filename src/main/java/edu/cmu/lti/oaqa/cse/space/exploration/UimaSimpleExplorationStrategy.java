package edu.cmu.lti.oaqa.cse.space.exploration;

import java.util.Map;

import org.apache.uima.jcas.JCas;

import edu.cmu.lti.oaqa.cse.configuration.ExplorerDescriptor;
import edu.cmu.lti.oaqa.cse.space.uima.UimaComponent;

public class UimaSimpleExplorationStrategy extends
		TSimpleExplorationStrategy<JCas, UimaComponent> {
	
	public UimaSimpleExplorationStrategy(ExplorerDescriptor explorerDesc){
		super(explorerDesc);
		
	}

}
