package edu.cmu.lti.oaqa.cse.configuration;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.yaml.snakeyaml.Yaml;

public class YAMLParser extends Parser {

	public YAMLParser(String resource) {
		super(resource);
	}

	/**
	 * 
	 */
	@Override
	public Map<String, Object> getResMap(String resource)
			throws FileNotFoundException {
		// Get configuration file from classpath.
		Map<String, Object> resMap;

		InputStream input = this.getClass().getClassLoader()
				.getResourceAsStream(resource);

		Yaml yaml = new Yaml();

		if (input != null) { // is file available?
			resMap = (Map<String, Object>) yaml.load(input);
			System.out.println(resMap);
			return resMap;
		}

		System.err.println("File: " + resource + " not found");
		throw new FileNotFoundException();
	}

	/**
	 * 
	 */

	@Override
	public CollectionReaderDescriptor buildCollectionReader() {
		Map<String, Object> resMap = (Map<String, Object>) confMap
				.get("collection-reader");
		resMap = flatten(resMap);
		System.out.println(resMap);
		String className = (String) resMap.get("class");
		// remove class from map now that it is an exposed programmatic
		// parameter
		resMap.remove("class");
		CollectionReaderDescriptor crd = new CollectionReaderDescriptor(
				className, resMap);
		return crd;
	}

	/**
	 * 
	 */

	@Override
	public PipelineDescriptor buildPipelineDescriptor() {
		ArrayList<Map<String, Object>> phaseResMaps = (ArrayList<Map<String, Object>>) confMap
				.get("pipeline");

		List<PhaseDescriptor> phaseDescs = new LinkedList<PhaseDescriptor>();

		for (Map<String, Object> phaseResMap : phaseResMaps)
			phaseDescs.add(buildPhaseDescriptor(phaseResMap));

		// resMap = flatten(resMap);
		// System.out.println(resMap);

		// PipelineDescriptor pipelineDesc = new PipelineDescriptor();
		return null;
	}

	/**
	 * 
	 * @param phaseResMap
	 * @return
	 */
	public PhaseDescriptor buildPhaseDescriptor(Map<String, Object> phaseResMap) {
		phaseResMap = flatten(phaseResMap);
		List<Map<String, Object>> optionResMaps = (List<Map<String, Object>>) phaseResMap
				.get("options");

		List<OptionDescriptor> optionDescs = new LinkedList<OptionDescriptor>();

		for (Map<String, Object> optionResMap : optionResMaps)
			optionDescs.add(buildOptionDescriptor(optionResMap));

		if (phaseResMap.containsKey("name")) {
			String name = (String) phaseResMap.get("name");
			phaseResMap.remove("name");
			return new PhaseDescriptor((String) phaseResMap.get("name"),
					optionDescs);
		} else
			return new PhaseDescriptor(optionDescs);

	}

	/**
	 * 
	 * @param optionResMap
	 * @return
	 */
	public OptionDescriptor buildOptionDescriptor(
			Map<String, Object> optionResMap) {
		optionResMap = flatten(optionResMap);
		String className = (String) optionResMap.get("class");
		optionResMap.remove("class");
		return new OptionDescriptor(className,optionResMap);
	}

	/**
	 * 
	 * @return
	 */
	private Map<String, String> getConfiguration() {
		return (Map<String, String>) confMap.get("configuration");
	}

	/**
	 * 
	 */

	@Override
	public String getName() {
		return getConfiguration().get("name");
	}

	/**
	 * 
	 */

	@Override
	public String getAuthor() {
		return getConfiguration().get("author");
	}

	/**
	 * Takes the specified yaml component and flattens the "inherits" therein so
	 * that it only contains the "class" and associated parameters.
	 * 
	 * @param resMap
	 *            is the yaml descriptor mapping from names to parameters.
	 * @return
	 */
	private Map<String, Object> flatten(Map<String, Object> resMap) {
		if (resMap.containsKey("inherit")) {
			// Get resource specified by "inherit"
			String resource = (String) resMap.get("inherit");
			// convert package notation to file path
			resource = resource.replace(".", "/") + ".yaml";
			// Get resource map specified by the resource
			Map<String, Object> inheritedResMap;
			try {
				inheritedResMap = getResMap(resource);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				return null;
			}
			// Remove "inherit" artifact from effective yaml
			resMap.remove("inherit");
			// Combine the current resource with the flattened inherited
			// resource recursively to create effective yaml.
			return combineMaps(resMap, flatten(inheritedResMap));
		}
		return resMap;
	}

	/**
	 * Combines map1 and map2 into a single a map by taking the union of their
	 * keys.
	 * 
	 * @param map1
	 * @param map2
	 * @return the union of map1 and map2.
	 */
	private static <K, V> Map<K, V> combineMaps(Map<K, V> map1,
			Map<? extends K, ? extends V> map2) {
		map1.putAll(map2);
		return map1;
	}

	public static void main(String[] args) {
		String resource = args[0];
		YAMLParser parser = new YAMLParser(resource);
		parser.buildPipelineDescriptor();
	}

}
