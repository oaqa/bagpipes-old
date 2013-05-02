/**
 * 
 */
package edu.cmu.lti.oaqa.cse.space.exploration.uima;

import java.util.List;

import org.apache.uima.jcas.JCas;

import edu.cmu.lti.oaqa.cse.component.uima.UimaComponent;
import edu.cmu.lti.oaqa.cse.configuration.ExplorerDescriptor;
import edu.cmu.lti.oaqa.cse.space.exploration.ThresholdExplorationStrategy;

/**
 * @author "Avner Maiberg (amaiberg.cs.cmu.edu)"
 *
 */
public class UimaThresholdExplorationStrategist extends
		ThresholdExplorationStrategy<List<JCas>, UimaComponent> {

	public UimaThresholdExplorationStrategist(ExplorerDescriptor expl){
		super(expl);
	}
	
}
