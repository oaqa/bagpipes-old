package edu.cmu.lti.oaqa.cse.space.exploration;

import static java.util.Arrays.asList;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import org.apache.uima.cas.CAS;
import org.apache.uima.util.CasCreationUtils;

import com.google.common.collect.Lists;

import edu.cmu.lti.oaqa.components.ExecutableComponent;
import edu.cmu.lti.oaqa.components.simple.SimpleClassNameAnnotator;
import edu.cmu.lti.oaqa.cse.space.tree.Node;
import edu.cmu.lti.oaqa.cse.space.tree.Tree;

public class TSimpleExplorationStrategy<T, E extends ExecutableComponent<T>>
		extends ExplorationStrategy<T, E> {
	private Node<E> nextNode, curNode;
	private Stack<List<Node<E>>> traversalStack;
	private double minBenefit = 0.6, maxCost = 1;

	List<Node<E>> toBeTraversed;

	public TSimpleExplorationStrategy() {
		toBeTraversed = Collections.EMPTY_LIST;
		traversalStack = new Stack<List<Node<E>>>();
	}

	@Override
	public T getNext() {
		curNode = nextNode;
		List<Node<E>> children = Lists.newLinkedList();
		if (nextNode.hasChildren()) {
			children.addAll(nextNode.getChildren());
			nextNode = thisOrNext(children);
			if (!children.isEmpty())
				traversalStack.push(children);
		} else if (toBeTraversed.isEmpty() && !traversalStack.isEmpty()) {
			toBeTraversed = traversalStack.pop();
			nextNode = thisOrNext(toBeTraversed);
		} else if (!toBeTraversed.isEmpty())
			nextNode = thisOrNext(toBeTraversed);

		T result = execute(curNode, inputMap.get(curNode));
		if (curNode.hasChildren()) {
			for (Node<E> child : curNode.getChildren()) {
				inputMap.put(child, result);
			}
		}
		return result;
	}

	private boolean condition(Node<E> n) {
		if (scoreMap.containsKey(n.getElement().getClassName())) {
			double benefit = scoreMap.get(n.getElement().getClassName())
					.getBenefit();
			double cost = scoreMap.get(n.getElement().getClassName()).getCost();
			return benefit > minBenefit && cost < maxCost;
		}

		return true;
	}

	private Node<E> thisOrNext(List<Node<E>> nodes) {
		if (nodes.isEmpty())
			return null;

		if (condition(nodes.get(0)))
			return nodes.remove(0);

		nodes.remove(0);
		return thisOrNext(nodes);

	}

	private T execute(Node<E> node, T input) {
		try {
			return node.getElement().execute(input);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean hasNext() {
		return curNode.hasChildren() || nextNode.hasChildren()
				|| !traversalStack.isEmpty() || !toBeTraversed.isEmpty();
	}

	@Override
	public void setTree(Tree<E> tree) {
		super.setTree(tree);
		curNode = nextNode = root;
	}

}
