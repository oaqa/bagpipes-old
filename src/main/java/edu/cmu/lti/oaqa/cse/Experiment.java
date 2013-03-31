package edu.cmu.lti.oaqa.cse;

import edu.cmu.lti.oaqa.cse.configuration.Configuration;

public interface Experiment {

  String getExperimentUuid();

  Configuration getConfiguration();

  ExplorationStrategy getExplorationStrategy();

  <T> Executor<T> getExecutor();

}