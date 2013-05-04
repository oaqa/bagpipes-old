package edu.cmu.lti.oaqa.cse.component.uima;

import static java.util.Arrays.asList;

import java.util.List;

import org.apache.uima.cas.CAS;
import org.apache.uima.collection.CollectionReader;
import org.apache.uima.jcas.JCas;
import org.apache.uima.util.CasCreationUtils;

import com.google.common.collect.Lists;

import edu.cmu.lti.oaqa.ecd.ExperimentPersistenceProvider;

public class CollectionReaderComponent extends UimaComponent {

	private final CollectionReader reader;
	
	private final ExperimentPersistenceProvider persistence;
	
	private final String uuid;

	public CollectionReaderComponent(CollectionReader reader, ExperimentPersistenceProvider persistence, 
	          String uuid) {
		super(reader.getMetaData().getName());
		this.reader = reader;
		this.persistence = persistence;
		this.uuid = uuid;
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
		persistence.updateExperimentMeta(uuid, list.size());
		return list;
	}

	@Override
	protected List<JCas> cloneInput(List<JCas> input) {
		return input;
	}
}
