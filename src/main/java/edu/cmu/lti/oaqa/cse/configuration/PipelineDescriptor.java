package edu.cmu.lti.oaqa.cse.configuration;

import java.util.LinkedList;
import java.util.List;

public class PipelineDescriptor {

	private List<PhaseDescriptor> phaseDescs;
//	private List<ConsumerDescriptor> consumers;

	public PipelineDescriptor(List<PhaseDescriptor> phases,
			List<ConsumerDescriptor> consumers) {
		phases = new LinkedList<PhaseDescriptor>();
	//	consumers = new LinkedList<ConsumerDescriptor>();	
	}
	
	public List<PhaseDescriptor> getPhaseDescriptors(){
		return phaseDescs;
	}

	
	
	
	
}
