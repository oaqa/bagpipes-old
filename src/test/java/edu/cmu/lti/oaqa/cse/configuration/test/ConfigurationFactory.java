package edu.cmu.lti.oaqa.cse.configuration.test;

import java.util.Arrays;
import java.util.List;

import com.google.common.collect.Lists;

import edu.cmu.lti.oaqa.cse.configuration.CollectionReaderDescriptor;
import edu.cmu.lti.oaqa.cse.configuration.Configuration;
import edu.cmu.lti.oaqa.cse.configuration.ConsumerDescriptor;
import edu.cmu.lti.oaqa.cse.configuration.OptionDescriptor;
import edu.cmu.lti.oaqa.cse.configuration.PhaseDescriptor;
import edu.cmu.lti.oaqa.cse.configuration.PipelineDescriptor;
import edu.cmu.lti.oaqa.cse.configuration.parameter.Parameter;

public final class ConfigurationFactory {
	public static final List<PhaseDescriptor> phaseDescsEx1 = initPhaseDescriptorsEx1();
	public static final List<PhaseDescriptor> phaseDescsEx4 = initPhaseDescriptorsEx4();
	public static final CollectionReaderDescriptor collectionReaderDesc = initCollectionReaderDescriptor();
	public static final PipelineDescriptor pipelineDescEx1 = initPipeline(phaseDescsEx1);
	public static final PipelineDescriptor pipelineDescEx4 = initPipeline(phaseDescsEx4);
	public static final Configuration programmedConfEx1 = initProgrammaticConfigurationEx1();
	public static final Configuration programmedConfEx4 = initProgrammaticConfigurationEx4();

	public static Configuration initProgrammaticConfigurationEx1() {
		List<ConsumerDescriptor> consumerDescs = initConsumers();
		return new Configuration(Ex1.NAME, Ex1.AUTHOR, collectionReaderDesc,
				pipelineDescEx1, consumerDescs);
	}

	public static Configuration initProgrammaticConfigurationEx4() {
		List<ConsumerDescriptor> consumerDescs = initConsumers();
		return new Configuration(Ex4.NAME, Ex4.AUTHOR, collectionReaderDesc,
				pipelineDescEx4, consumerDescs);
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

	public static List<PhaseDescriptor> initPhaseDescriptorsEx1() {
		List<OptionDescriptor> optionDescs = initPhaseOneOptionDescriptors();
		PhaseDescriptor phaseDesc = new PhaseDescriptor("RoomNumberAnnotator",
				optionDescs);
		return Lists.newArrayList(phaseDesc);
	}

	public static List<PhaseDescriptor> initPhaseDescriptorsEx4() {

		//Create phase1
		OptionDescriptor optionOne = initOptionDescriptor(Ex4.OPTION1_CLASS,Ex4.OPTION1_PARAM1,Ex4.OPTION1_PARAM2);
		PhaseDescriptor phaseOne = initPhaseDescriptor(Ex4.PHASE1_NAME,optionOne);
		//Create phase2
		optionOne = initOptionDescriptor(Ex4.OPTION2_1_CLASS,Ex4.OPTION2_1_PARAM1);
		OptionDescriptor optionTwo = initOptionDescriptor(Ex4.OPTION2_2_CLASS,Ex4.OPTION2_2_PARAM1);
		PhaseDescriptor phaseTwo = initPhaseDescriptor(Ex4.PHASE2_NAME,optionOne,optionTwo);
		//create phase3
		optionOne = initOptionDescriptor(Ex4.OPTION3_CLASS,Ex4.OPTION3_PARAM1);
		PhaseDescriptor phaseThree = initPhaseDescriptor(Ex4.PHASE3_NAME,optionOne);
		return Lists.newArrayList(phaseOne, phaseTwo,phaseThree);
	}

	public static PhaseDescriptor initPhaseDescriptor(String name,
			OptionDescriptor... optionDescs) {
		return new PhaseDescriptor(name, Arrays.asList(optionDescs));
	}

	public static final OptionDescriptor initOptionDescriptor(String className,
			Parameter... params) {
		OptionDescriptor optionDesc = new OptionDescriptor(className);
		for (Parameter p : params)
			optionDesc.addParam(p);
		return optionDesc;
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
