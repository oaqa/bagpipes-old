package edu.cmu.lti.oaqa.cse.space;

import org.apache.uima.resource.metadata.TypeSystemDescription;

import edu.cmu.lti.oaqa.components.ExecutableComponent;
import edu.cmu.lti.oaqa.components.Factory;
import edu.cmu.lti.oaqa.cse.configuration.Configuration;
import edu.cmu.lti.oaqa.cse.space.tree.Tree;

public class UIMAConfigurationSpace<A,B extends ExecutableComponent<A>> extends ConfigurationSpace<A,B> {

	private final TypeSystemDescription typeSystem = null;

	
	
	public UIMAConfigurationSpace(Configuration conf) throws Exception {
		super(conf);
		// TODO Auto-generated constructor stub
	}



	@Override
	protected Tree<B> getTree() {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	protected Factory<A, B> getFactory() {
		// TODO Auto-generated method stub
		return null;
	}

}
