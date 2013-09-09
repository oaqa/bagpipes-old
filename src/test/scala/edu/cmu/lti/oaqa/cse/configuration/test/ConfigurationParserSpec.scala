package edu.cmu.lti.oaqa.cse.configuration.test
import net.liftweb.json.JsonAST._
import net.liftweb.json.Extraction._
import net.liftweb.json.Printer._
import scala.collection.JavaConversions._
import scala.collection.JavaConverters._
import net.liftweb.json.parse
import net.liftweb.json.DefaultFormats
import net.liftweb.json.{ Serialization, ShortTypeHints }
import scala.util.parsing.json.JSONObject
import org.junit.runner.RunWith
import org.scalatest.FeatureSpec
import org.junit._
import org.scalatest.junit.JUnitRunner
import scala.reflect.ClassManifest
import edu.cmu.lti.oaqa.cse.scala.configuration.YAMLParser
import edu.cmu.lti.oaqa.cse.scala.configuration.Parser
import edu.cmu.lti.oaqa.cse.scala.configuration._
import edu.cmu.lti.oaqa.cse.scala.configuration.Implicits._
import edu.cmu.lti.oaqa.cse.CommonTesting.yamlsAndParsedConfigs

@RunWith(classOf[JUnitRunner])
class ConfigurationParserSpec extends FeatureSpec {
  val parser: Parser = YAMLParser
  feature("parse yamls") {
    new yamlsAndParsedConfigs {
      scenario("ex0: collection-reader with inherits") {
        val parsedEx0 = parser.parse(ex0)
        assert(confEx0 === parsedEx0)
      }
      scenario("ex1: collection-reader + 1 phase with 1 option (with inherits)") {
        val parsedEx1 = parser.parse(ex1)
        assert(confEx1 === parsedEx1)
      }
      scenario("ex2: collection-reader + 1 phase with 2 options (with inherits)") {
        val parsedEx2 = parser.parse(ex2)
        assert(confEx2 === parsedEx2)
      }

      scenario("ex3: collection-reader + phase with 1 option +  phase with 2 options") {
        val parsedEx3 = parser.parse(ex3)
        assert(confEx3 === parsedEx3)
      }

      scenario("ex4: collection-reader + phase with 2 option +  phase with 1 option") {
        val parsedEx4 = parser.parse(ex4)
        assert(confEx4 === parsedEx4)
      }
      
      scenario("ex5: collection-reader + component with cross-opts") {
        val parsedEx5 = parser.parse(ex6)
        assert(confEx5 === parsedEx5)
      }
    }
  }
}