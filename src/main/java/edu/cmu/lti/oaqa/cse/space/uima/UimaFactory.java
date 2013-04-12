package edu.cmu.lti.oaqa.cse.space.uima;

import org.apache.uima.jcas.JCas;

import edu.cmu.lti.oaqa.components.Factory;
import edu.cmu.lti.oaqa.cse.configuration.ComponentDescriptor;
import edu.cmu.lti.oaqa.cse.configuration.Configuration;

public class UimaFactory extends Factory<JCas, UimaComponent> {

	public UimaFactory(Configuration config) {
		super(config);
	}

	@Override
	public UimaComponent createExecutableComponent(
			ComponentDescriptor componentDescriptor) {
		String className = componentDescriptor.getClassName();
		return new SimpleUimaComponent(className);
	}

	
}
