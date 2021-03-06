package edu.cmu.lti.oaqa.cse.configuration;

/**
 * A generic parameter for a component in a pipeline.
 */
public  class Parameter<T> {

	private T value;
	private String name;

	public Parameter(String name, T val) {
		this.name = name;
		this.value = val;
	}

	public T getVal() {
		return value;
	}

	public String getName() {
		return name;
	}

	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}
		if (obj == null || obj.getClass() != this.getClass()) {
			return false;
		}
		return this.value.equals(((Parameter<T>) obj).value);
	}
	
	public String toString(){
		return  value + ""; 
		
	}
	
	public static DoubleParameter newParameter(String name, Double val){
		return new DoubleParameter(name,val);
	}
	
	public static StringParameter newParameter(String name, String val){
		return new StringParameter(name,val);
	}

	public static IntegerParameter newParameter(String key, Integer val) {
		return new IntegerParameter(key,val);
	}
	

}
