package edu.cmu.lti.oaqa.cse.configuration.test.worksheets

object mapMerge {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(141); 
  val map1 = Map("a" -> 1, "b" -> Map("foo" -> 2, "bar" -> 3));System.out.println("""map1  : scala.collection.immutable.Map[String,Any] = """ + $show(map1 ));$skip(63); 
  val map2 = Map("a" -> 2, "b" -> Map("foo" -> 4, "baz" -> 5));System.out.println("""map2  : scala.collection.immutable.Map[String,Any] = """ + $show(map2 ));$skip(126); val res$0 = 
  map1.map {
    case (k, v) => (k -> map2(k))
  //  case (k, Map[String, Any]) => map2(k).asInstanceOf[Map[String, Any]]
  };System.out.println("""res0: scala.collection.immutable.Map[String,Any] = """ + $show(res$0))}

}
