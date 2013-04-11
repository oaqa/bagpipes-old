package edu.cmu.lti.oaqa.cse.space.tree;

import edu.cmu.lti.oaqa.components.ExecutableComponent;

public class ExecutingNodeVisitor implements NodeVisitor<ExecutableComponent> {

	@Override
	public void visit(Node<ExecutableComponent> node) {
		System.out.println(node.getData().execute(null));
	}

	

}
