package edu.cmu.lti.oaqa.cse.scala.configuration

import edu.cmu.lti.oaqa.cse.scala.configuration.ConfigurationDescriptors._
import scala.io.Source._
import java.io.InputStream
object ParserUtils {
  def joinMaps(map1: Map[String, _], map2: Map[String, _], on: String): Map[String, Parameter] = {
    val paramMap1 = map1.get(on)
    val paramMap2 = map2.get(on)
    val mapCombined: Map[String, _] = (paramMap1, paramMap2) match {
      case (None, None) => Map()
      case (m1, None) => m1.get.asInstanceOf[Map[String, _]]
      case (None, m2) => m2.get.asInstanceOf[Map[String, _]]
      case (m1, m2) => (m1.get.asInstanceOf[Map[String, _]] ++ m2.get.asInstanceOf[Map[String, _]])
    }
    mapCombined.map((e: (String, Any)) => (e._1, primitive2Parameter(e._2)))
  }

  def loadFileContent(in: String): String =
    fromInputStream(getClass.getResourceAsStream(in)).mkString

}