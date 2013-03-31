package edu.cmu.lti.oaqa.cse.configuration;

public abstract class Parser {
	
	/*protected final static String CONFIGURATION = "CONFIGURATION",
			COLLECTION_READER = "collection-reader", PIPELINE = "pipeline",
			POST_PROCESS = "post-process";
*/
	private String resource;

	public Parser(String resource) {
		this.resource = resource;
	}

	public Configuration parse() {
		String name = getName();
		String author = getAuthor();
		CollectionReaderDescriptor crDesc = buildCollectionReader();
		PipelineDescriptor plDesc = buildPipelineDescriptor();
		return new Configuration(name, author, crDesc, plDesc);
	}

	public abstract CollectionReaderDescriptor buildCollectionReader();

	public abstract PipelineDescriptor buildPipelineDescriptor();

	public abstract String getName();

	public abstract String getAuthor();

}
