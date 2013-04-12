package edu.cmu.lti.oaqa.cse.space.simple;

import edu.cmu.lti.oaqa.components.Factory;
import edu.cmu.lti.oaqa.components.simple.SimpleClassNameAnnotator;
import edu.cmu.lti.oaqa.components.simple.SimpleFactory;
import edu.cmu.lti.oaqa.cse.configuration.Configuration;
import edu.cmu.lti.oaqa.cse.space.ConfigurationSpace;
import edu.cmu.lti.oaqa.cse.space.tree.Tree;

public class SimpleConfigurationSpace extends
		ConfigurationSpace<String, SimpleClassNameAnnotator> {

	private SimpleFactory sFactory;

	public SimpleConfigurationSpace(Configuration conf) {
		super(conf);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected Tree<SimpleClassNameAnnotator> getTree() {
		return new Tree<SimpleClassNameAnnotator>();
	}

	@Override
	protected Factory<String, SimpleClassNameAnnotator> getFactory() {
		return new SimpleFactory(conf);

	}

}
