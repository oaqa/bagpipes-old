package edu.cmu.lti.oaqa.cse.configuration;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;

/**
 * Abstract parsing object for parsing a file (or some data) and retrieving a
 * Configuration object.
 */
public abstract class Parser {

	/*
	 * protected final static String CONFIGURATION = "CONFIGURATION",
	 * COLLECTION_READER = "collection-reader", PIPELINE = "pipeline",
	 * POST_PROCESS = "post-process";
	 */
	protected Map<String, Object> confMap;

	public Parser(String resource) {
		try {
			confMap = getResourceMap(resource);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Configuration parse() {
		String name = getName();
		String author = getAuthor();
		CollectionReaderDescriptor crDesc = buildCollectionReaderDescriptor();
		PipelineDescriptor plDesc = buildPipelineDescriptor();
		Configuration config = new Configuration(name, author, crDesc, plDesc);
		config.setPersistenceProviderDescriptor(buildPersistenceProviderDescriptor());
		config.setConsumers(buildConsumerDescriptors());
		config.setScoreMap(buildScores());
		config.setExplorerDescriptor(buildExplorerDescriptor());

		return config;
	}

	protected abstract Map<String, Object> getResourceMap(String resource)
			throws FileNotFoundException;

	protected abstract CollectionReaderDescriptor buildCollectionReaderDescriptor();

	protected abstract PipelineDescriptor buildPipelineDescriptor();

	protected abstract List<ConsumerDescriptor> buildConsumerDescriptors();

	protected abstract ExplorerDescriptor buildExplorerDescriptor();

	protected abstract Map<String, ScoreDescriptor> buildScores();

	protected abstract PersistenceProviderDescriptor buildPersistenceProviderDescriptor();
	protected abstract String getName();

	protected abstract String getAuthor();

}
