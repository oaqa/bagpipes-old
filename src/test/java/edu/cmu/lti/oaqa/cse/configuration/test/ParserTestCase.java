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
import edu.cmu.lti.oaqa.cse.configuration.Parser;
import edu.cmu.lti.oaqa.cse.configuration.PhaseDescriptor;
import edu.cmu.lti.oaqa.cse.configuration.PipelineDescriptor;
import edu.cmu.lti.oaqa.cse.configuration.YAMLParser;
import edu.cmu.lti.oaqa.cse.configuration.parameter.Parameter;

public class ParserTestCase {

	// private List<ConsumerDescriptor> consumerDescs;

	private ConfigurationFactory cFactory;
	private Configuration parsedConfEx1, parsedConfEx4;

	@Before
	public void initialize() {
		// Parse example 1 configuration
		String resource = "oaqa-tutorial-ex1.yaml";
		Parser parserEx1 = new YAMLParser(resource);
		parsedConfEx1 = parserEx1.parse();
		// Parse example 4 configuration
		resource = "oaqa-tutorial-ex4.yaml";
		Parser parserEx4 = new YAMLParser(resource);
		parsedConfEx4 = parserEx4.parse();
	}
//
//	@Test
//	public void parserTest() {
//		// Configuration from yaml the same as programmatic conf?
//		assertEquals(Ex1.AUTHOR, parsedConfEx1.getAuthor());
//		assertEquals(Ex1.NAME, parsedConfEx1.getName());
//	}
//
//	@Test
//	public void testPipelinesEx1() {
//		System.out.println("Testing Ex1 pipeline...");
//		testPipelines(parsedConfEx1.getPipelineDescriptor(),
//				cFactory.pipelineDescEx1);
//	}
//
//	@Test
//	public void testPipelineEx4() {
//		System.out.println("Testing Ex4 pipeline...");
//		testPipelines(parsedConfEx4.getPipelineDescriptor(),
//				cFactory.pipelineDescEx4);
//	}
//
//	public void testPipelines(PipelineDescriptor parsedPd,
//			PipelineDescriptor progPd) {
//		List<PhaseDescriptor> confPhases = parsedPd.getPhaseDescriptors();
//		List<PhaseDescriptor> progPhases = progPd.getPhaseDescriptors();
//		System.out.println("confPhases" + confPhases);
//		System.out.println("progPhases" + progPhases);
//		assertEquals(confPhases, progPhases);
//		assertEquals(parsedPd, progPd);
//	}
//	
//	@Test
//	public void multipleComponents(){
//		PhaseDescriptor phase4 = parsedConfEx4.getPipelineDescriptor().getPhaseDescriptors().get(3);
//		System.out.println("Options: " + phase4.getOptionDescriptors());
//		assert(phase4.getOptionDescriptors().size() > 1);
//	}
//
//	@Test
//	public void collectionReaderTest() {
//		CollectionReaderDescriptor crdConf = parsedConfEx1
//				.getCollectionReaderDescriptor();
//		// Collection reader the same as programmatic collection reader
//		// descriptor?
//		assertEquals(crdConf.getClassName(),
//				cFactory.collectionReaderDesc.getClassName());
//		assertEquals(crdConf.getParam("InputDirectory"),
//				cFactory.collectionReaderDesc.getParam("InputDirectory"));
//		assertEquals(crdConf.getParam("Encoding"),
//				cFactory.collectionReaderDesc.getParam("Encoding"));
//		assertEquals(crdConf.getParam("Language"),
//				cFactory.collectionReaderDesc.getParam("Language"));
//		assertEquals(crdConf.getParam("BrowseSubdirectories"),
//				cFactory.collectionReaderDesc.getParam("BrowseSubdirectories"));
//		System.out.println("Config's collection reader: " + crdConf);
//		System.out.println("Programmatic collection reader: "
//				+ cFactory.collectionReaderDesc);
//		assertEquals(crdConf, cFactory.collectionReaderDesc);
//	}
//
//	@Test
//	public void configurationTest() {
//		assertEquals(parsedConfEx1, cFactory.programmedConfEx1);
//	}

}
