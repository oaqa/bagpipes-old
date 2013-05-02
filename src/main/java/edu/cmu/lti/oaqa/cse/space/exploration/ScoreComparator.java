package edu.cmu.lti.oaqa.cse.space.exploration;

import java.util.Comparator;
import java.util.Map;

import edu.cmu.lti.oaqa.components.ExecutableComponent;
import edu.cmu.lti.oaqa.cse.configuration.ScoreDescriptor;
import edu.cmu.lti.oaqa.cse.space.tree.Node;

public class ScoreComparator<T, E extends ExecutableComponent<T>> implements Comparator<Node<E>> {

  private Map<String, ScoreDescriptor> scoreMap;

  public ScoreComparator(Map<String, ScoreDescriptor> map) {
    this.scoreMap = map;
  }

  @Override
  public int compare(Node<E> n1, Node<E> n2) {
    String id1 = n1.getElement().getClassName();
    String id2 = n2.getElement().getClassName();
    if (scoreMap.get(id1) == null)
      return 1;
    if (scoreMap.get(id2) == null)
      return -1;
    return scoreMap.get(id1).compareTo(scoreMap.get(id2));
  }

}