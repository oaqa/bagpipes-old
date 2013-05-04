package edu.cmu.lti.oaqa.cse.space.exploration.simple;

import edu.cmu.lti.oaqa.components.simple.SimpleClassNameAnnotator;
import edu.cmu.lti.oaqa.cse.configuration.ExplorerDescriptor;
import edu.cmu.lti.oaqa.cse.space.exploration.GreedyExplorationStrategy;

public class SimpleExplorationStrategy extends
		GreedyExplorationStrategy<String, SimpleClassNameAnnotator> {

  public SimpleExplorationStrategy(ExplorerDescriptor explorerDesc) {
    super(explorerDesc);
  }
}
