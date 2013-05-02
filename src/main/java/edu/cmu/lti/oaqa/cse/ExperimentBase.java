package edu.cmu.lti.oaqa.cse;

import java.io.IOException;

import org.apache.uima.resource.ResourceInitializationException;

import mx.bigdata.anyobject.AnyObject;
import edu.cmu.lti.oaqa.cse.configuration.Configuration;
import edu.cmu.lti.oaqa.cse.space.exploration.ExplorationStrategy;
import edu.cmu.lti.oaqa.cse.space.exploration.SingleTraceGreedyExplorationStrategy;
import edu.cmu.lti.oaqa.ecd.config.ConfigurationLoader;
import edu.cmu.lti.oaqa.ecd.impl.AbstractExperimentPersistenceProvider;
import edu.cmu.lti.oaqa.ecd.impl.DefaultExperimentPersistenceProvider;

public class ExperimentBase implements Experiment {
  private final String experimentUuid;

  private final AnyObject configuration;

  private final AbstractExperimentPersistenceProvider persistence;
  
  public ExperimentBase(String experimentUuid, String resource) throws Exception {
    this.experimentUuid = experimentUuid;
    this.configuration = ConfigurationLoader.load(resource);
    this.persistence = newPersistenceProvider(configuration);
    insertExperiment(configuration, resource);
  }

  /* (non-Javadoc)
   * @see edu.cmu.lti.oaqa.cse.Experiment#getExperimentUuid()
   */
  @Override
  public String getExperimentUuid() {
    return experimentUuid;
  }
  
  private AbstractExperimentPersistenceProvider newPersistenceProvider(AnyObject config)
          throws ResourceInitializationException {
    AnyObject pprovider = config.getAnyObject("persistence-provider");
    if (pprovider == null) {
      return new DefaultExperimentPersistenceProvider();
    }
    try {
      return ResourceHelper.initializeResource(config, "persistence-provider", AbstractExperimentPersistenceProvider.class);
    } catch (Exception e) {
      throw new ResourceInitializationException(
              ResourceInitializationException.ERROR_INITIALIZING_FROM_DESCRIPTOR, new Object[] {
                  "persistence-provider", config }, e);
    }
  }

  private void insertExperiment(AnyObject config, String resource) throws Exception {
    AnyObject experiment = config.getAnyObject("configuration");
    String name = experiment.getString("name");
    String author = experiment.getString("author");
    persistence.insertExperiment(getExperimentUuid(), name, author,
            ConfigurationLoader.getString(resource), resource);
  }

  /* (non-Javadoc)
   * @see edu.cmu.lti.oaqa.cse.Experiment#getPipeline()
   */
  @Override
  public Configuration getConfiguration() {
    // TODO Auto-generated method stub
    return null;
  }

  /* (non-Javadoc)
   * @see edu.cmu.lti.oaqa.cse.Experiment#getExplorationStrategy()
   */
  @Override
  public ExplorationStrategy getExplorationStrategy() {
    ExplorationStrategy ps; 
    AnyObject map = configuration.getAnyObject("exploration-strategy");
    //if (map == null) {
      //ps = new GreedyExplorationStrategy<>();
    //}
    //{
//      ps = ResourceHelper.buildResource(handle, type);
//    }
    return null;
  }

  @Override
  public <T> Executor<T> getExecutor() {
    // TODO Auto-generated method stub
    return null;
  }
}
