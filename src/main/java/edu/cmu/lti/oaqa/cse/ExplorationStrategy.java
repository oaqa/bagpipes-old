package edu.cmu.lti.oaqa.cse;

import java.util.Iterator;

public interface ExplorationStrategy<T> {

  Iterator<T> getSpaceIterator();

}
