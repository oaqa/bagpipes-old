package edu.cmu.lti.oaqa.cse.space.tree;

import edu.cmu.lti.oaqa.components.ExecutableComponent;

public abstract class NodeVisitor<T, E> {
	
	public abstract T visit(Node<E> node);
	
}
