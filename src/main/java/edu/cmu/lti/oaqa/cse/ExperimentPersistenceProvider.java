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

import java.util.Set;

import org.apache.uima.resource.Resource;

public interface ExperimentPersistenceProvider extends Resource {
  void insertExperiment(String id, String name, String author,
         String configuration, String resource) throws Exception;
  void updateExperimentMeta(String experimentId, int size);
  void updateExperimentMeta(String experimentId, int size, Set<String> topics);
}
