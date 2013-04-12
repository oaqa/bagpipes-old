package edu.cmu.lti.oaqa.cse.configuration.test;

import java.util.Arrays;
import java.util.List;

import edu.cmu.lti.oaqa.cse.configuration.Parameter;

public final class Ex4 {
	// GENERAL CONFIGURATION
	public static final String NAME = "oaqa-tutorial", AUTHOR = "oaqa";
	/**
	 * COLLECTION READER:
	 */
	public static final Parameter<String> CR_DIR = createParam(
			"InputDirectory", "data/"), CR_LANGUAGE = createParam("Language",
			"en"), CR_ENCODING = createParam("Encoding", "UTF-8"),
			OPTION1_TEST = createParam("test", "param1");

	public static final Parameter<Boolean> CR_BROWSE = createParam(
			"BrowseSubdirectories", false);
	public static final String CR_CLASS = "collection.fs.FileSystemCollectionReader";

	/*
	 * PHASE 1:
	 */
	public static final String PHASE1_NAME = "RoomNumberAnnotator";
	// OPTION1_1:
	public static final String OPTION1_CLASS = "tutorial.ex3.RoomNumberAnnotator";
	public static final String[] LOCATIONS = new String[] {
			"Watson - Yorktown", "Watson - Hawthrone I",
			"Watson - Hawthorne II" };
	public static final String[] PATTERNS = new String[] {
			"\\b[0-4]\\d[0-2]\\d\\d\\b", "\\b[G1-4][NS]-[A-Z]\\d\\d\\b",
			"\\bJ[12]-[A-Z]\\d\\d\\b" };
	public static final Parameter<List<String>> OPTION1_PARAM1 = createParam(
			"Locations", Arrays.asList(LOCATIONS));
	public static final Parameter<List<String>> OPTION1_PARAM2 = createParam(
			"Patterns", Arrays.asList(PATTERNS));

	/**
	 * PHASE 2:
	 */
	public static final String PHASE2_NAME = "TutorialDateTime";
	// OPTION2_1
	public static final String OPTION2_1_CLASS = "tutorial.ex3.SimpleTutorialDateTime";
	public static final Parameter<String> OPTION2_1_PARAM1 = createParam(
			"test", "param1");
	// OPTION2_2
	public static final String OPTION2_2_CLASS = "tutorial.ex3.TutorialDateTime";
	public static final Parameter<String> OPTION2_2_PARAM1 = createParam(
			"test", "param1");

	/*
	 * PHASE 3:
	 */
	public static final String PHASE3_NAME = "MeetingAnnotator";
	// OPTION3
	public static final String OPTION3_CLASS = "tutorial.ex4.MeetingAnnotator";
	public static final Parameter<Integer> OPTION3_PARAM1 = createParam(
			"WindowSize", 200);

	// CONSUMER
	public static final String CONSUMER1_CLASS = "org.apache.uima.examples.xmi.XmiWriterCasConsumer";
	public static final Parameter<String> CONSUMER1_PARAM1 = createParam(
			"OutputDirectory", "cas-output");

	private static <T> Parameter<T> createParam(String name, T val) {
		return new Parameter<T>(name, val);
	}

}
