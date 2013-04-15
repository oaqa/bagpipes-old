package edu.cmu.lti.oaqa.cse.space.test;

import org.junit.Test;

import edu.cmu.lti.oaqa.components.ExecutableComponent;
import edu.cmu.lti.oaqa.components.simple.SimpleClassNameAnnotator;
import edu.cmu.lti.oaqa.cse.configuration.Configuration;
import edu.cmu.lti.oaqa.cse.configuration.test.ConfigurationFactory;
import edu.cmu.lti.oaqa.cse.space.ConfigurationSpace;
import edu.cmu.lti.oaqa.cse.space.exploration.ExplorationStrategy;
import edu.cmu.lti.oaqa.cse.space.exploration.SimpleExplorationStrategy;
import edu.cmu.lti.oaqa.cse.space.simple.SimpleConfigurationSpace;
import edu.cmu.lti.oaqa.cse.space.tree.NodeVisitor;

public class ConfigurationSpaceTestCase {

	private static ConfigurationFactory cFactory;
	private static final Configuration ex1Conf = cFactory.programmedConfEx1;
	private static final Configuration ex4Conf = cFactory.programmedConfEx4;
	private static final ExplorationStrategy<String, SimpleClassNameAnnotator> simpleStrategyEx1 = initSimpleExplorationStrategy();
	private static final ExplorationStrategy<String, SimpleClassNameAnnotator> simpleStrategyEx4 = initSimpleExplorationStrategy();
	private static final ConfigurationSpace<String, SimpleClassNameAnnotator> ex1SimpleSpace = initSimpleConfigurationSpace(
			ex1Conf, simpleStrategyEx1);
	private static final ConfigurationSpace<String, SimpleClassNameAnnotator> ex4SimpleSpace = initSimpleConfigurationSpace(
			ex4Conf, simpleStrategyEx4);
	private static final NodeVisitor<String, ExecutableComponent<String>> executingNodeVisitor = initExecutingVisitor();

	@Test
	public void phaseTreeTest() {

		System.out
				.println("ex1 phase tree:\n " + ex1SimpleSpace.getPhaseTree());
		for (String c : ex1SimpleSpace)
			System.out.println(c);

		System.out.println("");
		System.out.flush();
		System.out
				.println("ex4 phase tree: \n" + ex4SimpleSpace.getPhaseTree());
		for (String c : ex4SimpleSpace)
			System.out.println(c);
	}

	private static SimpleConfigurationSpace initSimpleConfigurationSpace(
			Configuration config, ExplorationStrategy strategy) {
		SimpleConfigurationSpace space;
		try {
			space = new SimpleConfigurationSpace(config);
			space.setExplorationStrategy(strategy);
			return space;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private static SimpleExplorationStrategy initSimpleExplorationStrategy() {
		return new SimpleExplorationStrategy();
	}

	private static NodeVisitor<String, ExecutableComponent<String>> initExecutingVisitor() {
		return null;
	}

}
