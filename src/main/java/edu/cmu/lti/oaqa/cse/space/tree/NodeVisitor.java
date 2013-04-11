package edu.cmu.lti.oaqa.cse.space.tree;

public interface NodeVisitor<T> {
	
	public void visit(Node<T> node);
	
}
