package edu.cmu.lti.oaqa.cse.configuration;

public interface Paramable {
	public void addToParams(String name, Integer i);
	public void addToParams(String name,Double d);
	public void addToParams(String name,String s);
	
	public Integer getInteger(String key);
	public Double getDouble(String key);
	public String getString(String key);
}
