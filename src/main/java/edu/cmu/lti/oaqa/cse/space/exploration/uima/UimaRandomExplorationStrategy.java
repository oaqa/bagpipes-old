package edu.cmu.lti.oaqa.cse.space.exploration.uima;

import java.util.List;

import org.apache.uima.jcas.JCas;

import edu.cmu.lti.oaqa.cse.component.uima.UimaComponent;
import edu.cmu.lti.oaqa.cse.configuration.ExplorerDescriptor;
import edu.cmu.lti.oaqa.cse.space.exploration.RandomExplorationStrategy;

public class UimaRandomExplorationStrategy extends
		RandomExplorationStrategy<List<JCas>, UimaComponent> {

	public UimaRandomExplorationStrategy(ExplorerDescriptor explorerDesc){
		super(explorerDesc);
		
	}

}
