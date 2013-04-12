package edu.cmu.lti.oaqa.components.uima;

import java.util.Collections;

import org.apache.uima.cas.CAS;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.util.CasCreationUtils;
import org.junit.BeforeClass;
import org.junit.Test;

import edu.cmu.lti.oaqa.components.ExecutableComponent;
import edu.cmu.lti.oaqa.cse.configuration.Configuration;
import edu.cmu.lti.oaqa.cse.configuration.test.ConfigurationFactory;
import edu.cmu.lti.oaqa.cse.space.exploration.ExplorationStrategy;
import edu.cmu.lti.oaqa.cse.space.exploration.TSimpleExplorationStrategy;
import edu.cmu.lti.oaqa.cse.space.tree.NodeVisitor;
import edu.cmu.lti.oaqa.cse.space.uima.UimaComponent;
import edu.cmu.lti.oaqa.cse.space.uima.UimaConfigurationSpace;

public class UimaFactoryTest {

  private static Configuration ex1Conf;

  private static ExplorationStrategy<JCas, UimaComponent> simpleStrategy;

  private static UimaConfigurationSpace ex1SimpleSpace;

  
  @Test
  public void phaseTreeTest() {
    System.out.println(ex1SimpleSpace.getPhaseTree());
    for (JCas c : ex1SimpleSpace)
      System.out.println(c);
  }

  @BeforeClass
  public static void initSimpleConfigurationSpace() throws Exception {
    ex1Conf = ConfigurationFactory.programmedConfEx1;
    UimaConfigurationSpace space = new UimaConfigurationSpace(ex1Conf);
    simpleStrategy = initSimpleExplorationStrategy();
    space.setExplorationStrategy(simpleStrategy);
    ex1SimpleSpace = space;
  }

  private static TSimpleExplorationStrategy<JCas, UimaComponent> initSimpleExplorationStrategy() throws Exception {
    CAS cas = CasCreationUtils.createCas(Collections.EMPTY_LIST);
    return new TSimpleExplorationStrategy<JCas, UimaComponent>(cas.getJCas());
  }

  private static NodeVisitor<String, ExecutableComponent<String>> initExecutingVisitor() {
    return null;
  }

}
