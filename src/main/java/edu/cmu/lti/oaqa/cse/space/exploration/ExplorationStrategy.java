package edu.cmu.lti.oaqa.cse.space.exploration;

import edu.cmu.lti.oaqa.components.ExecutableComponent;
import edu.cmu.lti.oaqa.cse.space.tree.Node;
import edu.cmu.lti.oaqa.cse.space.tree.NodeVisitor;
import edu.cmu.lti.oaqa.cse.space.tree.Tree;

public abstract class ExplorationStrategy<T,E extends ExecutableComponent<T>> {
	
	Tree<E> phaseTree;
	NodeVisitor<E> visitor;
	public ExplorationStrategy(Tree<E> tree){
		this.phaseTree =tree;
	}
	
	public abstract Node<E> getNext();
	public abstract boolean hasNext();
	public abstract NodeVisitor<E> getNodeVisitor();
	
	
}
