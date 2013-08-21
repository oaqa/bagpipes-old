package edu.cmu.lti.oaqa.cse.scala.configuration

import java.util.Map.Entry
import Implicits._
import net.liftweb.json.JsonAST.JValue
import net.liftweb.json.JsonAST.JInt
import net.liftweb.json.JsonAST.JString
import net.liftweb.json.JsonAST.JBool
import net.liftweb.json.JsonAST.JDouble
import Parameters.Parameter
import edu.cmu.lti.oaqa.cse.scala.configuration.Parameters._

sealed trait ConfExpr
sealed trait ExecutableDescriptor
sealed trait ExecutableConf extends ExecutableDescriptor with ConfExpr
case class ConfigurationDescriptor(configuration: Configuration, `collection-reader`: CollectionReaderDescriptor, pipeline: List[PhaseDescriptor]) extends ConfExpr //, pipeline: List[PhaseDescriptor], consumers: List[ConsumerDescriptor]) 
case class Configuration(name: String = "default-config", author: String = "default-author") extends ConfExpr
case class CollectionReaderDescriptor(`class`: String, params: Map[String, Parameter] = Map()) extends ParameterizedDescriptor(`class`, params) with ExecutableConf
case class PhaseDescriptor(name: String, options: List[ComponentDescriptor]) extends ExecutableConf
case class ComponentDescriptor(`class`: String, params: Map[String, Parameter] = Map() /*, `persistence-provider`: ComponentDescriptor = emptyComponent*/ ) extends ParameterizedDescriptor(`class`, params) with ExecutableConf
case class ScoreDescriptor(cost: Double, benefit: Double)

sealed abstract class ParameterizedDescriptor(`class`: String, parmeters: Map[String, Any]) {
  def get[T](key: String)(implicit m: scala.reflect.Manifest[T]): Option[T] =
    parmeters.restrictTo[T].get(key) match {
      case None => { println("key: " + key + " of type: " + m.erasure + " does not exist!"); None }
      case s => s
    }
  def get(key: String) = get[Any](key)
  //typesafe get methods, guaranteed to get a parameter of a given type 
  // or nothing if it is not found
  def getInt(key: String) = get[IntegerParameter](key)
  def getDouble(key: String) = get[DoubleParameter](key)
  def getString(key: String) = get[StringParameter](key)
  def getBoolean(key: String) = get[BooleanParameter](key)
  def getMap(key: String) = get[Map[String, Parameter]](key)
  def getList(key: String) = get[List[Parameter]](key)
}