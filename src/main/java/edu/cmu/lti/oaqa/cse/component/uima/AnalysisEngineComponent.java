package edu.cmu.lti.oaqa.cse.component.uima;

import java.io.IOException;
import java.net.InetAddress;
import java.util.Arrays;
import java.util.List;

import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.analysis_engine.AnalysisEngineDescription;
import org.apache.uima.cas.CAS;
import org.apache.uima.cas.CASException;
import org.apache.uima.cas.text.AnnotationIndex;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.util.CasCopier;
import org.apache.uima.util.CasCreationUtils;
import org.uimafit.factory.AnalysisEngineFactory;
import org.xml.sax.SAXException;

import com.google.common.collect.Lists;

import edu.cmu.lti.oaqa.ecd.phase.ExecutionStatus;
import edu.cmu.lti.oaqa.ecd.phase.ProcessingStepUtils;
import edu.cmu.lti.oaqa.ecd.phase.Trace;
import edu.cmu.lti.oaqa.ecd.util.CasUtils;
import edu.cmu.lti.oaqa.framework.types.CurrentExecution;
import edu.cmu.lti.oaqa.framework.types.InputElement;
import edu.cmu.lti.oaqa.framework.types.ProcessingStep;

public class AnalysisEngineComponent extends UimaComponent {

  private final AnalysisEngineDescription descriptor;

  public AnalysisEngineComponent(AnalysisEngineDescription descriptor, String className)
          throws ResourceInitializationException {
    super(className);
    this.descriptor = descriptor;
  }

  @Override
  public void init() {
    // TODO Auto-generated method stub
  }

  @Override
  public List<JCas> thisExecute(List<JCas> input) throws Exception {
    AnalysisEngine ae = AnalysisEngineFactory.createAggregate(descriptor);
    for (JCas cas : input) {
      AnnotationIndex<Annotation> prevSteps = cas.getAnnotationIndex(ProcessingStep.type);
      String prevCasId = ProcessingStepUtils.getPreviousCasId(prevSteps);
      Trace prevTrace = ProcessingStepUtils.getTrace(prevSteps);
      String optionId = ae.getAnalysisEngineMetaData().getName();
      String sequenceId = ProcessingStepUtils.getSequenceId(cas);
      Trace trace = ProcessingStepUtils.getPartialTrace(prevTrace.getTrace(), 0, optionId);
      process(ae, cas, prevCasId, prevTrace, optionId, sequenceId, trace);
      //ae.process(cas);
    }
    ae.destroy();
    return input;
  }

  private void process(final AnalysisEngine ae, JCas nextCas, String prevCasId, Trace prevTrace,
          String optionId, String sequenceId, Trace trace) throws IOException, SAXException,
          Exception {
    long a = System.currentTimeMillis();
    final String uuid = ProcessingStepUtils.getCurrentExperimentId(nextCas);
    final String key = ProcessingStepUtils.getExecutionIdHash(uuid, trace, sequenceId);
    try {
      insertExecutionTrace(nextCas, optionId, a, prevCasId, trace, key);
      System.out.printf("[%s] Executing option: %s on trace %s\n", sequenceId, optionId, prevTrace);
      addExecutionIdHash(key, nextCas);
      ae.process(nextCas);
      long b = System.currentTimeMillis();
      addProcessingStep(nextCas, optionId, key);
      // storeCas(nextCas, b, key);
      System.out.printf("[%s]  Execution time for option %s: %ss\n", sequenceId, optionId,
              (b - a) / 1000);
    } catch (Exception e) {
      long b = System.currentTimeMillis();
      try {
        // storeException(b, e, key, ExecutionStatus.FAILURE);
        System.out.printf("[%s]  Execution failed for option: %s after %ss\n", sequenceId,
                optionId, (b - a) / 1000);
      } finally {
        nextCas.release();
      }
      throw e; // Re-throw exception to allow the Flow controller do its job
    }

  }

  private void addProcessingStep(JCas jcas, String optionId, String key) {
    ProcessingStep s = new ProcessingStep(jcas);
    s.setComponent(optionId);
    s.setPhaseId(0);
    s.setCasId(key);
    s.addToIndexes();
  }

  public void addExecutionIdHash(String idHash, JCas cas) {
    CurrentExecution ce = new CurrentExecution(cas);
    ce.setIdHash(idHash);
    ce.addToIndexes();
  }

  private void insertExecutionTrace(JCas jcas, final String optionId, final long startTime,
          final String prevCas, final Trace trace, final String key) throws IOException {
    final String uuid = ProcessingStepUtils.getCurrentExperimentId(jcas);
    InputElement input = (InputElement) CasUtils.getFirst(jcas, InputElement.class.getName());
    final String dataset = input.getDataset();
    final String sequenceId = input.getSequenceId();
    //persistence.insertExecutionTrace(optionId, sequenceId, dataset, 0, uuid, startTime,
      //      getHostName(), trace.getTrace(), key);
  }

  private String getHostName() throws IOException {
    InetAddress addr = InetAddress.getLocalHost();
    return addr.getHostName();
  }

  @Override
  public List<JCas> cloneInput(List<JCas> input) {
    List<JCas> newList = Lists.newArrayList();
    for (JCas cas : input) {
      CAS newCas = null;
      try {
        newCas = CasCreationUtils.createCas(Arrays.asList(descriptor.getMetaData()));
        CasCopier.copyCas(cas.getCas(), newCas, true);
        newList.add(newCas.getJCas());
      } catch (Exception e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
    return newList;
  }
}
