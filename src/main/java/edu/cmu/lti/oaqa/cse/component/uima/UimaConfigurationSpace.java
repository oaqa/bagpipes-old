package edu.cmu.lti.oaqa.cse.component.uima;

import java.util.List;

import org.apache.uima.jcas.JCas;

import edu.cmu.lti.oaqa.components.Factory;
import edu.cmu.lti.oaqa.cse.configuration.Configuration;
import edu.cmu.lti.oaqa.cse.space.ConfigurationSpace;
import edu.cmu.lti.oaqa.cse.space.tree.Tree;

public class UimaConfigurationSpace extends ConfigurationSpace<List<JCas>, UimaComponent> {

  public UimaConfigurationSpace(Configuration conf) throws Exception {
    super(conf);
    // TODO Auto-generated constructor stub
  }

  @Override
  protected Tree<UimaComponent> newTree() {
    return new Tree<UimaComponent>();
  }

  @Override
  protected Factory<List<JCas>, UimaComponent> getFactory() throws Exception {
    UimaFactory factory=  new UimaFactory();
    factory.createPersistenceProvider(conf.getPersistenceProviderDescriptor());
    return factory;
  }

}
