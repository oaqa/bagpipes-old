package edu.cmu.lti.oaqa.cse.scala.configuration
import Implicits._
import ParserUtils._
import scala.io.Source._
import org.yaml.snakeyaml.Yaml
import java.io.InputStream
import scala.collection.JavaConverters._
object YAMLParser extends Parser {
  def getConfigurationMap(res: String): Map[String, Any] =
    new Yaml().load(res).asInstanceOf[java.util.Map[String, Any]]

  override def getConfigurationMapFromFile(path: String): Map[String, Any] = {
    val classpath = path + ".yaml"
    val content = loadFileContent(classpath)
    getConfigurationMap(content)
  }
}