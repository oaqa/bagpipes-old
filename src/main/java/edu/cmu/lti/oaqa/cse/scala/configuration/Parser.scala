package edu.cmu.lti.oaqa.cse.scala.configuration
import scala.io.Source._
import java.io.InputStream
import edu.cmu.lti.oaqa.cse.scala.configuration.ConfigurationDescriptors._
import net.liftweb.json.JsonAST.JValue
import net.liftweb.json.DefaultFormats
import net.liftweb.json.JsonAST._
import net.liftweb.json.Extraction._
import net.liftweb.json.Serialization
import net.liftweb.json.ShortTypeHints
import scala.util.parsing.json.JSONObject

trait Parser {

  def getResourceMap(resource: String): Map[String, Any]
  def getResourceMapFromFile(path: Any): Map[String, Any] = getResourceMapFromFile("/" + path.asInstanceOf[String].replace(".", "/"))
  def getResourceMapFromFile(path: String): Map[String, Any]

  def parseFromFile(path: String) = {
    //YAML (filepath)->java.util.map->scala.collections.Map
    val resMap = getResourceMapFromFile(path)
    parse(resMap)
  }
  def parse(resource: String): ConfigurationDescriptor = {
    //YAML->java.util.map->scala.collections.Map
    val resMap = getResourceMap(resource)
    parse(resMap)
  }

  implicit val formats = Serialization.formats(ShortTypeHints(List(classOf[StringParameter], classOf[DoubleParameter], classOf[IntegerParameter], classOf[BooleanParameter])))

  def parse(resMap: Map[String, Any]): ConfigurationDescriptor = {
    //flatten and convert configuration maps to configuration maps with parameters 
    //scala.collections.Map->(flattened+converted to parameters)scala.collections.Map
    val configMap = flatten(resMap)
    println("configMap: " + configMap)
    //Serialize to JSON
    //scala.collections.map->JSONObject
    //implicit value "formats" is used in decomposition of map into JSONObject.
    val jsonObjConf = decompose(configMap)
    //Deserialize to Scala case classes
    // JSONObj -> ConfigurationDescriptor
    jsonObjConf.extract[ConfigurationDescriptor]
  }

  def flatten(resMap: Map[String, Any]): Map[String, Any] =
    resMap.map {
      case (k, v: List[_]) => (k, flattenList(v))
      case (k, v: Map[_, _]) => (k, flattenComponent(v.asInstanceOf[Map[String, Any]]))
      case (k, v) => (k, v)
    }

  def flattenComponent(resMap: Map[String, Any]): Map[String, Any] = resMap.head match {
    case ("inherit", v) => {
      val resource = flattenComponent(getResourceMapFromFile(v))
      val combinedWithInherited = Map(resource.head, "parameters" -> joinMaps(resMap, resource, "parameters"))
      combinedWithInherited ++ flatten(resMap.tail ++ resource.tail - "parameters")
    }
    case _ => flatten(resMap)
  }

  def flattenList(resList: List[Any]): List[Any] =
    resList.map {
      case v: List[_] => flattenList(v)
      case v: Map[_, _] => flattenComponent(v.asInstanceOf[Map[String, Any]])
      case v => v
    }

  def joinMaps(map1: Map[String, _], map2: Map[String, _], on: String): Map[String, Parameter] = {
    val paramMap1 = map1.get(on)
    val paramMap2 = map2.get(on)
    val mapCombined: Map[String, _] = (paramMap1, paramMap2) match {
      case (None, None) => Map()
      case (m1, None) => m1.get.asInstanceOf[Map[String, _]]
      case (None, m2) => m2.get.asInstanceOf[Map[String, _]]
      case (m1, m2) => (m1 ++ m2).asInstanceOf[Map[String, _]]
    }
    mapCombined.map((e: (String, Any)) => (e._1, primitive2Parameter(e._2)))
  }

  def loadFileContent(in: String): String =
    fromInputStream(getClass.getResourceAsStream(in)).mkString

}
  