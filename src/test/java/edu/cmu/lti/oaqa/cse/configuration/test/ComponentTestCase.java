package edu.cmu.lti.oaqa.cse.configuration.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.cmu.lti.oaqa.cse.configuration.CollectionReaderDescriptor;
import edu.cmu.lti.oaqa.cse.configuration.parameter.Parameter;

public class ComponentTestCase {

	private final static String TEST_CLASS="className";
	private final static Parameter<String> TEST_PARAM1= new Parameter<String>("paramName1","testParam1");
	private final static Parameter<Integer> TEST_PARAM2 = new Parameter<Integer>("paramName2",3);
	
	CollectionReaderDescriptor cr;
	Parameter<String> param1;
	Parameter<Integer> param2;

	
	@Before
	public void init(){
		cr = new CollectionReaderDescriptor(TEST_CLASS);
		cr.addParam(TEST_PARAM1);
		cr.addParam(TEST_PARAM2);
		param1= new Parameter<String>("paramName1","testParam1");
		param2 = new Parameter<Integer>("paramName2",3);
	}
	@Test
	public void testComponents() {
		assertEquals(cr.getClassName(),TEST_CLASS);
		assertEquals(cr.getParam(TEST_PARAM1.getName()),param1);
		assertEquals(cr.getParam(TEST_PARAM2.getName()),param2);
	}
	
	@Test
	public void testParameters(){
		assertEquals(param1,TEST_PARAM1);
		assertEquals(param2,TEST_PARAM2);
		
	}

}
