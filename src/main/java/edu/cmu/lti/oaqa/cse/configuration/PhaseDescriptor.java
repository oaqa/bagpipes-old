package edu.cmu.lti.oaqa.cse.configuration;

import java.util.List;

public class PhaseDescriptor {
	private List<OptionDescriptor> options;
	private String name;

	public PhaseDescriptor(List<OptionDescriptor> options) {
		this.options = options;
	}

	public PhaseDescriptor(String name, List<OptionDescriptor> options) {
		this(options);
		this.name = name;
	}
	
	public List<OptionDescriptor> getOptionDescriptors(){
		return options;
	}
	
	public boolean equals(Object obj){
		if (obj == this) {
			return true;
		}
		if (obj == null || obj.getClass() != this.getClass()) {
			return false;
		}
		return this.options.equals(((PhaseDescriptor)obj).getOptionDescriptors());
	}
	
	public String toString(){
		return "name: "+ name + "\noptions: " + options; 
	}

}
