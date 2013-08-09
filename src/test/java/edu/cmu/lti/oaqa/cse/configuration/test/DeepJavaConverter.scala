package edu.cmu.lti.oaqa.cse.configuration.test

import scala.collection.JavaConverters._
import scala.collection.JavaConversions._
object DeepJavaConverter {

  implicit def deepMapAsScalaConverter[A, B](javaMap: java.util.Map[A, B]): Map[String, Any] = {
    def deepMapAsScalaConverter[A, B](javaMap: java.util.Map[A, B]): Map[String, Any] =
      javaMap.toMap.map {
        case (k:String, v: java.util.Map[_, _]) => (k, deepMapAsScalaConverter(v))
        case (k:String, v: java.util.List[_]) => (k, deepListAsScalaConverter(v))
        case (k:String, v) => (k.replaceAll("class","className"), v)
      }
    deepMapAsScalaConverter(javaMap)
  }

  implicit def deepListAsScalaConverter[A](list: java.util.List[A]): List[Any] = {
    def deepListAsScalaConverter[A](list: java.util.List[A]): List[Any] =
      list.toList.map {
        case v: java.util.Map[_, _] => deepMapAsScalaConverter(v)
        case v: java.util.List[_] =>  deepListAsScalaConverter(v)
        case v => v
      }
    deepListAsScalaConverter(list)
  }

}