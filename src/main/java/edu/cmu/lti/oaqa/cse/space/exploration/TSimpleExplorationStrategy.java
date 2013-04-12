package edu.cmu.lti.oaqa.cse.space.exploration;

import edu.cmu.lti.oaqa.components.ExecutableComponent;
import edu.cmu.lti.oaqa.cse.space.tree.Node;
import edu.cmu.lti.oaqa.cse.space.tree.Tree;

public class TSimpleExplorationStrategy<T, E extends ExecutableComponent<T>> extends
        ExplorationStrategy<T, E> {

  private Node<E> curNode;

  private Node<E> prevNode;

  @Override
  public T getNext() {
    prevNode = curNode;
    curNode = curNode.hasChildren() ? curNode.getChildren().get(0) : curNode;
    return prevNode.getData().execute(null);
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

}
