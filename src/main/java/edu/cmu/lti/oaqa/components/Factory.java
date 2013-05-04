package edu.cmu.lti.oaqa.components;

import edu.cmu.lti.oaqa.cse.configuration.CollectionReaderDescriptor;
import edu.cmu.lti.oaqa.cse.configuration.ComponentDescriptor;
import edu.cmu.lti.oaqa.cse.configuration.ExplorerDescriptor;
import edu.cmu.lti.oaqa.cse.configuration.OptionDescriptor;
import edu.cmu.lti.oaqa.cse.space.exploration.ExplorationStrategy;
/**
 * 
 * 
 * 
 * @author Avner Maiberg (amaiberg@cs.cmu.edu)
 *
 * @param <I>
 * @param <E>
 */
public abstract class Factory<I, E extends ExecutableComponent<I>> {

	public abstract E createExecutableComponent(
			ComponentDescriptor compoentDescriptor) throws Exception;

	public abstract E createExecutableComponent(
			OptionDescriptor compoentDescriptor) throws Exception;

	public abstract E createExecutableComponent(
			CollectionReaderDescriptor compoentDescriptor) throws Exception;

	@SuppressWarnings("unchecked")
	public ExplorationStrategy<I, E> createStrategist(
			ExplorerDescriptor exploreDesc) {
		String className = exploreDesc.getClassName();
		ExplorationStrategy<I, E> strategy = null;
		try {
			System.out.println(className);
			return (ExplorationStrategy<I, E>) Class.forName(className)
					.getConstructor(ExplorerDescriptor.class)
					.newInstance(exploreDesc);
		} catch (ClassCastException e) {
			System.err.println("Class must be of type "
					+ this.getClass().getTypeParameters());
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return strategy;
	}

}
