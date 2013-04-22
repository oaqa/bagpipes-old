package edu.cmu.lti.oaqa.cse.space.exploration;

import edu.cmu.lti.oaqa.components.ExecutableComponent;
import edu.cmu.lti.oaqa.cse.configuration.ExplorerDescriptor;
import edu.cmu.lti.oaqa.cse.space.tree.Node;
import edu.cmu.lti.oaqa.cse.space.tree.Tree;

public class RandomExplorationStrategy<T, E extends ExecutableComponent<T>>
		extends ExplorationStrategy<T, E> {

	public RandomExplorationStrategy(ExplorerDescriptor explorerDesc) {
		super(explorerDesc);
	}

	Node<E> curNode, nextNode;

	@Override
	public boolean hasNext() {
		// TODO Auto-generated method stub
		return nextNode != null;
	}

	@Override
	protected final Node<E> getNextNode() {
		curNode = nextNode;
		if (nextNode.hasChildren())
			nextNode = nextNode.getChildAt((int)Math.round( Math.random()
					* (nextNode.getChildren().size() - 1)));
		else
			nextNode = null;
		return curNode;
	}

	public void setTree(Tree<E> tree) {
		super.setTree(tree);
		curNode = nextNode = root;
	}

}
