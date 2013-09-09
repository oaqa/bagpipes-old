package edu.cmu.lti.oaqa.cse.space.test.worksheets

object notes {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(128); val res$0 = 

  Map(1 -> 2) ++ List(Map("a" -> "b", "c" -> "d")).flatten;System.out.println("""res0: scala.collection.immutable.Map[Any,Any] = """ + $show(res$0));$skip(271); 
  def crossParamsExpander[K, V](m: Map[K, List[V]]): List[Map[K, V]] = m.keys.toList match {
    case Nil => List(Map())
    case head :: tail =>
      for {
        p <- m(head)
        pMap <- crossParamsExpander(m.tail.toMap)
      } yield  Map(head -> p) ++ pMap
  };System.out.println("""crossParamsExpander: [K, V](m: Map[K,List[V]])List[Map[K,V]]""");$skip(106); 

  def combineMaps[K, V](map: Map[K, V], rest: List[Map[K, V]]) = for (pMap <- rest) yield (map ++ pMap);System.out.println("""combineMaps: [K, V](map: Map[K,V], rest: List[Map[K,V]])List[scala.collection.immutable.Map[K,V]]""");$skip(86); val res$1 = 

  crossParamsExpander(Map("opt1" -> List("a", "b", "c"), "opt2" -> List("1", "2")));System.out.println("""res1: List[Map[String,String]] = """ + $show(res$1))}

}
