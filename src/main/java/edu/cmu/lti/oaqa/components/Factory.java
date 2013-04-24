package edu.cmu.lti.oaqa.components;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import edu.cmu.lti.oaqa.cse.configuration.CollectionReaderDescriptor;
import edu.cmu.lti.oaqa.cse.configuration.ComponentDescriptor;
import edu.cmu.lti.oaqa.cse.configuration.Configuration;
import edu.cmu.lti.oaqa.cse.configuration.ExplorerDescriptor;
import edu.cmu.lti.oaqa.cse.configuration.OptionDescriptor;
import edu.cmu.lti.oaqa.cse.space.exploration.ExplorationStrategy;

public abstract class Factory<I, E extends ExecutableComponent<I>> {

	private Configuration config;

	public Factory(Configuration config) {
		this.config = config;

	}

	public abstract E createExecutableComponent(
			ComponentDescriptor compoentDescriptor) throws Exception;

	public abstract E createExecutableComponent(
			OptionDescriptor compoentDescriptor) throws Exception;

	public abstract E createExecutableComponent(
			CollectionReaderDescriptor compoentDescriptor) throws Exception;


	public ExplorationStrategy<I, E> createStrategist(
			ExplorerDescriptor exploreDesc) {
		String className = exploreDesc.getClassName();
		ExplorationStrategy<I, E> strategy = null;
		try {
			System.out.println(className);
			return (ExplorationStrategy<I, E>) Class.forName(className).getConstructor(ExplorerDescriptor.class).newInstance(exploreDesc);
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassCastException e) {
			System.err.println("Class must be of type +"
					+ this.getClass().getTypeParameters());
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return strategy;
	}

}
