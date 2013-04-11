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

	// private List<ConsumerDescriptor> consumerDescs;

	private ConfigurationFactory cFactory;
	private Configuration parsedConf;

	@Before
	public void initialize() {
		String resource = "oaqa-tutorial-ex1.yaml";
		Parser parser = new YAMLParser(resource);
		parsedConf = parser.parse();
	}

	@Test
	public void parserTest() {
		// Configuration from yaml the same as programmatic conf?
		assertEquals(Ex1.AUTHOR, parsedConf.getAuthor());
		assertEquals(Ex1.NAME, parsedConf.getName());
	}

	@Test
	public void yamlParserTest() {

	}

	@Test
	public void pipelineDescriptorTest() {
		PipelineDescriptor confPd = parsedConf.getPipelineDescriptor();
		List<PhaseDescriptor> confPhases = confPd.getPhaseDescriptors();
		List<PhaseDescriptor> progPhases = cFactory.pipelineDesc
				.getPhaseDescriptors();
		System.out.println("confPhases" + confPhases);
		System.out.println("progPhases" + progPhases);
		assertEquals(confPhases, progPhases);
		assertEquals(confPd, cFactory.pipelineDesc);
	}

	@Test
	public void collectionReaderTest() {
		CollectionReaderDescriptor crdConf = parsedConf
				.getCollectionReaderDescriptor();
		// Collection reader the same as programmatic collection reader
		// descriptor?
		assertEquals(crdConf.getClassName(),
				cFactory.collectionReaderDesc.getClassName());
		assertEquals(crdConf.getParam("InputDirectory"),
				cFactory.collectionReaderDesc.getParam("InputDirectory"));
		assertEquals(crdConf.getParam("Encoding"),
				cFactory.collectionReaderDesc.getParam("Encoding"));
		assertEquals(crdConf.getParam("Language"),
				cFactory.collectionReaderDesc.getParam("Language"));
		assertEquals(crdConf.getParam("BrowseSubdirectories"),
				cFactory.collectionReaderDesc.getParam("BrowseSubdirectories"));
		System.out.println("Config's collection reader: " + crdConf);
		System.out.println("Programmatic collection reader: "
				+ cFactory.collectionReaderDesc);
		assertEquals(crdConf, cFactory.collectionReaderDesc);
	}

	@Test
	public void configurationTest() {
		assertEquals(parsedConf, cFactory.programmedConf);
	}

}
