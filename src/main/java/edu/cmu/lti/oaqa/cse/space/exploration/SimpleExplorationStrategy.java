package edu.cmu.lti.oaqa.cse.space.exploration;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

import com.google.common.collect.Lists;

import edu.cmu.lti.oaqa.components.ExecutableComponent;
import edu.cmu.lti.oaqa.components.simple.SimpleClassNameAnnotator;
import edu.cmu.lti.oaqa.cse.configuration.ScoreDescriptor;
import edu.cmu.lti.oaqa.cse.space.tree.Node;
import edu.cmu.lti.oaqa.cse.space.tree.NodeVisitor;
import edu.cmu.lti.oaqa.cse.space.tree.Tree;

public class SimpleExplorationStrategy extends
		ExplorationStrategy<String, SimpleClassNameAnnotator> {
	private Node<SimpleClassNameAnnotator> nextNode, curNode;
	private Stack<List<Node<SimpleClassNameAnnotator>>> traversalStack;
	List<Node<SimpleClassNameAnnotator>> toBeTraversed;

	public SimpleExplorationStrategy() {
		toBeTraversed = Collections.EMPTY_LIST;
		traversalStack = new Stack<List<Node<SimpleClassNameAnnotator>>>();
	}

	@Override
	public String getNext() {
		curNode = nextNode;
		List<Node<SimpleClassNameAnnotator>> children = Lists.newLinkedList();
		if (nextNode.hasChildren()) {
			children.addAll(nextNode.getChildren());
			nextNode = children.remove(0);
			if (!children.isEmpty())
				traversalStack.push(children);
		} else if (toBeTraversed.isEmpty() && !traversalStack.isEmpty()) {
			toBeTraversed = traversalStack.pop();
			nextNode = toBeTraversed.remove(0);
		} else if (!toBeTraversed.isEmpty())
			nextNode = toBeTraversed.remove(0);
		return execute(curNode, "");
	}

	private static final String execute(Node<SimpleClassNameAnnotator> node,
			String input) {
		return node.getElement().execute("");
	}

	@Override
	public boolean hasNext() {
		return curNode.hasChildren() || nextNode.hasChildren()
				|| !traversalStack.isEmpty() || !toBeTraversed.isEmpty();
	}

	@Override
	public void setTree(Tree<SimpleClassNameAnnotator> tree) {
		super.setTree(tree);
		curNode = nextNode = root;
	}
	
	

}
