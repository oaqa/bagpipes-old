package edu.cmu.lti.oaqa.components;

import edu.cmu.lti.oaqa.cse.configuration.CollectionReaderDescriptor;
import edu.cmu.lti.oaqa.cse.configuration.ComponentDescriptor;
import edu.cmu.lti.oaqa.cse.configuration.Configuration;
import edu.cmu.lti.oaqa.cse.configuration.OptionDescriptor;

public abstract class Factory<I,E extends ExecutableComponent<I>> {

	private Configuration config;

	public Factory(Configuration config) {
		this.config = config;

	}

  public abstract E createExecutableComponent(ComponentDescriptor compoentDescriptor) throws Exception;
  
  public abstract E createExecutableComponent(OptionDescriptor compoentDescriptor) throws Exception;
  
  public abstract E createExecutableComponent(CollectionReaderDescriptor compoentDescriptor);

}
