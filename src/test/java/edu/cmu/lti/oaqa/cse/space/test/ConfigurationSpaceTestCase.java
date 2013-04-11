package edu.cmu.lti.oaqa.cse.space.test;


import edu.cmu.lti.oaqa.cse.configuration.Configuration;
import edu.cmu.lti.oaqa.cse.configuration.test.ConfigurationFactory;
import edu.cmu.lti.oaqa.cse.space.ConfigurationSpace;
import edu.cmu.lti.oaqa.cse.space.simple.SimpleConfigurationSpace;
import edu.cmu.lti.oaqa.cse.space.tree.Node;
import edu.cmu.lti.oaqa.cse.space.tree.Tree;
import edu.cmu.lti.oaqa.components.simple.*;

import org.junit.Test;

public class ConfigurationSpaceTestCase {

	private static ConfigurationFactory cFactory;
	private static final Configuration ex1Conf = cFactory.programmedConf;
	private static final ConfigurationSpace ex1SimpleSpace = initSimpleConfigurationSpace(ex1Conf);

	@Test
	public void phaseTreeTest() {
		Tree<Node<SimpleClassNameAnnotator>> simpleTree =ex1SimpleSpace.getPhaseTree();
		
		
		//System.out.println(simpleTree.toStringWithDepth());
	}

	private static SimpleConfigurationSpace initSimpleConfigurationSpace(
			Configuration config) {
		return new SimpleConfigurationSpace(config);
	}
	
	

}
