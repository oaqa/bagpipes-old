/*
 *  Copyright 2012 Carnegie Mellon University
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package edu.cmu.lti.oaqa.cse;

import java.util.Iterator;


public final class ConfigurationSpace<T> implements Iterable<T> {

  private ExplorationStrategy strategy;
  
  private final Configuration conf;

  public ConfigurationSpace(Configuration conf) throws Exception {
    this.conf = conf;
  }

  @Override
  public Iterator<T> iterator() {
    return strategy.getSpaceIterator();
  }

  public void setExplorationStrategy(ExplorationStrategy strategy) {
    this.strategy = strategy;
  }
  
 

}
