package edu.cmu.lti.oaqa.components;

import edu.cmu.lti.oaqa.cse.configuration.ComponentDescriptor;
import edu.cmu.lti.oaqa.cse.configuration.Configuration;

public abstract class Factory<I,E extends ExecutableComponent<I>> {

	private Configuration config;

	public Factory(Configuration config) {
		this.config = config;

	}
	
	public abstract E createExecutableComponent(ComponentDescriptor compoentDescriptor);

}
