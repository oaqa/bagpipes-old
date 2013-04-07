package edu.cmu.lti.oaqa.cse.configuration;

import java.util.Arrays;
import java.util.List;

import com.google.common.collect.Lists;

import edu.cmu.lti.oaqa.cse.AnalysisEngineDescriptor;

public class Configuration {

	private String name, author;
	private PersistenceProviderDescriptor ppDesc;
	private CollectionReaderDescriptor collectionReaderDesc;
	private PipelineDescriptor pipelineDesc;
	private List<ConsumerDescriptor> consumers;

	private Configuration(String name, String author) {
		this.name = name;
		this.author = author;
	}

	public Configuration(String name, String author,
			CollectionReaderDescriptor crDesc, PipelineDescriptor plDesc) {
		this(name, author);
		this.collectionReaderDesc = crDesc;
		this.pipelineDesc = plDesc;
	}

	public Configuration(String name, String author,
			CollectionReaderDescriptor crDesc, PipelineDescriptor plDesc,
			List<ConsumerDescriptor> consumers) {
		this(name, author, crDesc, plDesc);
		this.consumers = consumers;
	}

	public Configuration(CollectionReaderDescriptor crDesc,
			PipelineDescriptor plDesc) {
		this("oaqa-experiment", "oaqa-author", crDesc, plDesc);
	}

	public String getAuthor() {
		return this.author;
	}

	public String getName() {
		return name;
	}

	public CollectionReaderDescriptor getCollectionReaderDescriptor() {
		return collectionReaderDesc;
	}
	
	public PipelineDescriptor getPipelineDescriptor(){
		return pipelineDesc;
	}

}
