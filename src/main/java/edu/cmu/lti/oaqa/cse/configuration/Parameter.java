package edu.cmu.lti.oaqa.cse.configuration;

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
	
	
	public static  Parameter<Integer> thisParameter(String name, Integer val){
		return new IntegerParameter(name,val);
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
