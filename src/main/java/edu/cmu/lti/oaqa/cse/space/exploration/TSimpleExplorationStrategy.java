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

<<<<<<< HEAD
public class TSimpleExplorationStrategy<T, E extends ExecutableComponent<T>>
		extends ExplorationStrategy<T, E> {
	private Node<E> nextNode, curNode;
	private Stack<List<Node<E>>> traversalStack;

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
			nextNode = children.remove(0);
			if (!children.isEmpty())
				traversalStack.push(children);
		} else if (toBeTraversed.isEmpty() && !traversalStack.isEmpty()) {
			toBeTraversed = traversalStack.pop();
			nextNode = toBeTraversed.remove(0);
		} else if (!toBeTraversed.isEmpty())
			nextNode = toBeTraversed.remove(0);
		
		
		T result;
		if (curNode != phaseTree.getRoot()) {
			result = execute(curNode, inputMap.get(curNode));
			if (curNode.hasChildren())
				for (Node<E> child : curNode.getChildren())
					inputMap.put(child, result);
		} else
			result = execute(curNode, null);
		return result;
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
=======
public class TSimpleExplorationStrategy<T, E extends ExecutableComponent<T>> extends
        ExplorationStrategy<T, E> {

  private Node<E> curNode;

  private Node<E> prevNode;

  private T input;
  
  public TSimpleExplorationStrategy() {
  }
  
  @Override
  public T getNext() throws Exception {
    prevNode = curNode;
    curNode = curNode.hasChildren() ? curNode.getChildren().get(0) : curNode;
    T out = prevNode.getData().execute(input);
    input = out;
    return out;
  }

  @Override
  public boolean hasNext() {
    return prevNode.hasChildren();
  }

  @Override
  public void setTree(Tree<E> tree) {
    super.setTree(tree);
    prevNode = curNode = root;
  }
>>>>>>> 7c860750542831d5749afafcf72147c591f3a548

}
