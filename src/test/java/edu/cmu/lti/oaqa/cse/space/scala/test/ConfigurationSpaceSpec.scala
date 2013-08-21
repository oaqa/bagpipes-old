package edu.cmu.lti.oaqa.cse.space.scala.test
import edu.cmu.lti.oaqa.cse.configuration.scala.test._
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
import edu.cmu.lti.oaqa.cse.scala.configuration.ConfigurationDescriptors._
import edu.cmu.lti.oaqa.cse.scala.configuration.Implicits._
import edu.cmu.lti.oaqa.cse.CommonTesting.yamlsAndParsedConfigs
import edu.cmu.lti.oaqa.cse.scala.space.ConfigurationSpace

@RunWith(classOf[JUnitRunner])
class ConfigurationSpaceSpec extends FeatureSpec {
  val confSpace = ConfigurationSpace
  feature("construct simple trees") {
    new configurationTrees {
 
      scenario("just collection reader") {
        val testTree0 = confSpace.populateTree(progEx0)
        assert(testTree0 === confTree1)
      }

      scenario("collection reader + a single phase containing 1 option") {
        val testTree1 = confSpace.populateTree(progEx1)
        assert(testTree1 === confTree2)
      }

      scenario("collection reader + a single phase containing 2 options") {
        val testTree2 = confSpace.populateTree(progEx2)
        assert(testTree2 === confTree3)
      }
    }
  }

}