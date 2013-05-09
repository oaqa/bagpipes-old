package edu.cmu.lti.oaqa.cse.component.uima;

import java.util.List;

import org.apache.uima.jcas.JCas;

import edu.cmu.lti.oaqa.components.ExecutableComponent;

public abstract class UimaComponent extends ExecutableComponent<List<JCas>> {

	protected String className;

	public UimaComponent(String className) {
		this.className = className;
	}

	public String getClassName() {
		return className;
	}

	public String toString() {
		return className;
	}
/*
	public final List<JCas> execute(List<JCas> input) throws Exception {
		return thisExecute(input);
	}
*/

	public  List<JCas> execute(List<JCas> input)
			throws Exception {
		List<JCas> result = thisExecute(cloneInput(input));
	//	if (input != null)
		//	for (JCas cas : input)
			//	cas.release();
		return result;
	}

	protected abstract List<JCas> thisExecute(List<JCas> input)
			throws Exception;

	protected abstract List<JCas> cloneInput(List<JCas> input);
}
