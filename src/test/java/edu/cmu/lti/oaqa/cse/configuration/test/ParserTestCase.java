package edu.cmu.lti.oaqa.cse.configuration.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.google.common.collect.Lists;

import edu.cmu.lti.oaqa.cse.configuration.CollectionReaderDescriptor;
import edu.cmu.lti.oaqa.cse.configuration.ComponentDescriptor;
import edu.cmu.lti.oaqa.cse.configuration.Configuration;
import edu.cmu.lti.oaqa.cse.configuration.ConsumerDescriptor;
import edu.cmu.lti.oaqa.cse.configuration.OptionDescriptor;
import edu.cmu.lti.oaqa.cse.configuration.Parameter;
import edu.cmu.lti.oaqa.cse.configuration.Parser;
import edu.cmu.lti.oaqa.cse.configuration.PhaseDescriptor;
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
	private static final Parameter<String> OPTION1_PARAM1 = createParam("test",
			"param1");

	private static final String CONSUMER1_CLASS = "org.apache.uima.examples.xmi.XmiWriterCasConsumer";
	private static final Parameter<String> CONSUMER1_PARAM1 = createParam(
			"OutputDirectory", "cas-output");

	private Configuration parsedConf, programmedConf;
	private CollectionReaderDescriptor collectionReaderDesc;
	private PipelineDescriptor pipelineDesc;

	// private List<ConsumerDescriptor> consumerDescs;

	@Before
	public void initialize() {
		String resource = "oaqa-tutorial-ex1.yaml";
		Parser parser = new YAMLParser(resource);
		parsedConf = parser.parse();
	}

	@Before
	public void initProgrammaticConfiguration() {
		this.collectionReaderDesc = initCollectionReaderDescriptor();
		List<PhaseDescriptor> phaseDescs = initPhaseDescriptors();
		this.pipelineDesc = initPipeline(phaseDescs);
		List<ConsumerDescriptor> consumerDescs = initConsumers();
		this.programmedConf = new Configuration(NAME, AUTHOR,
				collectionReaderDesc, pipelineDesc, consumerDescs);
	}

	public CollectionReaderDescriptor initCollectionReaderDescriptor() {
		CollectionReaderDescriptor collectionReaderDesc = new CollectionReaderDescriptor(
				CR_CLASS);
		collectionReaderDesc.addParam(CR_DIR);
		collectionReaderDesc.addParam(CR_LANGUAGE);
		collectionReaderDesc.addParam(CR_ENCODING);
		collectionReaderDesc.addParam(CR_BROWSE);
		return collectionReaderDesc;
	}

	public List<PhaseDescriptor> initPhaseDescriptors() {
		List<OptionDescriptor> optionDescs = initPhaseOneOptionDescriptors();
		PhaseDescriptor phaseDesc = new PhaseDescriptor("RoomNumberAnnotator",
				optionDescs);
		return Lists.newArrayList(phaseDesc);
	}

	public List<OptionDescriptor> initPhaseOneOptionDescriptors() {
		OptionDescriptor optionDesc = new OptionDescriptor(OPTION1_CLASS);
		optionDesc.addParam(OPTION1_PARAM1);
		return Lists.newArrayList(optionDesc);
	}

	public PipelineDescriptor initPipeline(List<PhaseDescriptor> phaseDescs) {
		return new PipelineDescriptor(phaseDescs);
	}

	public List<ConsumerDescriptor> initConsumers() {
		ConsumerDescriptor consumerDesc = new ConsumerDescriptor(
				CONSUMER1_CLASS);
		consumerDesc.addParam(CONSUMER1_PARAM1);
		return Lists.newArrayList(consumerDesc);
	}

	@Test
	public void parserTest() {
		// Configuration from yaml the same as programmatic conf?
		assertEquals(AUTHOR, parsedConf.getAuthor());
		assertEquals(NAME, parsedConf.getName());
	}

	@Test
	public void yamlParserTest() {

	}

	@Test
	public void pipelineDescriptorTest() {
		PipelineDescriptor confPd = parsedConf.getPipelineDescriptor();
		List<PhaseDescriptor> confPhases = confPd.getPhaseDescriptors();
		List<PhaseDescriptor> progPhases = pipelineDesc.getPhaseDescriptors();
		System.out.println("confPhases" + confPhases);
		System.out.println("progPhases" + progPhases);
		assertEquals(confPhases, progPhases);
		assertEquals(confPd,pipelineDesc);
	}

	@Test
	public void collectionReaderTest() {
		CollectionReaderDescriptor crdConf = parsedConf
				.getCollectionReaderDescriptor();
		// Collection reader the same as programmatic collection reader
		// descriptor?
		assertEquals(crdConf.getClassName(),
				collectionReaderDesc.getClassName());
		assertEquals(crdConf.getParam("InputDirectory"),
				collectionReaderDesc.getParam("InputDirectory"));
		assertEquals(crdConf.getParam("Encoding"),
				collectionReaderDesc.getParam("Encoding"));
		assertEquals(crdConf.getParam("Language"),
				collectionReaderDesc.getParam("Language"));
		assertEquals(crdConf.getParam("BrowseSubdirectories"),
				collectionReaderDesc.getParam("BrowseSubdirectories"));
		System.out.println("Config's collection reader: " + crdConf);
		System.out.println("Programmatic collection reader: "
				+ collectionReaderDesc);
		assertEquals(crdConf, collectionReaderDesc);
	}

	@Test
	public void configurationTest() {
		assertEquals(parsedConf, programmedConf);
	}

	private static <T> Parameter<T> createParam(String name, T val) {
		return new Parameter<T>(name, val);
	}

}
