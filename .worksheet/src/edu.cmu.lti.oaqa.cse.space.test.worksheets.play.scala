package edu.cmu.lti.oaqa.cse.space.test.worksheets

object play {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(88); 
	val x = Stream(1,2,3);System.out.println("""x  : scala.collection.immutable.Stream[Int] = """ + $show(x ));$skip(23); 
	val y = Stream(1,2,3);System.out.println("""y  : scala.collection.immutable.Stream[Int] = """ + $show(y ));$skip(12); val res$0 = 
	y equals x;System.out.println("""res0: Boolean = """ + $show(res$0))}
 
}
