package edu.cmu.lti.oaqa.cse.space.uima;

import org.apache.uima.jcas.JCas;

import edu.cmu.lti.oaqa.components.Factory;
import edu.cmu.lti.oaqa.components.simple.SimpleFactory;
import edu.cmu.lti.oaqa.cse.configuration.Configuration;
import edu.cmu.lti.oaqa.cse.space.ConfigurationSpace;
import edu.cmu.lti.oaqa.cse.space.tree.Tree;

public class UimaConfigurationSpace extends ConfigurationSpace<JCas, UimaComponent> {

  private SimpleFactory sFactory;

  public UimaConfigurationSpace(Configuration conf) {
    super(conf);
    // TODO Auto-generated constructor stub
  }

  @Override
  protected Tree<UimaComponent> getTree() {
    return new Tree<UimaComponent>();
  }

  @Override
  protected Factory<JCas, UimaComponent> getFactory() {
    return new UimaFactory(conf);

  }

}
