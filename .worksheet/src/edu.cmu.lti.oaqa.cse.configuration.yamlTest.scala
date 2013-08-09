package edu.cmu.lti.oaqa.cse.configuration

import org.yaml.snakeyaml.Yaml
import org.yaml.snakeyaml.constructor._

object yamlTest {
import edu.cmu.lti.oaqa.cse.configuration.test.FooBar;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(215); 
val yaml =
"""foo: ahaaa""";System.out.println("""yaml  : String = """ + $show(yaml ));$skip(55); val res$0 = 

new Yaml(new Constructor(FooBar.getClass)).load(yaml);System.out.println("""res0: <error> = """ + $show(res$0))}



}
