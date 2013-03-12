package edu.cmu.lti.oaqa.cse;

public interface Experiment {

  String getExperimentUuid();

  Configuration getConfiguration();

  ExplorationStrategy getExplorationStrategy();

  <T> Executor<T> getExecutor();

}