package edu.cmu.lti.oaqa.cse;

import java.util.List;

import mx.bigdata.anyobject.AnyObject;

import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.collection.CollectionReader;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.resource.metadata.TypeSystemDescription;
import org.apache.uima.util.Progress;
import org.uimafit.factory.TypeSystemDescriptionFactory;

import com.google.common.collect.Lists;

import edu.cmu.lti.oaqa.ecd.driver.SimplePipelineRev803;

public class UIMAExecutor implements Executor<Pipeline> {

  private final List<Long> processedItems = Lists.newArrayList();
private BaseExperimentBuilder builder;
private AnyObject config;

  
  public UIMAExecutor(String resource, String uuid) throws Exception{
	   TypeSystemDescription typeSystem = TypeSystemDescriptionFactory.createTypeSystemDescription();
	    this.builder = new BaseExperimentBuilder(uuid, resource, typeSystem);
	    this.config = builder.getConfiguration();
  }


@Override
public void execute(Pipeline conf) {
	// TODO Auto-generated method stub
	
}
  
  //@Override
/*  public void execute(Pipeline conf) {
    CollectionReader reader = builder.buildCollectionReader(conf);
    AnalysisEngine pipeline = builder.buildPipeline(conf, "pipeline");
    if (conf.getIterable("post-process") != null) {
      AnalysisEngine post = builder.buildPipeline(conf, "post-process");
      SimplePipelineRev803.runPipeline(reader, pipeline, post);
    } else {
      SimplePipelineRev803.runPipeline(reader, pipeline);
    }
    Progress progress = reader.getProgress()[0];
    long total = progress.getCompleted();
    processedItems.add(total);
  }

  Iterable<Long> getProcessedItems() {
    return processedItems;
  }*/
}
