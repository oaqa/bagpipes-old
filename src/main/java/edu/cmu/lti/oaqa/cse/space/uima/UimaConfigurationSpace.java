package edu.cmu.lti.oaqa.cse.space.uima;

import org.apache.uima.jcas.JCas;

import edu.cmu.lti.oaqa.components.Factory;
import edu.cmu.lti.oaqa.cse.configuration.Configuration;
import edu.cmu.lti.oaqa.cse.space.ConfigurationSpace;
import edu.cmu.lti.oaqa.cse.space.tree.Tree;

public class UimaConfigurationSpace extends ConfigurationSpace<JCas, UimaComponent> {

  public UimaConfigurationSpace(Configuration conf) throws Exception {
    super(conf);
    // TODO Auto-generated constructor stub
  }

  @Override
  protected Tree<UimaComponent> newTree() {
    return new Tree<UimaComponent>();
  }

  @Override
  protected Factory<JCas, UimaComponent> getFactory() throws Exception {
    return new UimaFactory(conf);
  }

}
