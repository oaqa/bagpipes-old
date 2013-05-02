package edu.cmu.lti.oaqa.cse.space.exploration;

import static java.util.Arrays.asList;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import org.apache.uima.cas.CAS;
import org.apache.uima.util.CasCreationUtils;

import com.google.common.collect.Lists;

import edu.cmu.lti.oaqa.components.ExecutableComponent;
import edu.cmu.lti.oaqa.components.simple.SimpleClassNameAnnotator;
import edu.cmu.lti.oaqa.cse.configuration.ExplorerDescriptor;
import edu.cmu.lti.oaqa.cse.configuration.ScoreDescriptor;
import edu.cmu.lti.oaqa.cse.space.tree.Node;
import edu.cmu.lti.oaqa.cse.space.tree.Tree;

public class ThresholdExplorationStrategy<T, E extends ExecutableComponent<T>>
		extends ExplorationStrategy<T, E> {
	private Node<E> nextNode, curNode;
	private Stack<List<Node<E>>> traversalStack;
	private double minBenefit = 0, maxCost = Integer.MAX_VALUE;
	List<Node<E>> toBeTraversed;

	public ThresholdExplorationStrategy() {
		toBeTraversed = Collections.EMPTY_LIST;
		traversalStack = new Stack<List<Node<E>>>();

	}

	public ThresholdExplorationStrategy(ExplorerDescriptor explorerDesc) {
		super(explorerDesc);
		toBeTraversed = Collections.EMPTY_LIST;
		traversalStack = new Stack<List<Node<E>>>();
		System.out.println(explorerDesc);
	//	minBenefit = explorerDesc.getDouble("minBenefit");
		//maxCost = explorerDesc.getDouble("maxCost");
	}

	@Override
	public Node<E> getNextNode() {
		// Move pointer to next node
		curNode = nextNode;
		List<Node<E>> children = Lists.newLinkedList();
		// If node has children, pre-order recurse on child
		if (nextNode.hasChildren()) {
			children.addAll(nextNode.getChildren());
			nextNode = firstOrNext(children);
			// if children are left, remember to visit them later
			if (!children.isEmpty())
				traversalStack.push(children);
			// traverse all siblings of node after visiting the subtree
		} else if (!toBeTraversed.isEmpty())
			nextNode = firstOrNext(sort(toBeTraversed));
		else {
			// If you already traversed all the siblings, go back up the tree
			if (!traversalStack.isEmpty())
				toBeTraversed = traversalStack.pop();
			// Store first node from the stack, but
			// NOTE: will return null if no nodes are left
			// and cause hasNext() to return false. This is how
			// getNext() signals the end of execution.
			nextNode = firstOrNext(sort(toBeTraversed));
		}

		System.out.println(curNode);
		return curNode;
	}
	
	private   List<Node<E>> sort(List<Node<E>> list){
		  Collections.sort(list, new ThresholdComparator(scoreMap));
		  return list;
    }

	private class ThresholdComparator<T, E extends ExecutableComponent<T>> implements Comparator<Node<E>>{

		private Map<String,ScoreDescriptor> scoreMap;
	public ThresholdComparator(Map<String,ScoreDescriptor> scoreMap){
		this.scoreMap = scoreMap;
	}
		@Override
		public int compare(Node<E> o1, Node<E> o2) {
			double thisRatio = computeRatio(o1);
			double thatRatio = computeRatio(o2);
			if(thisRatio < thatRatio)
				return 1;
			if(thisRatio<thatRatio)
				return -1;
			else return 0;
			
		}
	
		private double computeRatio(Node<E> o1){
			double thisBenefit = scoreMap.get(o1.getElement().getClassName()).getBenefit();
			double thisCost = scoreMap.get(o1.getElement().getClassName()).getCost();
			return thisBenefit/thisCost;
		}
	}
	
	private boolean condition(Node<E> n) {
	
		return true;
	}

	private Node<E> firstOrNext(List<Node<E>> nodes) {
		if (nodes.isEmpty())
			return null;

		Node<E> n = nodes.remove(0);

		if (condition(n))
			return n;

		return firstOrNext(nodes);

	}

	@Override
	public boolean hasNext() {
		return nextNode != null
				&& (nextNode.hasChildren() || curNode != null
						|| !traversalStack.isEmpty() || !toBeTraversed
							.isEmpty());

	}

	@Override
	public void setTree(Tree<E> tree) {
		super.setTree(tree);
		curNode = nextNode = root;
	}

}
