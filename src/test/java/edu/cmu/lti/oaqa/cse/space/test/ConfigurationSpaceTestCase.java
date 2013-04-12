package edu.cmu.lti.oaqa.cse.space.test;

import java.util.List;

import edu.cmu.lti.oaqa.cse.configuration.Configuration;
import edu.cmu.lti.oaqa.cse.configuration.test.ConfigurationFactory;
import edu.cmu.lti.oaqa.cse.space.ConfigurationSpace;
import edu.cmu.lti.oaqa.cse.space.exploration.ExplorationStrategy;
import edu.cmu.lti.oaqa.cse.space.exploration.SimpleExplorationStrategy;
import edu.cmu.lti.oaqa.cse.space.simple.SimpleConfigurationSpace;
import edu.cmu.lti.oaqa.cse.space.tree.Node;
import edu.cmu.lti.oaqa.cse.space.tree.NodeVisitor;
import edu.cmu.lti.oaqa.cse.space.tree.Tree;
import edu.cmu.lti.oaqa.components.simple.*;
import edu.cmu.lti.oaqa.components.ExecutableComponent;

import org.junit.Test;

import com.google.common.collect.Lists;

public class ConfigurationSpaceTestCase {

	private static ConfigurationFactory cFactory;
	private static final Configuration ex1Conf = cFactory.programmedConfEx1;
	private static final ExplorationStrategy<String, SimpleClassNameAnnotator> simpleStrategy = initSimpleExplorationStrategy();
	private static final ConfigurationSpace<String, SimpleClassNameAnnotator> ex1SimpleSpace = initSimpleConfigurationSpace(ex1Conf);
	private static final NodeVisitor<String, ExecutableComponent<String>> executingNodeVisitor = initExecutingVisitor();
	
	@Test
	public void phaseTreeTest() {
		System.out.println(ex1SimpleSpace.getPhaseTree());
		for (String c : ex1SimpleSpace)
			System.out.println(c);
	}
	
	private static SimpleConfigurationSpace initSimpleConfigurationSpace(
			Configuration config) {
		SimpleConfigurationSpace space = new SimpleConfigurationSpace(config);
		space.setExplorationStrategy(simpleStrategy);
		return space;
	}

	private static SimpleExplorationStrategy initSimpleExplorationStrategy() {
		return new SimpleExplorationStrategy();
	}

	private static NodeVisitor<String, ExecutableComponent<String>> initExecutingVisitor() {
		return null;
	}

}
