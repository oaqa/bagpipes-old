package edu.cmu.lti.oaqa.components.uima;

import java.util.List;

import org.apache.uima.jcas.JCas;
import org.junit.BeforeClass;
import org.junit.Test;

import edu.cmu.lti.oaqa.components.ExecutableComponent;
import edu.cmu.lti.oaqa.cse.component.uima.UimaComponent;
import edu.cmu.lti.oaqa.cse.component.uima.UimaConfigurationSpace;
import edu.cmu.lti.oaqa.cse.configuration.Configuration;
import edu.cmu.lti.oaqa.cse.configuration.Parser;
import edu.cmu.lti.oaqa.cse.configuration.YAMLParser;
import edu.cmu.lti.oaqa.cse.space.exploration.ExplorationStrategy;
import edu.cmu.lti.oaqa.cse.space.exploration.ThresholdExplorationStrategy;
import edu.cmu.lti.oaqa.cse.space.tree.NodeVisitor;

public class UimaFactoryTest {

  private static ExplorationStrategy<List<JCas>, UimaComponent> simpleStrategy;

  private static UimaConfigurationSpace ex1SimpleSpace;

 
  @Test
 public void phaseTreeTest() {
//    System.out.println(ex1SimpleSpace.getPhaseTree());
//    for (List<JCas> c : ex1SimpleSpace);
////      //System.out.println(c);
    }

  @BeforeClass
  public static void initSimpleConfigurationSpace() throws Exception {
    
    Configuration ex1Conf = parse("oaqa-tutorial-ex4");
    UimaConfigurationSpace space = new UimaConfigurationSpace(ex1Conf);
    ex1SimpleSpace = space;
  }

  
  private static Configuration parse(String resource){
		Parser p = new YAMLParser(resource + ".yaml");
		Configuration conf = p.parse();
		return conf;
	}

}
