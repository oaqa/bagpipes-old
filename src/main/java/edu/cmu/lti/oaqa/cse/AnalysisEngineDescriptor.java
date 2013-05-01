package edu.cmu.lti.oaqa.cse;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

//import org.apache.axis.server.ParamList;

import edu.cmu.lti.oaqa.cse.configuration.parameter.Parameter;

public class AnalysisEngineDescriptor {

	private String className;
	
	private Map<String,List<Parameter>> paramMap;
	
	public AnalysisEngineDescriptor(String className){
		paramMap = new HashMap<String,List<Parameter>>();
		this.className = className;
	}
	
	public <T> void  addParam(Parameter<T> param){
		List<Parameter> pList = new LinkedList<Parameter>();
		pList.add(param);
		paramMap.put(param.getName(),pList);
	}
	
	
	

}
