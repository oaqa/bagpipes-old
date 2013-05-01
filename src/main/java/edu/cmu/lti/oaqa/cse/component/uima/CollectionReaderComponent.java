package edu.cmu.lti.oaqa.cse.component.uima;

import static java.util.Arrays.asList;

import java.util.List;

import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.analysis_engine.AnalysisEngineDescription;
import org.apache.uima.analysis_engine.metadata.AnalysisEngineMetaData;
import org.apache.uima.cas.CAS;
import org.apache.uima.collection.CollectionReader;
import org.apache.uima.jcas.JCas;
import org.apache.uima.util.CasCreationUtils;

import com.google.common.collect.Lists;

public class CollectionReaderComponent extends UimaComponent {

	private final CollectionReader reader;

	public CollectionReaderComponent(CollectionReader reader) {
		super(reader.getMetaData().getName());
		this.reader = reader;
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
	}

	@Override
	public List<JCas> thisExecute(List<JCas> input) throws Exception {
		List<JCas> list = Lists.newArrayList();
		while (reader.hasNext()) {
			CAS cas = CasCreationUtils.createCas(asList(reader.getMetaData()));
			reader.getNext(cas);
			list.add(cas.getJCas());
		}
		return list;
	}

	@Override
	protected List<JCas> cloneInput(List<JCas> input) {
		return input;
	}
	
	
/*
	@Override
	protected AnalysisEngineMetaData getMetaData() {
		return reader.getMetaData();
	}
*/

}
