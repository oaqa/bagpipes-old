package edu.cmu.lti.oaqa.cse.space.tree;

import java.util.*;

import com.google.common.collect.Lists;

public class Tree<T> {

	public enum GenericTreeTraversalOrderEnum {
		PRE_ORDER, POST_ORDER
	}

	private Node<T> root;
	private List<Node<T>> leaves;

	public Tree() {
		super();
		leaves = Lists.newArrayList();
	}

	public Node<T> getRoot() {
		return this.root;
	}

	public void setRoot(Node<T> root) {
		if (root != null)
			leaves.add(root);
		this.root = root;
	}

	public void addToLeaves(Node<T>... nodes) {
		List<Node<T>> newLeaves = Lists.newArrayList();
		for (Node<T> newLeaf : nodes)
			for (Node<T> oldLeaf : leaves)
				addToLeaf(oldLeaf, newLeaf, newLeaves);
		leaves = newLeaves;
	}

	private void addToLeaf(Node<T> n1, Node<T> n2, List<Node<T>> newLeaves) {
		newLeaves.add(n2);
		n1.addChild(n2);
	}

	public int getNumberOfNodes() {
		int numberOfNodes = 0;

		if (root != null) {
			numberOfNodes = auxiliaryGetNumberOfNodes(root) + 1; // 1 for the
																	// root!
		}

		return numberOfNodes;
	}

	private int auxiliaryGetNumberOfNodes(Node<T> node) {
		int numberOfNodes = node.getNumberOfChildren();

		for (Node<T> child : node.getChildren()) {
			numberOfNodes += auxiliaryGetNumberOfNodes(child);
		}

		return numberOfNodes;
	}

	public boolean exists(Node<T> nodeToFind) {
		return (find(nodeToFind) != null);
	}

	public Node<T> find(Node<T> nodeToFind) {
		Node<T> returnNode = null;

		if (root != null) {
			returnNode = auxiliaryFind(root, nodeToFind);
		}

		return returnNode;
	}

	private Node<T> auxiliaryFind(Node<T> currentNode, Node<T> nodeToFind) {
		Node<T> returnNode = null;
		int i = 0;

		if (currentNode.equals(nodeToFind)) {
			returnNode = currentNode;
		}

		else if (currentNode.hasChildren()) {
			i = 0;
			while (returnNode == null && i < currentNode.getNumberOfChildren()) {
				returnNode = auxiliaryFind(currentNode.getChildAt(i),
						nodeToFind);
				i++;
			}
		}

		return returnNode;
	}

	public boolean isEmpty() {
		return (root == null);
	}

	public List<Node<T>> build(GenericTreeTraversalOrderEnum traversalOrder) {
		List<Node<T>> returnList = null;

		if (root != null) {
			returnList = build(root, traversalOrder);
		}

		return returnList;
	}

	public List<Node<T>> build(Node<T> node,
			GenericTreeTraversalOrderEnum traversalOrder) {
		List<Node<T>> traversalResult = new ArrayList<Node<T>>();

		if (traversalOrder == GenericTreeTraversalOrderEnum.PRE_ORDER) {
			buildPreOrder(node, traversalResult);
		}

		else if (traversalOrder == GenericTreeTraversalOrderEnum.POST_ORDER) {
			buildPostOrder(node, traversalResult);
		}

		return traversalResult;
	}

	private void buildPreOrder(Node<T> node, List<Node<T>> traversalResult) {
		traversalResult.add(node);

		for (Node<T> child : node.getChildren()) {
			buildPreOrder(child, traversalResult);
		}
	}

	private void buildPostOrder(Node<T> node, List<Node<T>> traversalResult) {
		for (Node<T> child : node.getChildren()) {
			buildPostOrder(child, traversalResult);
		}

		traversalResult.add(node);
	}

	public Map<Node<T>, Integer> buildWithDepth(
			GenericTreeTraversalOrderEnum traversalOrder) {
		Map<Node<T>, Integer> returnMap = null;

		if (root != null) {
			returnMap = buildWithDepth(root, traversalOrder);
		}

		return returnMap;
	}

	public Map<Node<T>, Integer> buildWithDepth(Node<T> node,
			GenericTreeTraversalOrderEnum traversalOrder) {
		Map<Node<T>, Integer> traversalResult = new LinkedHashMap<Node<T>, Integer>();

		if (traversalOrder == GenericTreeTraversalOrderEnum.PRE_ORDER) {
			buildPreOrderWithDepth(node, traversalResult, 0);
		}

		else if (traversalOrder == GenericTreeTraversalOrderEnum.POST_ORDER) {
			buildPostOrderWithDepth(node, traversalResult, 0);
		}

		return traversalResult;
	}

	private void buildPreOrderWithDepth(Node<T> node,
			Map<Node<T>, Integer> traversalResult, int depth) {
		traversalResult.put(node, depth);

		for (Node<T> child : node.getChildren()) {
			buildPreOrderWithDepth(child, traversalResult, depth + 1);
		}
	}

	private void buildPostOrderWithDepth(Node<T> node,
			Map<Node<T>, Integer> traversalResult, int depth) {
		for (Node<T> child : node.getChildren()) {
			buildPostOrderWithDepth(child, traversalResult, depth + 1);
		}

		traversalResult.put(node, depth);
	}

	public String toString() {
		/*
		 * We're going to assume a pre-order traversal by default
		 */

		String stringRepresentation = "";

		if (root != null) {
			stringRepresentation = build(
					GenericTreeTraversalOrderEnum.PRE_ORDER).toString();

		}

		return stringRepresentation;
	}

	public String toStringWithDepth() {
		/*
		 * We're going to assume a pre-order traversal by default
		 */

		String stringRepresentation = "";

		if (root != null) {
			stringRepresentation = buildWithDepth(
					GenericTreeTraversalOrderEnum.PRE_ORDER).toString();
		}

		return stringRepresentation;
	}
}