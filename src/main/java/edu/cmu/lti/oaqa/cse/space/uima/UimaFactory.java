package edu.cmu.lti.oaqa.cse.space.uima;

import org.apache.uima.analysis_engine.AnalysisEngineDescription;
import org.apache.uima.collection.CollectionReader;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.metadata.TypeSystemDescription;
import org.uimafit.factory.TypeSystemDescriptionFactory;

import edu.cmu.lti.oaqa.components.Factory;
import edu.cmu.lti.oaqa.cse.configuration.CollectionReaderDescriptor;
import edu.cmu.lti.oaqa.cse.configuration.ComponentDescriptor;
import edu.cmu.lti.oaqa.cse.configuration.Configuration;
import edu.cmu.lti.oaqa.cse.configuration.OptionDescriptor;

public class UimaFactory extends Factory<JCas, UimaComponent> {

  private final ExperimentBuilder builder;
  
  public UimaFactory(Configuration config) throws Exception {
    super(config);
    TypeSystemDescription typeSystem = TypeSystemDescriptionFactory.createTypeSystemDescription();
    this.builder = new ExperimentBuilder("", "", typeSystem);
  }

  @Override
  public UimaComponent createExecutableComponent(ComponentDescriptor componentDescriptor) throws Exception {
    AnalysisEngineDescription desc = builder.buildComponent(componentDescriptor);
    return new AnalysisEngineComponent(desc);
  }

  @Override
  public UimaComponent createExecutableComponent(OptionDescriptor componentDescriptor) throws Exception {
    AnalysisEngineDescription desc = builder.buildComponent(componentDescriptor);
    return new AnalysisEngineComponent(desc);
  }

  @Override
  public UimaComponent createExecutableComponent(CollectionReaderDescriptor componentDescriptor) throws Exception {
    CollectionReader desc = builder.buildCollectionReader(componentDescriptor);
    return new CollectionReaderComponent(desc);
  }

}
