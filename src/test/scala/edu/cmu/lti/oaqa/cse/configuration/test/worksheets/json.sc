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
object json {

  4 equals JInt(4)                                //> res0: Boolean = false
  val x = "check"                                 //> x  : String = check
  JString("check") == x                           //> res1: Boolean = false
  implicit val formats = Serialization.formats(ShortTypeHints(List(classOf[DoubleParameter], classOf[IntegerParameter],classOf[BooleanParameter])))
                                                  //> formats  : net.liftweb.json.Formats = net.liftweb.json.Serialization$$anon$
                                                  //| 1@3a6ac461
  case class A(what:String)
    
  val decomp = decompose(JSONObject(Map("params" -> A("check")))) \ "obj"
                                                  //> decomp  : net.liftweb.json.JsonAST.JValue = JObject(List(JField(params,JObj
                                                  //| ect(List(JField($outer,JObject(List())), JField(what,JString(check)))))))
  decomp.extract[Map[String,A]]                   //> res2: Map[String,edu.cmu.lti.oaqa.cse.configuration.scala.test.worksheets.j
                                                  //| son.A] = Map(params -> A(check))

}