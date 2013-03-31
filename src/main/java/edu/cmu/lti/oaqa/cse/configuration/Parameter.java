package edu.cmu.lti.oaqa.cse.configuration;

public class Parameter<T> {

	private T value;
	private String name;
	
	public Parameter(String name, T val){
		this.name = name;
		this.value = val;
	}
	
	public T getVal() {
		return value;
	}

	public String getName(){
		return name;
	}
	
	
	
	
}
