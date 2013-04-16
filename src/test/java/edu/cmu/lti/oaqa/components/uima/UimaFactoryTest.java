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
import edu.cmu.lti.oaqa.cse.configuration.Parser;
import edu.cmu.lti.oaqa.cse.configuration.YAMLParser;
import edu.cmu.lti.oaqa.cse.configuration.test.ConfigurationFactory;
import edu.cmu.lti.oaqa.cse.space.exploration.ExplorationStrategy;
import edu.cmu.lti.oaqa.cse.space.exploration.TSimpleExplorationStrategy;
import edu.cmu.lti.oaqa.cse.space.tree.NodeVisitor;
import edu.cmu.lti.oaqa.cse.space.uima.UimaComponent;
import edu.cmu.lti.oaqa.cse.space.uima.UimaConfigurationSpace;

public class UimaFactoryTest {

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
    Configuration ex1Conf = parse("oaqa-tutorial-ex1");
    UimaConfigurationSpace space = new UimaConfigurationSpace(ex1Conf);
    simpleStrategy = initSimpleExplorationStrategy();
    space.setExplorationStrategy(simpleStrategy);
    ex1SimpleSpace = space;
  }

  private static TSimpleExplorationStrategy<JCas, UimaComponent> initSimpleExplorationStrategy() throws Exception {
    return new TSimpleExplorationStrategy<JCas, UimaComponent>();
  }

  private static NodeVisitor<String, ExecutableComponent<String>> initExecutingVisitor() {
    return null;
  }
  
  private static Configuration parse(String resource){
		Parser p = new YAMLParser(resource + ".yaml");
		Configuration conf = p.parse();
		return conf;
	}

}
