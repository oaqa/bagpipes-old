package edu.cmu.lti.oaqa.cse.scala.configuration

import edu.cmu.lti.oaqa.cse.scala.configuration.ConfigurationDescriptors._
import net.liftweb.json.JsonAST.JValue
import net.liftweb.json.DefaultFormats
import net.liftweb.json.JsonAST._
import net.liftweb.json.Extraction._
import net.liftweb.json.Serialization
import net.liftweb.json.ShortTypeHints
import scala.util.parsing.json.JSONObject
import ParserUtils._
import Implicits._

/**
 * @define confDes $packagePath.ConfigurationDescriptors.ConfigurationDescriptor
 * Maps a configuration descriptor (specified as its content or file path) to its canonical [[$confDes]]
 * case class representation as specified in [[$packagePath.ConfigurationDescriptors]].
 *
 * To tell `Parser` which parameters (@see [[$packagePath.ConfigurationDescriptors.Parameter]])
 * to use for mapping, implicit `ShortTypeHints` are defined in assigned to
 * `formats` in [[$packagePath.Implicits]].
 *
 * Briefly, parsing configuration descriptors can be seen as this flowchart:
 * (configuration descriptor)->[[java.util.Map]]->[[scala.collections.Map]]->
 * (flattened+converted to Parameters)[[scala.collections.Map]]->[[$confDes]]
 *
 * In more detail:
 * 1. Converts descriptor to [[java.util.Map]] using concrete implementation
 * (e.g., [[$packagePath.YAMLParser]]).
 * <p>
 * 2. Implicitly converts [[java.util.Map]] to [[scala.collections.Map]] using
 * `deepMapAsScalaConverter` and `deepListAsScalaConverter` that recursively convert
 * all the contents of the [[java.util.Map]] to Scala classes.
 * <p>
 * 3. Flattens the configuration map to an _effective_ configuration map. For
 * instance if a configuration descriptor is given as:
 * {{{
 * inherit: edu.cmu.descriptors.foo
 *   params:
 *     param_a: bar
 * }}}
 * if there is another file `edu.cmu.foo`:
 * {{{
 * class: edu.cmu.classes.foo
 *    params:
 *      param_b: baz
 * }}}
 * then the flattened version will result in a map representation
 * {{{
 * class: edu.cmu.classes.foo
 *    params:
 *      param_a: bar
 *      param_b: baz
 * }}}
 * Primitive types are also converted to their corresponding
 * [[$packagePath.ConfigurationDescriptors.Parameter]] types.
 * <p>
 * 4. Converts [[scala.collections.Map]] to [[scala.util.parsing.json.JSONObject]].
 * <p>
 * 5. Extracts [[$packagePath.ConfigurationDescriptors.ConfigurationDescriptor]]
 * from [[scala.util.parsing.json.JSONObject]] using the `extract` method defined
 * in [[net.liftweb.json.Extraction._]] and the `ShortTypeHints` defined in
 * [[$packagePath.Implicits]] `formats`.
 *
 * @see [[$packagePath.Implicits]]
 * @see [[$packagePath.ConfigurationDescriptors]]
 * @see [[$packagePath.ParserUtils]]
 */

trait Parser {

  /**
   * Returns a [[scala.util.Map]] representation of a configuration. Reformats
   * file path String to classpath resource style from standard Java package style
   * (e.g.,`edu.cmu.lti.oaqa.cse.configuration` -> `/edu/cmu/lti.oaqa.cse.configuration`).
   *
   * @param path the file path of the configuration descriptor.
   * @return [[scala.util.Map]] representation of the configuration descriptor.
   */
  protected def getConfigurationMapFromFile(path: Any): Map[String, Any] = getConfigurationMapFromFile("/" + path.asInstanceOf[String].replace(".", "/"))

  /**
   * Returns a [[scala.util.Map]] representation of a configuration from the
   * configuration descriptor contents. Hook method for concrete implementation
   * (e.g., @see [[$packagePath.YAMLParser]].
   */
  protected def getConfigurationMap(resource: String): Map[String, Any]
  /**
   * Returns a [[scala.util.Map]] representation of a configuration. Hook method
   * for concrete implementation (e.g., @see [[$packagePath.YAMLParser]].
   */
  protected def getConfigurationMapFromFile(path: String): Map[String, Any]
  /**Returns a new [[$confDes]] from file path of a configuration descriptor */
  def parseFromFile(path: String) = {
    //YAML (filepath)->java.util.map->scala.collections.Map
    val resMap = getConfigurationMapFromFile(path)
    parse(resMap)
  }
  /**Returns a new [[$confDes]] from contents of a configuration descriptor */
  def parse(resource: String): ConfigurationDescriptor = {
    //YAML->java.util.map->scala.collections.Map
    val resMap = getConfigurationMap(resource)
    parse(resMap)
  }

  /**
   *  Returns a new [[$confDes]] from a [[scala.collections.Map]].
   *  @param resMap a map containing the key/value pairs of the descriptor.
   */
  private def parse(resMap: Map[String, Any]): ConfigurationDescriptor = {
    //flatten and convert configuration maps to configuration maps with parameters 
    //scala.collections.Map->(flattened+converted to parameters)scala.collections.Map
    val configMap = flattenMap(resMap)
    println("configMap: " + configMap)
    //Serialize to JSON
    //scala.collections.map->JSONObject
    //implicit value "formats" is used in decomposition of map into JSONObject.
    val jsonObjConf = decompose(configMap)
    //Deserialize to Scala case classes
    // JSONObj -> ConfigurationDescriptor
    jsonObjConf.extract[ConfigurationDescriptor]
  }

  /**
   * Returns flattened version of the
   */
  private def flattenMap(confMap: Map[String, Any]): Map[String, Any] =
    confMap.map {
      case (k, v: List[_]) => (k, flattenList(v))
      case (k, v: Map[_, _]) => (k, flattenComponent(v.asInstanceOf[Map[String, Any]]))
      case (k, v) => (k, v)
    }

  private def flattenComponent(confMap: Map[String, Any]): Map[String, Any] = confMap.head match {
    case ("inherit", v) => {
      val resource = flattenComponent(getConfigurationMapFromFile(v))
      val combinedWithInherited = Map(resource.head, "params" -> joinMaps(confMap, resource, "params"))
      combinedWithInherited ++ flattenMap(confMap.tail ++ resource.tail - "params")
    }
    case _ => flattenMap(confMap)
  }

  private def flattenList(confList: List[Any]): List[Any] =
    confList.map {
      case v: List[_] => flattenList(v)
      case v: Map[_, _] => flattenComponent(v.asInstanceOf[Map[String, Any]])
      case v => v
    }

}
  