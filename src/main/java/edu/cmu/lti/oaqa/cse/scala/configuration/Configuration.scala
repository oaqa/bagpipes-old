package edu.cmu.lti.oaqa.cse.scala.configuration

import java.util.Map.Entry
import Implicits._
import net.liftweb.json.JsonAST.JValue
import net.liftweb.json.JsonAST.JInt
import net.liftweb.json.JsonAST.JString
import net.liftweb.json.JsonAST.JBool
import net.liftweb.json.JsonAST.JDouble

object ConfigurationDescriptors {

  val paramsKey = "params"
  
  trait ExecutableDescriptor

  abstract class ParameterizedDescriptor(`class`: String, parmeters: Map[String, Any]) {
    def get[T](key: String)(implicit m: scala.reflect.Manifest[T]): Option[T] =
      parmeters.restrictTo[T].get(key) match {
        case None => { println("key: " + key + " of type: " + m.erasure + " does not exist!"); None }
        case s => s
      }
    def get(key: String) = get[Any](key)
    def getInt(key: String) = get[IntegerParameter](key)
    def getDouble(key: String) = get[DoubleParameter](key)
    def getString(key: String) = get[StringParameter](key)
    def getBoolean(key: String) = get[BooleanParameter](key)
    def getMap(key: String) = get[Map[String, Parameter]](key)
    def getList(key: String) = get[List[Parameter]](key)
  }

  case class ConfigurationDescriptor(configuration: Configuration, `collection-reader`: CollectionReaderDescriptor, pipeline: List[PhaseDescriptor]) //, pipeline: List[PhaseDescriptor], consumers: List[ConsumerDescriptor])
  case class Configuration(name: String = "default-config", author: String = "default-author")
  case class CollectionReaderDescriptor(`class`: String, params: Map[String, Parameter] = Map()) extends ExecutableDescriptor
  case class PhaseDescriptor(name: String, options: List[ComponentDescriptor]) extends ExecutableDescriptor
  val emptyComponent = ComponentDescriptor("EMPTY", Map())
  case class ComponentDescriptor(`class`: String, params: Map[String, Parameter]/*, `persistence-provider`: ComponentDescriptor = emptyComponent*/) extends ParameterizedDescriptor(`class`, params) with ExecutableDescriptor
  case class ScoreDescriptor(cost: Double, benefit: Double)

  abstract class Parameter
  case class IntegerParameter(value: Int) extends Parameter
  case class StringParameter(value: String) extends Parameter
  case class DoubleParameter(value: Double) extends Parameter
  case class BooleanParameter(value: Boolean) extends Parameter
  case class ListParameter(pList: List[Parameter]) extends Parameter
  case class MapParameter(map: Map[String, Parameter]) extends Parameter
  implicit def primitive2Parameter[T <: Any](value: T): Parameter = value match {
    case v: Int => IntegerParameter(v)
    case v: String => StringParameter(v)
    case v: Double => DoubleParameter(v)
    case v: Boolean => BooleanParameter(v)
    case plist: List[_] => ListParameter(plist.map(p => primitive2Parameter(p)))
    case pmap: Map[_, _] => MapParameter(pmap.map { case (k: String, v) => (k, primitive2Parameter(v)) })
  }

  //implicit def primitive2PList(list:List[_]) : Parameter =   

}