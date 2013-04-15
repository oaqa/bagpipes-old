package edu.cmu.lti.oaqa.cse.space.exploration;

import static java.util.Arrays.asList;

import java.util.Collections;

import org.apache.uima.cas.CAS;
import org.apache.uima.util.CasCreationUtils;

import com.google.common.collect.Lists;

import edu.cmu.lti.oaqa.components.ExecutableComponent;
import edu.cmu.lti.oaqa.cse.space.tree.Node;
import edu.cmu.lti.oaqa.cse.space.tree.Tree;

public class TSimpleExplorationStrategy<T, E extends ExecutableComponent<T>> extends
        ExplorationStrategy<T, E> {

  private Node<E> curNode;

  private Node<E> prevNode;

  private T input;
  
  public TSimpleExplorationStrategy(T input) {
    this.input = input;
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

}
