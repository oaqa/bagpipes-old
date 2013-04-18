package edu.cmu.lti.oaqa.cse.configuration;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;

import edu.cmu.lti.oaqa.cse.AnalysisEngineDescriptor;

public class Configuration {

	private String name, author;
	private PersistenceProviderDescriptor ppDesc;
	private CollectionReaderDescriptor collectionReaderDesc;
	private PipelineDescriptor pipelineDesc;
	private List<ConsumerDescriptor> consumerDescs;
	private Map<String, ScoreDescriptor> scoreMap;
	private ExplorerDescriptor explorerDesc;

	private Configuration(String name, String author) {
		this.name = name;
		this.author = author;
	}

	public Configuration(String name, String author,
			CollectionReaderDescriptor crDesc, PipelineDescriptor plDesc) {
		this(name, author);
		this.collectionReaderDesc = crDesc;
		this.pipelineDesc = plDesc;
	}

	public Configuration(String name, String author,
			CollectionReaderDescriptor crDesc, PipelineDescriptor plDesc,
			List<ConsumerDescriptor> consumers) {
		this(name, author, crDesc, plDesc);
		this.consumerDescs = consumers;
	}

	public Configuration(CollectionReaderDescriptor crDesc,
			PipelineDescriptor plDesc) {
		this("oaqa-experiment", "oaqa-author", crDesc, plDesc);
	}

	public void setConsumers(List<ConsumerDescriptor> consumerDescs) {
		this.consumerDescs = consumerDescs;
	}

	public void setScoreMap(Map<String, ScoreDescriptor> scoreMap) {
		this.scoreMap = scoreMap;
	}

	public void setExplorerDescriptor(ExplorerDescriptor expDesc) {
		this.explorerDesc = expDesc;
	}

	public String getAuthor() {
		return this.author;
	}

	public String getName() {
		return name;
	}

	public CollectionReaderDescriptor getCollectionReaderDescriptor() {
		return collectionReaderDesc;
	}

	public PipelineDescriptor getPipelineDescriptor() {
		return pipelineDesc;
	}

	public List<ConsumerDescriptor> getConsumers() {
		return consumerDescs;
	}

	public Map<String, ScoreDescriptor> getScores() {
		return scoreMap;
	}
	
	public ExplorerDescriptor getExplorationDescriptor(){
		return explorerDesc;
	}

	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}
		if (obj == null || obj.getClass() != this.getClass()) {
			return false;
		}
		return equals((Configuration) obj);
	}

	public boolean equals(Configuration conf) {
		boolean equal = true;
		equal &= this.getName().equals(conf.getName());
		System.out.println("name: " + equal);
		equal &= this.getAuthor().equals(conf.getAuthor());
		System.out.println("author: " + equal);
		equal &= this.collectionReaderDesc.equals(conf
				.getCollectionReaderDescriptor());
		System.out.println("collectionReader: " + equal);
		equal &= this.pipelineDesc.equals(conf.getPipelineDescriptor());
		System.out.println("plDesc: " + equal);
		equal &= this.consumerDescs.equals(conf.getConsumers());
		System.out.println("consumer1: " + consumerDescs + "consumer2: "
				+ conf.getConsumers() + "equal?: " + equal);
		return equal;
	}

}
