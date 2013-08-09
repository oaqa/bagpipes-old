package edu.cmu.lti.oaqa.cse.scala.configuration
import scala.collection.JavaConverters._
import net.liftweb.json.JsonAST._
import scala.collection.JavaConversions._
object Implicits {
  class MapWithRestrictTo[A, That](from: Map[A, That]) {
    def restrictTo[B](implicit m: scala.reflect.Manifest[B]): Map[A, B] = {
      from.filter((e: (A, That)) => m.erasure.isInstance(e._2))
        .map((e: (A, That)) => e.asInstanceOf[(A, B)])
    }
  }
  implicit def makeMapWithRestrictTo[A, B](from: Map[A, B]) = {
    new MapWithRestrictTo(from)
  }
  /*
  implicit def ==[J <: JValue, T <: Any](jval: J, sVal: T) = (jval, sVal) match {
    case (j: JInt, s: Int) => jInt2SInt(j) == s
    case (j: JString, s: String) => jString2SString(j) == s
    case (j: JBool, s: Boolean) => jBool2SBool(j) == s
    case (j: JDouble, s: Double) => jDouble2SDouble(j) == s
  }
*/
 // implicit def ==[J <: JValue, T <: Any](sval: T, jVal: J) = jVal == sval
  implicit def jInt2SInt(jVal: JInt): Int = jVal.num.toInt
  implicit def jBool2SBool(jVal: JBool): Boolean = jVal.value
  implicit def jDouble2SDouble(jVal: JDouble): Double = jVal.num
  implicit def jString2SString(jVal: JString): String = jVal.s

  /**
   * Implicitly takes a Java map and converts it to a Scala map. If the
   * values of the Java map contains any references to another Java map or
   * list, then those will be recursively converted to Scala map or list
   * respectively. If one of the keys in the map uses the Java keyword "class"
   * then that will be converted to className so as to be compatible with
   * the Configuration case classes.
   */
  implicit def deepMapAsScalaConverter[A, B](javaMap: java.util.Map[A, B]): Map[String, Any] = {
    def deepMapAsScalaConverter[A, B](javaMap: java.util.Map[A, B]): Map[String, Any] =
      javaMap.toMap.map {
        case (k: String, v: java.util.Map[_, _]) => (k, deepMapAsScalaConverter(v))
        case (k: String, v: java.util.List[_]) => (k, deepListAsScalaConverter(v))
        case (k: String, v) => (k, v)
      }
    deepMapAsScalaConverter(javaMap)
  }

  /**
   * Implicitly takes a Java list and converts it to a Scala list. If the
   * values of the Java list contains any references to another Java map or
   * list, then those will be recursively converted to Scala map or list
   * respectively.
   */
  implicit def deepListAsScalaConverter[A](list: java.util.List[A]): List[Any] = {
    def deepListAsScalaConverter[A](list: java.util.List[A]): List[Any] =
      list.toList.map {
        case v: java.util.Map[_, _] => deepMapAsScalaConverter(v)
        case v: java.util.List[_] => deepListAsScalaConverter(v)
        case v => v
      }
    deepListAsScalaConverter(list)
  }

}