package edu.cmu.lti.oaqa.cse.component.uima;

import java.util.List;

import org.apache.uima.analysis_engine.AnalysisEngineDescription;
import org.apache.uima.collection.CollectionReader;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.metadata.TypeSystemDescription;
import org.uimafit.factory.TypeSystemDescriptionFactory;

import edu.cmu.lti.oaqa.components.Factory;
import edu.cmu.lti.oaqa.cse.configuration.CollectionReaderDescriptor;
import edu.cmu.lti.oaqa.cse.configuration.ComponentDescriptor;
import edu.cmu.lti.oaqa.cse.configuration.OptionDescriptor;
import edu.cmu.lti.oaqa.cse.configuration.PersistenceProviderDescriptor;

public class UimaFactory extends Factory<List<JCas>, UimaComponent> {

	private final ExperimentBuilder builder;

	public UimaFactory(String uuid) throws Exception {
		TypeSystemDescription typeSystem = TypeSystemDescriptionFactory
				.createTypeSystemDescription();
		this.builder = new ExperimentBuilder(uuid, "", typeSystem);
	}

	@Override
	public UimaComponent createExecutableComponent(
			ComponentDescriptor componentDescriptor) throws Exception {
		AnalysisEngineDescription desc = builder
				.buildComponent(componentDescriptor);
		return new AnalysisEngineComponent(desc,
				componentDescriptor.getClassName());
	}

	@Override
	public UimaComponent createExecutableComponent(
			OptionDescriptor componentDescriptor) throws Exception {
		AnalysisEngineDescription desc = builder
				.buildComponent(componentDescriptor);
		return new AnalysisEngineComponent(desc,
				componentDescriptor.getClassName());
	}

	@Override
	public UimaComponent createExecutableComponent(
			CollectionReaderDescriptor componentDescriptor) throws Exception {
		CollectionReader desc = builder
				.buildCollectionReader(componentDescriptor);
		return new CollectionReaderComponent(desc, builder.getExperimentPersistenceProvider(), builder.getExperimentUuid());
	}
	
	public void createPersistenceProvider(PersistenceProviderDescriptor ppd) throws Exception{
	   builder.initializePersistenceProvider(ppd);
	}

	/*
	 * @Override public ExplorationStrategy<JCas, UimaComponent>
	 * createStrategist( ExplorerDescriptor exploreDesc) { return new
	 * UimaSimpleExplorationStrategy(); }
	 */
}
