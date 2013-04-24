package edu.cmu.lti.oaqa.cse.space.uima.list;

import java.util.Arrays;
import java.util.List;

import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.analysis_engine.AnalysisEngineDescription;
import org.apache.uima.analysis_engine.metadata.AnalysisEngineMetaData;
import org.apache.uima.cas.CAS;
import org.apache.uima.cas.CASException;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.util.CasCopier;
import org.apache.uima.util.CasCreationUtils;

import com.google.common.collect.Lists;

import edu.cmu.lti.oaqa.components.ExecutableComponent;

public abstract class UimaComponent extends ExecutableComponent<List<JCas>> {

	protected String className;

	public UimaComponent(String className) {
		this.className = className;
	}

	public String getClassName() {
		return className;
	}

	public String toString() {
		return className;
	}

	public final List<JCas> execute(List<JCas> input) throws Exception {
		return thisExecute(cloneInput(input));
	}

	protected abstract List<JCas> thisExecute(List<JCas> input) throws Exception;

	protected abstract List<JCas> cloneInput(List<JCas> input);

	// protected abstract AnalysisEngineMetaData getMetaData();

}
