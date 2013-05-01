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

package edu.cmu.lti.oaqa.ecd.driver;

import java.util.UUID;

import edu.cmu.lti.oaqa.cse.component.uima.UimaConfigurationSpace;
import edu.cmu.lti.oaqa.cse.configuration.Configuration;
import edu.cmu.lti.oaqa.cse.configuration.Parser;
import edu.cmu.lti.oaqa.cse.configuration.YAMLParser;
import edu.cmu.lti.oaqa.cse.space.ConfigurationSpace;

public class CSEDriver {

	private ConfigurationSpace confSpace;

	public CSEDriver(String resource, String uuid) throws Exception {
		Configuration ex1Conf = parse(resource);
		confSpace = new UimaConfigurationSpace(ex1Conf);
	}

	private static Configuration parse(String resource) {
		Parser p = new YAMLParser(resource + ".yaml");
		Configuration conf = p.parse();
		return conf;
	}

	public void run() throws Exception {

		for (Object o : confSpace)
			;

	}

	public static void main(String[] args) throws Exception {
		String uuid = UUID.randomUUID().toString();
		if (args.length > 1) {
			uuid = args[1];
		}

		System.out.println("Experiment UUID: " + uuid);
		CSEDriver driver = new CSEDriver(args[0], uuid);
		driver.run();

	}

}