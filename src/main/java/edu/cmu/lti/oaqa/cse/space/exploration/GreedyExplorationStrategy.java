package edu.cmu.lti.oaqa.cse.space.exploration;

import java.util.Comparator;

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
	public T getNext() {
		curNode = nextNode;
		
		
		
		
		
		T input = inputMap.get(curNode);
		return execute(curNode,input);
	}

	@Override
	public boolean hasNext() {
		// TODO Auto-generated method stub
		return false;
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
