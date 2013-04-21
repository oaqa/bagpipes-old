package edu.cmu.lti.oaqa.cse.space.exploration;

import java.util.Map;

import com.google.common.collect.Maps;

import edu.cmu.lti.oaqa.components.ExecutableComponent;
import edu.cmu.lti.oaqa.cse.configuration.ExplorerDescriptor;
import edu.cmu.lti.oaqa.cse.configuration.ScoreDescriptor;
import edu.cmu.lti.oaqa.cse.space.tree.Node;
import edu.cmu.lti.oaqa.cse.space.tree.NodeVisitor;
import edu.cmu.lti.oaqa.cse.space.tree.Tree;

public abstract class ExplorationStrategy<T, E extends ExecutableComponent<T>> {

	protected Tree<E> phaseTree;
	protected NodeVisitor<T, ? extends ExecutableComponent<T>> visitor;
	protected Node<E> root;
	protected Map<String, ScoreDescriptor> scoreMap;
	protected Map<Node<E>, T> inputMap;

	// protected Map<String, Object> paramMap;

	public final T getNext() throws Exception {
		Node<E> curNode = getNextNode();

		// Compute execution of current node and cache as input
		// for all its child components
		T result = execute(curNode, inputMap.get(curNode));
		if (curNode.hasChildren())
			for (Node<E> child : curNode.getChildren())
				inputMap.put(child, result);

		return result;
	}

	public abstract boolean hasNext();

	public ExplorationStrategy(ExplorerDescriptor explorerDesc) {
		this();
		
	}

	public ExplorationStrategy() {
		inputMap = Maps.newHashMap();
	}

	public void setNodeVisitor(
			NodeVisitor<T, ? extends ExecutableComponent<T>> visitor) {
		this.visitor = visitor;
	}

	public void setTree(Tree<E> tree) {
		phaseTree = tree;
		root = tree.getRoot();
	}

	public void setScoreMap(Map<String, ScoreDescriptor> scoreMap) {
		this.scoreMap = scoreMap;
	}

	protected abstract Node<E> getNextNode();

	protected T execute(Node<E> node, T input) {
		try {
			return node.getElement().execute(input);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
