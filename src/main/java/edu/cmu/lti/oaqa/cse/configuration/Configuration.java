package edu.cmu.lti.oaqa.cse.configuration;

import java.util.List;

import edu.cmu.lti.oaqa.cse.AnalysisEngineDescriptor;

public class Configuration {

	private String name, author;
	private PersistenceProviderDescriptor ppDesc;
	private CollectionReaderDescriptor crDesc;
	private PipelineDescriptor plDesc;

	private Configuration(String name, String author) {
		this.name = name;
		this.author = author;
	}

	public Configuration(CollectionReaderDescriptor crDesc,
			PipelineDescriptor plDesc) {
		this("oaqa-experiment", "oaqa-author");
		this.crDesc = crDesc;
		this.plDesc = plDesc;
	}

	public Configuration(String name, String author,
			CollectionReaderDescriptor crDesc, PipelineDescriptor plDesc) {
		this(name, author);
		this.crDesc = crDesc;
		this.plDesc = plDesc;
	}

}
