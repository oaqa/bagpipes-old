package edu.cmu.lti.oaqa.cse.space.uima.list;

import java.util.Arrays;
import java.util.List;

import org.apache.uima.analysis_engine.AnalysisEngineDescription;
import org.apache.uima.cas.CAS;
import org.apache.uima.cas.CASException;
import org.apache.uima.collection.CollectionReader;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.resource.metadata.TypeSystemDescription;
import org.apache.uima.util.CasCopier;
import org.apache.uima.util.CasCreationUtils;
import org.uimafit.component.CasMultiplier_ImplBase;
import org.uimafit.factory.TypeSystemDescriptionFactory;

import com.google.common.collect.Lists;

import edu.cmu.lti.oaqa.components.Factory;
import edu.cmu.lti.oaqa.cse.configuration.CollectionReaderDescriptor;
import edu.cmu.lti.oaqa.cse.configuration.ComponentDescriptor;
import edu.cmu.lti.oaqa.cse.configuration.Configuration;
import edu.cmu.lti.oaqa.cse.configuration.OptionDescriptor;
import edu.cmu.lti.oaqa.ecd.util.CasUtils;

public class UimaFactory extends Factory<List<JCas>, UimaComponent> {

	private final ExperimentBuilder builder;

	public UimaFactory(Configuration config) throws Exception {
		super(config);
		TypeSystemDescription typeSystem = TypeSystemDescriptionFactory
				.createTypeSystemDescription();
		this.builder = new ExperimentBuilder("", "", typeSystem);
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
		return new CollectionReaderComponent(desc);
	}




	/*
	 * @Override public ExplorationStrategy<JCas, UimaComponent>
	 * createStrategist( ExplorerDescriptor exploreDesc) { return new
	 * UimaSimpleExplorationStrategy(); }
	 */
}
