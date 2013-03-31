package edu.cmu.lti.oaqa.cse.configuration;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.annotation.Nullable;

import org.yaml.snakeyaml.Yaml;

import com.google.common.base.Predicate;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

public class YAMLParser extends Parser {

	private Map<String, Object> yamlMap;
	private static final Set<String> FILTER_SET = Sets.newHashSet("inherit",
			"class");
	private static final Predicate<String> FILTER_PREDICATE = new Predicate<String>() {

		@Override
		public boolean apply(@Nullable String s) {
			if (FILTER_SET.contains(s))
				return false;
			return true;
		}

	};

	public YAMLParser(String resource) {
		super(resource);
		// Get configuration file from classpath.
		InputStream input = this.getClass().getClassLoader()
				.getResourceAsStream(resource);

		Yaml yaml = new Yaml();

		if (input != null) { // is file available?
			yamlMap = (Map<String, Object>) yaml.load(input);
			System.out.println(yamlMap);
		} else {
			System.err.println("File: " + resource + " not found");
		}

	}

	@Override
	public CollectionReaderDescriptor buildCollectionReader() {
		Map<String, Object> resMap = (Map<String, Object>) yamlMap
				.get("collection-reader");
		resMap = flatten(resMap);
		System.out.println(resMap);
		String className = (String) resMap.get("class");
		resMap = Maps.filterKeys(resMap, FILTER_PREDICATE);
		CollectionReaderDescriptor crd = new CollectionReaderDescriptor(className,resMap);

		return null;
	}

	@Override
	public PipelineDescriptor buildPipelineDescriptor() {
		// TODO Auto-generated method stub
		return null;
	}

	private Map<String, String> getConfiguration() {
		return (Map<String, String>) yamlMap.get("configuration");
	}

	@Override
	public String getName() {
		return getConfiguration().get("name");
	}

	@Override
	public String getAuthor() {
		return getConfiguration().get("author");
	}

	/**
	 * Takes the specified yaml component and flattens the "inherits" therin so
	 * that it only contains the "class" and associated parameters.
	 * 
	 * @param resMap
	 * @return
	 */
	private Map<String, Object> flatten(Map<String, Object> resMap) {
		for (String key : resMap.keySet()) {
			if (key.equals("inherit"))
				return combineMaps(resMap,
						flatten((Map<String, Object>) resMap.get("inherit")));
		}
		return resMap;
	}

	private static <K, V> Map<K, V> combineMaps(Map<K, V> map1,
			Map<? extends K, ? extends V> map2) {
		map1.putAll(map2);
		return map1;
	}

	public static void main(String[] args) {
		String resource = args[0];
		Parser parser = new YAMLParser(resource);
	}

}
