package edu.cmu.lti.oaqa.cse.configuration;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;

public abstract class Parser {

	/*
	 * protected final static String CONFIGURATION = "CONFIGURATION",
	 * COLLECTION_READER = "collection-reader", PIPELINE = "pipeline",
	 * POST_PROCESS = "post-process";
	 */
	private String resource;
	protected Map<String, Object> confMap;

	public Parser(String resource) {
		this.resource = resource;
		try {
			confMap = getResMap(resource);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Configuration parse() {
		String name = getName();
		String author = getAuthor();
		CollectionReaderDescriptor crDesc = buildCollectionReader();
		PipelineDescriptor plDesc = buildPipelineDescriptor();
		List<ConsumerDescriptor> consumerDescs = buildConsumers();
		return new Configuration(name, author, crDesc, plDesc, consumerDescs);
	}

	public abstract Map<String, Object> getResMap(String resource)
			throws FileNotFoundException;

	public abstract CollectionReaderDescriptor buildCollectionReader();

	public abstract PipelineDescriptor buildPipelineDescriptor();

	public abstract List<ConsumerDescriptor> buildConsumers();

	public abstract String getName();

	public abstract String getAuthor();

}
