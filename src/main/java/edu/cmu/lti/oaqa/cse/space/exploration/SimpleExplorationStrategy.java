package edu.cmu.lti.oaqa.cse.space.exploration;

import edu.cmu.lti.oaqa.components.ExecutableComponent;
import edu.cmu.lti.oaqa.components.simple.SimpleClassNameAnnotator;
import edu.cmu.lti.oaqa.cse.space.tree.Node;
import edu.cmu.lti.oaqa.cse.space.tree.NodeVisitor;
import edu.cmu.lti.oaqa.cse.space.tree.Tree;

public class SimpleExplorationStrategy extends
		ExplorationStrategy<String, SimpleClassNameAnnotator> {

	private Node<SimpleClassNameAnnotator> curNode, prevNode;

	@Override
	public String getNext() {
		prevNode = curNode;
		curNode = curNode.hasChildren()? curNode.getChildren().get(0): curNode;
		return prevNode.getData().execute("");
	}

	@Override
	public boolean hasNext() {
		return prevNode.hasChildren();
	}

	@Override
	public void setTree(Tree<SimpleClassNameAnnotator> tree) {
		super.setTree(tree);
		prevNode = curNode = root;
	}

}
