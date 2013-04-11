package edu.cmu.lti.oaqa.cse.configuration.test;

import edu.cmu.lti.oaqa.cse.configuration.Parameter;

public class Ex1 {

	public static final String NAME = "oaqa-tutorial", AUTHOR = "oaqa";
	public static final Parameter<String> CR_DIR = createParam(
			"InputDirectory", "data/"), CR_LANGUAGE = createParam("Language",
			"en"), CR_ENCODING = createParam("Encoding", "UTF-8"),
			OPTION1_TEST = createParam("test", "param1");

	public static final Parameter<Boolean> CR_BROWSE = createParam(
			"BrowseSubdirectories", false);
	public static final String CR_CLASS = "collection.fs.FileSystemCollectionReader";

	public static final String OPTION1_CLASS = "org.apache.uima.tutorial.ex1.RoomNumberAnnotator";
	public static final Parameter<String> OPTION1_PARAM1 = createParam("test",
			"param1");

	public static final String CONSUMER1_CLASS = "org.apache.uima.examples.xmi.XmiWriterCasConsumer";
	public static final Parameter<String> CONSUMER1_PARAM1 = createParam(
			"OutputDirectory", "cas-output");
	
	private static <T> Parameter<T> createParam(String name, T val) {
		return new Parameter<T>(name, val);
	}


}
