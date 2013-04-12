package edu.cmu.lti.oaqa.cse.configuration;

import java.util.LinkedList;
import java.util.List;

public class PipelineDescriptor {

	private List<PhaseDescriptor> phaseDescs;
//	private List<ConsumerDescriptor> consumers;

	public PipelineDescriptor(List<PhaseDescriptor> phases) {
		this.phaseDescs = phases;
	//	consumers = new LinkedList<ConsumerDescriptor>();	
	}
	
	public List<PhaseDescriptor> getPhaseDescriptors(){
		return phaseDescs;
	}
	
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}
		if (obj == null || obj.getClass() != this.getClass()) {
			return false;
		}
		return equals((PipelineDescriptor) obj);
	}
	
	private boolean equals(PipelineDescriptor plDesc){
		/* REMOVE, THIS IS TEMPORARY */
		for(PhaseDescriptor p:phaseDescs)
			if(!plDesc.getPhaseDescriptors().contains(p))
				System.out.println("!not equal: "+ p + "!");
		
		return phaseDescs.equals(plDesc.getPhaseDescriptors());
	}

	
	
	
	
}
