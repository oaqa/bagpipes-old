package edu.cmu.lti.oaqa.cse.space.exploration;

import edu.cmu.lti.oaqa.components.ExecutableComponent;
import edu.cmu.lti.oaqa.cse.space.tree.Node;
import edu.cmu.lti.oaqa.cse.space.tree.NodeVisitor;
import edu.cmu.lti.oaqa.cse.space.tree.Tree;

public abstract class ExplorationStrategy<T, E extends ExecutableComponent<T>> {

	protected Tree<E> phaseTree;
	protected NodeVisitor<T,? extends ExecutableComponent<T>> visitor;
	protected Node<E> root;
	
	public abstract T getNext();

	public abstract boolean hasNext();

	public void setNodeVisitor(NodeVisitor<T,? extends ExecutableComponent<T>> visitor) {
		this.visitor = visitor;
	}

	public void setTree(Tree<E> tree) {
		phaseTree = tree;
		root = tree.getRoot();
	}

}
