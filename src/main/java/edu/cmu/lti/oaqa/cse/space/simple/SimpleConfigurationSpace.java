package edu.cmu.lti.oaqa.cse.space.simple;

import edu.cmu.lti.oaqa.components.simple.SimpleClassNameAnnotator;
import edu.cmu.lti.oaqa.components.simple.SimpleFactory;
import edu.cmu.lti.oaqa.cse.configuration.Configuration;
import edu.cmu.lti.oaqa.cse.space.ConfigurationSpace;
import edu.cmu.lti.oaqa.cse.space.tree.Tree;

public class SimpleConfigurationSpace extends
		ConfigurationSpace<String, SimpleClassNameAnnotator> {

	public SimpleConfigurationSpace(Configuration conf) throws Exception {
		super(conf, new SimpleFactory());
		// TODO Auto-generated constructor stub
	}

	@Override
	protected Tree<SimpleClassNameAnnotator> newTree() {
		return new Tree<SimpleClassNameAnnotator>();
	}
}
