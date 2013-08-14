package edu.cmu.lti.oaqa.cse.configuration.scala.test
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
@RunWith(classOf[JUnitRunner])
class ConfigurationParserSpec extends FeatureSpec {

  trait yamlParsers {
    val ex0 = """
configuration:
  name: oaqa-tutorial
  author: oaqa 
 
collection-reader:
  inherit: collection_reader.filesystem-collection-reader
"""

    val ex1 = """
configuration: 
  name: oaqa-tutorial
  author: oaqa

collection-reader:
  inherit: collection_reader.filesystem-collection-reader
  params:
    InputDirectory: data/

pipeline:
     - inherit: phases.phase  
       name: RoomNumberAnnotator
       options: 
         - inherit: tutorial.ex1.RoomNumberAnnotator  
"""

    val ex2 = """
configuration: 
  name: oaqa-tutorial
  author: oaqa

collection-reader:
  inherit: collection_reader.filesystem-collection-reader
  params:
    InputDirectory: data/

pipeline:
    - inherit: phases.phase  
      name: RoomNumberAnnotator
      options: 
        - inherit: tutorial.ex2.RoomNumberAnnotator   
      """

    val config = Configuration("oaqa-tutorial", "oaqa")
    val collectionReaderParams = Map("Language" -> StringParameter("en"), "BrowseSubdirectories" -> BooleanParameter(false), "Encoding" -> StringParameter("UTF-8"))
    val collectionReader = CollectionReaderDescriptor("collection_reader.filesystem-collection-reader", collectionReaderParams)
    val ex0Parsed = ConfigurationDescriptor(config, collectionReader, List())

  }
  val parser: Parser = YAMLParser
  feature("parse yamls") {
    scenario("collection-reader with inherits") {
      new yamlParsers {
        val parsedEx0 = parser.parse(ex0)
        println(parsedEx0)
        val parsedEx1 = parser.parse(ex1)
        println(parsedEx1)
        val parsedEx2 = parser.parse(ex2)
        println(parsedEx2)
      }
    }
  }
}