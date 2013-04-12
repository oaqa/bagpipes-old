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

package edu.cmu.lti.oaqa.cse.space;

import java.util.Iterator;
import java.util.List;

import edu.cmu.lti.oaqa.components.ExecutableComponent;
import edu.cmu.lti.oaqa.components.Factory;
import edu.cmu.lti.oaqa.cse.configuration.CollectionReaderDescriptor;
import edu.cmu.lti.oaqa.cse.configuration.Configuration;
import edu.cmu.lti.oaqa.cse.configuration.ConsumerDescriptor;
import edu.cmu.lti.oaqa.cse.configuration.OptionDescriptor;
import edu.cmu.lti.oaqa.cse.configuration.PhaseDescriptor;
import edu.cmu.lti.oaqa.cse.configuration.PipelineDescriptor;
import edu.cmu.lti.oaqa.cse.space.exploration.ExplorationStrategy;
import edu.cmu.lti.oaqa.cse.space.tree.Node;
import edu.cmu.lti.oaqa.cse.space.tree.Tree;
import edu.cmu.lti.oaqa.cse.configuration.ComponentDescriptor;

public abstract class ConfigurationSpace<T, E extends ExecutableComponent<T>>
		implements Iterable<T> {

	private ExplorationStrategy<T, E> strategy;

	protected final Configuration conf;

	private Tree<E> phaseTree;
	private Factory<T, E> componentFactory;

	public ConfigurationSpace(Configuration conf) throws Exception {
		this.conf = conf;
      this.componentFactory = getFactory();
		this.phaseTree = getTree();
		initTree();
		// typeSystem =null;
	}

	public Tree<E> getPhaseTree() {
		return phaseTree;
	}

	private void initTree() throws Exception {
		// Set the collection reader as root of the phaseTree
		Node<E> root = buildCollectionReaderNode();
		phaseTree.setRoot(root);

		// Build all the options in the phases from the pipeline descriptor
		PipelineDescriptor plDesc = conf.getPipelineDescriptor();
		buildPhases(plDesc);
	}

	private Node<E> buildCollectionReaderNode() {
		CollectionReaderDescriptor crDesc = conf
				.getCollectionReaderDescriptor();
		Node<E> collectionReaderNode = createNode(crDesc);
		return collectionReaderNode;
	}

	private void buildPhases(PipelineDescriptor plDesc) throws Exception {
		List<PhaseDescriptor> phaseDescs = plDesc.getPhaseDescriptors();
		for (PhaseDescriptor phaseDesc : phaseDescs)
			buildPhase(phaseDesc);
	}

	private void buildPhase(PhaseDescriptor pd) throws Exception {
		List<OptionDescriptor> optionDescs = pd.getOptionDescriptors();
		for (OptionDescriptor optionDesc : optionDescs)
			// IMPORTANT: this method essentially adds the next phase to the
			// execution tree by taking all the options in the phase
			// and adding it to the leaves from the previous phase.
			phaseTree.addToLeaves(createNode(optionDesc));
	}

	private void buildConsumers() throws Exception {
		List<ConsumerDescriptor> consumerDescs = conf.getConsumers();
		for (ConsumerDescriptor consumerDesc : consumerDescs)
			phaseTree.addToLeaves(createNode(consumerDesc));
	}

	private Node<E> createNode(ComponentDescriptor cd) throws Exception {
		return new Node<E>(componentFactory.createExecutableComponent(cd));
	}
	
  private Node<E> createNode(OptionDescriptor cd) throws Exception {
    return new Node<E>(componentFactory.createExecutableComponent(cd));
  }
  
  private Node<E> createNode(CollectionReaderDescriptor cd) {
    return new Node<E>(componentFactory.createExecutableComponent(cd));
  }

	protected abstract Tree<E> getTree();

	protected abstract Factory<T, E> getFactory() throws Exception;

	@Override
	public Iterator<T> iterator() {
		return new PipelineIterator(conf);
	}

	public void setExplorationStrategy(
			ExplorationStrategy<T, E> explorationStrategy) {
		explorationStrategy.setTree(this.phaseTree);
		System.out.println(phaseTree);
		this.strategy = explorationStrategy;
	}

	public class PipelineIterator implements Iterator<T> {

		public PipelineIterator(Configuration conf) {
			// List<Phase> phases = conf.getPhases();
		}

		@Override
		public boolean hasNext() {
			return strategy.hasNext();
		}

		@Override
		public T next() {
			try {
        return strategy.getNext();
      } catch (Exception e) {
        // TODO Auto-generated catch block
        throw new RuntimeException(e);
      }
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}
	}

}
