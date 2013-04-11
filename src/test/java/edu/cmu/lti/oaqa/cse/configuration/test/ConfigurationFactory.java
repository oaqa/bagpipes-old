package edu.cmu.lti.oaqa.cse.configuration.test;

import java.util.List;
import edu.cmu.lti.oaqa.cse.configuration.test.Ex1.*;
import com.google.common.collect.Lists;

import edu.cmu.lti.oaqa.cse.configuration.CollectionReaderDescriptor;
import edu.cmu.lti.oaqa.cse.configuration.Configuration;
import edu.cmu.lti.oaqa.cse.configuration.ConsumerDescriptor;
import edu.cmu.lti.oaqa.cse.configuration.OptionDescriptor;
import edu.cmu.lti.oaqa.cse.configuration.Parameter;
import edu.cmu.lti.oaqa.cse.configuration.PhaseDescriptor;
import edu.cmu.lti.oaqa.cse.configuration.PipelineDescriptor;

public final class ConfigurationFactory {
	public static final List<PhaseDescriptor> phaseDescs = initPhaseDescriptors();
	public static final CollectionReaderDescriptor collectionReaderDesc = initCollectionReaderDescriptor();
	public static final PipelineDescriptor pipelineDesc = initPipeline(phaseDescs);
	public static final Configuration programmedConf = initProgrammaticConfiguration();
	
	public static Configuration initProgrammaticConfiguration() {
		List<ConsumerDescriptor> consumerDescs = initConsumers();
		return new Configuration(Ex1.NAME, Ex1.AUTHOR, collectionReaderDesc,
				pipelineDesc, consumerDescs);
	}
	
	public static CollectionReaderDescriptor initCollectionReaderDescriptor() {
		CollectionReaderDescriptor collectionReaderDesc = new CollectionReaderDescriptor(
				Ex1.CR_CLASS);
		collectionReaderDesc.addParam(Ex1.CR_DIR);
		collectionReaderDesc.addParam(Ex1.CR_LANGUAGE);
		collectionReaderDesc.addParam(Ex1.CR_ENCODING);
		collectionReaderDesc.addParam(Ex1.CR_BROWSE);
		return collectionReaderDesc;
	}

	public static List<PhaseDescriptor> initPhaseDescriptors() {
		List<OptionDescriptor> optionDescs = initPhaseOneOptionDescriptors();
		PhaseDescriptor phaseDesc = new PhaseDescriptor("RoomNumberAnnotator",
				optionDescs);
		return Lists.newArrayList(phaseDesc);
	}

	public static List<OptionDescriptor> initPhaseOneOptionDescriptors() {
		OptionDescriptor optionDesc = new OptionDescriptor(Ex1.OPTION1_CLASS);
		optionDesc.addParam(Ex1.OPTION1_PARAM1);
		return Lists.newArrayList(optionDesc);
	}

	public static PipelineDescriptor initPipeline(
			List<PhaseDescriptor> phaseDescs) {
		return new PipelineDescriptor(phaseDescs);
	}

	public static List<ConsumerDescriptor> initConsumers() {
		ConsumerDescriptor consumerDesc = new ConsumerDescriptor(
				Ex1.CONSUMER1_CLASS);
		consumerDesc.addParam(Ex1.CONSUMER1_PARAM1);
		return Lists.newArrayList(consumerDesc);
	}

}
