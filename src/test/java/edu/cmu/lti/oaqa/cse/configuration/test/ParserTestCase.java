package edu.cmu.lti.oaqa.cse.configuration.test;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import edu.cmu.lti.oaqa.cse.configuration.CollectionReaderDescriptor;
import edu.cmu.lti.oaqa.cse.configuration.ComponentDescriptor;
import edu.cmu.lti.oaqa.cse.configuration.Configuration;
import edu.cmu.lti.oaqa.cse.configuration.Parameter;
import edu.cmu.lti.oaqa.cse.configuration.Parser;
import edu.cmu.lti.oaqa.cse.configuration.PipelineDescriptor;
import edu.cmu.lti.oaqa.cse.configuration.YAMLParser;

public class ParserTestCase {

	private static final String NAME = "oaqa-tutorial", AUTHOR = "oaqa";
	private static final Parameter<String> CR_DIR = createParam(
			"InputDirectory", "data/"), CR_LANGUAGE = createParam("Language",
			"en"), CR_ENCODING = createParam("Encoding", "UTF-8"),
			OPTION1_TEST = createParam("test", "param1");
	
	private static final Parameter<Boolean> CR_BROWSE = createParam(
			"BrowseSubdirectories", false);
	private static final String CR_CLASS = "collection.fs.FileSystemCollectionReader";

	private static final String OPTION1_CLASS = "org.apache.uima.tutorial.ex1.RoomNumberAnnotator";

	private Configuration conf;
	private CollectionReaderDescriptor collectionReaderDesc;
	private PipelineDescriptor pd;

	@Before
	public void init() {
		String resource = "oaqa-tutorial-ex1.yaml";
		Parser parser = new YAMLParser(resource);
		conf = parser.parse();

		// pd = new PipelineDescriptor("some class");
		// conf = new Configuration(NAME,AUTHOR);

	}

	@Before
	public void initCollectionReaderDescriptor() {
		collectionReaderDesc = new CollectionReaderDescriptor(CR_CLASS);
		collectionReaderDesc.addParam(CR_DIR);
		collectionReaderDesc.addParam(CR_LANGUAGE);
		collectionReaderDesc.addParam(CR_ENCODING);
		collectionReaderDesc.addParam(CR_BROWSE);
	}

	@Before
	public void initPipeline() {
	
	}

	@Test
	public void parserTest() {
		// Configuration from yaml the same as programmatic conf?
		assertEquals(AUTHOR, conf.getAuthor());
		assertEquals(NAME, conf.getName());

	}

	@Test
	public void yamlParserTest() {

	}

	@Test
	public void pipelineDescriptorTest(){
		pd = conf.getPipelineDescriptor();
	//	assertEquals(pd.)
		
	}

	@Test
	public void collectionReaderTest() {
		CollectionReaderDescriptor crdConf = conf
				.getCollectionReaderDescriptor();
		// Collection reader the same as programmatic collection reader
		// descriptor?
		assertEquals(crdConf.getClassName(), collectionReaderDesc.getClassName());
		assertEquals(crdConf.getParam("InputDirectory"),
				collectionReaderDesc.getParam("InputDirectory"));
		assertEquals(crdConf.getParam("Encoding"), collectionReaderDesc.getParam("Encoding"));
		assertEquals(crdConf.getParam("Language"), collectionReaderDesc.getParam("Language"));
		assertEquals(crdConf.getParam("BrowseSubdirectories"),
				collectionReaderDesc.getParam("BrowseSubdirectories"));
		System.out.println("Config's collection reader: " + crdConf);
		System.out.println("Programmatic collection reader: " + collectionReaderDesc);
		assertEquals(crdConf, collectionReaderDesc);
	}

	private static <T> Parameter<T> createParam(String name, T val) {
		return new Parameter<T>(name, val);
	}

}
