package edu.cmu.lti.oaqa.cse.space.exploration;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import edu.cmu.lti.oaqa.components.ExecutableComponent;
import edu.cmu.lti.oaqa.cse.configuration.ExplorerDescriptor;
import edu.cmu.lti.oaqa.cse.space.tree.Node;
import edu.cmu.lti.oaqa.cse.space.tree.Tree;

public class GreedyExplorationStrategy<T, E extends ExecutableComponent<T>>
		extends ExplorationStrategy<T, E> {

	private Node<E> curNode, nextNode;

	public GreedyExplorationStrategy(ExplorerDescriptor explorerDesc) {
		super(explorerDesc);

	}

	@Override
	public Node<E> getNextNode() {
		curNode = nextNode;
		if (nextNode.hasChildren()) {
			List<Node<E>> children = nextNode.getChildren();
			Collections.sort(children,new ScoreComparator());
			nextNode = children.get(children.size()-1);
		}
		return curNode;
	}

	@Override
	public boolean hasNext() {
		return nextNode != curNode;
	}

	public void setTree(Tree<E> tree) {
		super.setTree(tree);
		curNode = nextNode = root;
	}

	private class ScoreComparator implements Comparator<Node<E>> {

		@Override
		public int compare(Node<E> n1, Node<E> n2) {
			return scoreMap.get(n1).compareTo(scoreMap.get(n2));
		}

	}

}
