package edu.cmu.lti.oaqa.cse.space.test;

import org.apache.uima.jcas.JCas;
import org.junit.Test;

import edu.cmu.lti.oaqa.components.ExecutableComponent;
import edu.cmu.lti.oaqa.cse.configuration.Configuration;
import edu.cmu.lti.oaqa.cse.configuration.test.ConfigurationFactory;
import edu.cmu.lti.oaqa.cse.space.exploration.ExplorationStrategy;
import edu.cmu.lti.oaqa.cse.space.exploration.TSimpleExplorationStrategy;
import edu.cmu.lti.oaqa.cse.space.simple.SimpleConfigurationSpace;
import edu.cmu.lti.oaqa.cse.space.tree.NodeVisitor;
import edu.cmu.lti.oaqa.cse.space.uima.UimaComponent;
import edu.cmu.lti.oaqa.cse.space.uima.UimaConfigurationSpace;

public class UimaFactoryTest {

	private static final Configuration ex1Conf = ConfigurationFactory.programmedConfEx1;
	private static final ExplorationStrategy<JCas, UimaComponent> simpleStrategy = initSimpleExplorationStrategy();
	private static final UimaConfigurationSpace ex1SimpleSpace = initSimpleConfigurationSpace(ex1Conf);
		
	@Test
	public void phaseTreeTest() {
		System.out.println(ex1SimpleSpace.getPhaseTree());
		for (JCas c : ex1SimpleSpace)
			System.out.println(c);
	}
	
	private static UimaConfigurationSpace initSimpleConfigurationSpace(
			Configuration config) {
	  UimaConfigurationSpace space = new UimaConfigurationSpace(config);
		space.setExplorationStrategy(simpleStrategy);
		return space;
	}

	private static TSimpleExplorationStrategy<JCas, UimaComponent> initSimpleExplorationStrategy() {
		return new TSimpleExplorationStrategy<JCas, UimaComponent>();
	}

	private static NodeVisitor<String, ExecutableComponent<String>> initExecutingVisitor() {
		return null;
	}

}
