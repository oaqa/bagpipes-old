package edu.cmu.lti.oaqa.cse.space.test
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
import edu.cmu.lti.oaqa.cse.scala.space.ConfigurationSpace

@RunWith(classOf[JUnitRunner])
class ConfigurationSpaceSpec extends FeatureSpec {
  val confSpace = ConfigurationSpace
  feature("construct simple pipeline trees") {
    new confTrees {

      scenario("just a collection reader") {
        val testTree0 = confSpace.populateTree(confEx0)
        assert(testTree0 === confTree0)
      }

      scenario("collection reader + 1 phase with 1 option") {
        val testTree1 = confSpace.populateTree(confEx1)
        assert(testTree1 === confTree1)
      }

      scenario("collection reader + 1 phase with 2 options") {
        val testTree2 = confSpace.populateTree(confEx2)
        assert(testTree2 === confTree2)
      }

    }
  }

}