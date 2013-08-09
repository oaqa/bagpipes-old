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
import edu.cmu.lti.oaqa.cse.scala.configuration.ConfigurationDescriptors._
import net.liftweb.json.JsonAST.JValue
import net.liftweb.json.DefaultFormats
import net.liftweb.json.JsonAST._
import net.liftweb.json.Extraction._
import edu.cmu.lti.oaqa.cse.scala.configuration.ConfigurationDescriptors.Parameters._
import net.liftweb.json.Serialization
import net.liftweb.json.ShortTypeHints
object json {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(996); val res$0 = 

  4 equals JInt(4);System.out.println("""res0: Boolean = """ + $show(res$0));$skip(18); 
  val x = "check";System.out.println("""x  : String = """ + $show(x ));$skip(24); val res$1 = 
  JString("check") == x;System.out.println("""res1: Boolean = """ + $show(res$1));$skip(148); 
  implicit val formats = Serialization.formats(ShortTypeHints(List(classOf[DoubleParameter], classOf[IntegerParameter],classOf[BooleanParameter])))
  case class A(what:String);System.out.println("""formats  : net.liftweb.json.Formats = """ + $show(formats ));$skip(107); 
    
  val decomp = decompose(JSONObject(Map("params" -> A("check")))) \ "obj";System.out.println("""decomp  : net.liftweb.json.JsonAST.JValue = """ + $show(decomp ));$skip(32); val res$2 = 
  decomp.extract[Map[String,A]];System.out.println("""res2: Map[String,edu.cmu.lti.oaqa.cse.configuration.scala.test.worksheets.json.A] = """ + $show(res$2))}

}
