package edu.cmu.lti.oaqa.components.simple;

import edu.cmu.lti.oaqa.components.Factory;
import edu.cmu.lti.oaqa.cse.configuration.CollectionReaderDescriptor;
import edu.cmu.lti.oaqa.cse.configuration.ComponentDescriptor;
import edu.cmu.lti.oaqa.cse.configuration.Configuration;
import edu.cmu.lti.oaqa.cse.configuration.OptionDescriptor;

public class SimpleFactory extends Factory<String,SimpleClassNameAnnotator> {

	public SimpleFactory(Configuration config) {
		super(config);
	}

	@Override
	public SimpleClassNameAnnotator createExecutableComponent(
			ComponentDescriptor componentDescriptor) {
		String className = componentDescriptor.getClassName();
		return new SimpleClassNameAnnotator(className);
	}

  @Override
  public SimpleClassNameAnnotator createExecutableComponent(OptionDescriptor componentDescriptor) {
    // Just propagate to the implementing method
    return createExecutableComponent((ComponentDescriptor) componentDescriptor);
  }

  @Override
  public SimpleClassNameAnnotator createExecutableComponent(
          CollectionReaderDescriptor componentDescriptor) {
    // Just propagate to the implementing method
    return createExecutableComponent((ComponentDescriptor) componentDescriptor);
  }

	
}
