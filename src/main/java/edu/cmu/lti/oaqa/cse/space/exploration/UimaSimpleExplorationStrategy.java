package edu.cmu.lti.oaqa.cse.space.exploration;

import java.util.List;

import org.apache.uima.jcas.JCas;

import edu.cmu.lti.oaqa.cse.component.uima.UimaComponent;
import edu.cmu.lti.oaqa.cse.configuration.ExplorerDescriptor;

public class UimaSimpleExplorationStrategy extends
		TSimpleExplorationStrategy<List<JCas>, UimaComponent> {
	
	public UimaSimpleExplorationStrategy(ExplorerDescriptor explorerDesc){
		super(explorerDesc);
		
	}

}
