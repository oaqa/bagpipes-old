package edu.cmu.lti.oaqa.cse.space.scala.test.worksheets
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
import edu.cmu.lti.oaqa.cse.scala.space.ConfigurationSpace
import edu.cmu.lti.oaqa.cse.CommonTesting._
object confTree {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(1268); val res$0 = 
new yamls {
  val parser: Parser = YAMLParser
 // val parsedEx0 = parser.parse(ex0)
 // println(parsedEx0)
//  val parsedEx1 = parser.parse(ex1)
  val confSpace = ConfigurationSpace

 // confSpace.populateTree(parsedEx1)
  val parsedEx2 = parser.parse(ex2)
  val space2 = confSpace.populateTree(parsedEx2)
  println(space2)
};System.out.println("""res0: edu.cmu.lti.oaqa.cse.CommonTesting.yamls{val parser: edu.cmu.lti.oaqa.cse.scala.configuration.Parser; val confSpace: edu.cmu.lti.oaqa.cse.scala.space.ConfigurationSpace.type; val parsedEx2: edu.cmu.lti.oaqa.cse.scala.configuration.ConfigurationDescriptors.ConfigurationDescriptor; val space2: this.confSpace.Tree[edu.cmu.lti.oaqa.cse.scala.configuration.ConfigurationDescriptors.ExecutableDescriptor]} = """ + $show(res$0))}
}
