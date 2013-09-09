package edu.cmu.lti.oaqa.cse.configuration.scala.test.worksheets
import net.liftweb.json.JsonAST._
import net.liftweb.json.Extraction._
import net.liftweb.json.Printer._
import scala.collection.JavaConversions._
import scala.collection.JavaConverters._
import net.liftweb.json.parse
import edu.cmu.lti.oaqa.cse.scala.configuration.Implicits._
import net.liftweb.json.DefaultFormats
import net.liftweb.json.{ Serialization, ShortTypeHints }
import net.liftweb.json.JsonAST.JInt._
import scala.util.parsing.json.JSONObject

import scala.io.Source._
import java.io.InputStream
import net.liftweb.json.JsonAST.JValue
import net.liftweb.json.DefaultFormats
import net.liftweb.json.JsonAST._
import net.liftweb.json.Extraction._
import net.liftweb.json.Serialization
import net.liftweb.json.ShortTypeHints
import edu.cmu.lti.oaqa.cse.CommonTesting._
import edu.cmu.lti.oaqa.cse.scala.configuration.Parser
import edu.cmu.lti.oaqa.cse.scala.configuration.YAMLParser._

object json {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(997); val res$0 = 

  4 equals JInt(4)
 // val x = "check"
  //JString("check") == x
  trait Listable;System.out.println("""res0: Boolean = """ + $show(res$0));$skip(192); 
  implicit val formats = Serialization.formats(ShortTypeHints(List(classOf[CollectionReaderDescriptor], classOf[B],classOf[C])))
  sealed trait ExecutableConf //extends ExecutableDescriptor with ConfExpr
  sealed trait Testable //extends ExecutableDescriptor with ConfExpr

  sealed trait ExecutableComponent extends ExecutableConf
  case class ConfigurationDescriptor(configuration: Configuration, `collection-reader`: ExecutableConf, pipeline: List[PhaseDescriptor])
  case class Configuration(name: String = "default-config", author: String = "default-author")
  case class CollectionReaderDescriptor(name: String) extends /* ParameterizedDescriptor(`class`, params) with*/ ExecutableConf
  case class D(name: String) extends  Testable
  case class PhaseDescriptor(name: String, options: List[ExecutableComponent]) extends ExecutableConf
  case class ComponentDescriptor(`class`: String, params: Map[String, B] = Map()) extends /*ParameterizedDescriptor(`class`, params) with*/ ExecutableComponent
  case class CrossComponentDescriptor(`class`: String, params: Map[String, B] = Map(), `cross-opts`: Map[String, List[B]] = Map()) extends /* ParameterizedDescriptor(`class`, params) with*/ ExecutableComponent
  case class ScoreDescriptor(cost: Double, benefit: Double)

  case class C(listy: List[Listable]) extends Listable
 // case class A(what: String, who: String) extends Listable
  case class B(name: String) extends Listable;System.out.println("""formats  : net.liftweb.json.Formats = """ + $show(formats ));$skip(1391); 
  val decomp = decompose(
    JSONObject(Map("listy" -> List( B("name"))))) \ "obj";System.out.println("""decomp  : net.liftweb.json.JsonAST.JValue = """ + $show(decomp ));$skip(53); val res$1 = 
  new yamls {
    //  flattenConfMap(parse(ex2))
  };System.out.println("""res1: edu.cmu.lti.oaqa.cse.CommonTesting.yamls = """ + $show(res$1));$skip(35); 
  val y = decomp.extract[Listable];System.out.println("""y  : edu.cmu.lti.oaqa.cse.configuration.scala.test.worksheets.json.Listable = """ + $show(y ))}

}
