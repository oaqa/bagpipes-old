package edu.cmu.lti.oaqa.cse.configuration;

import java.util.LinkedList;
import java.util.List;

/**
 * Pipeline description object, tracks phases of a pipeline.
 */
public class PipelineDescriptor {

	private List<PhaseDescriptor> phaseDescs;
	private List<ComponentDescriptor> components;
	private List<ConsumerDescriptor> consumers;

	public PipelineDescriptor(List<PhaseDescriptor> phases) {
		this.phaseDescs = phases;
		// consumers = new LinkedList<ConsumerDescriptor>();
	}
	
	public PipelineDescriptor(List<PhaseDescriptor> phases, List<ComponentDescriptor> components) {
		this.phaseDescs = phases;
		this.components = components;
	}
	
	

	public List<PhaseDescriptor> getPhaseDescriptors() {
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

	private boolean equals(PipelineDescriptor plDesc) {
		/* REMOVE, THIS IS TEMPORARY */
		for (PhaseDescriptor p : phaseDescs)
			if (!plDesc.getPhaseDescriptors().contains(p))
				System.out.println("!not equal: " + p + "!");

		return phaseDescs.equals(plDesc.getPhaseDescriptors());
	}

}
