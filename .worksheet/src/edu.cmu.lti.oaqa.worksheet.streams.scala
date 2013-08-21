package edu.cmu.lti.oaqa.worksheet

object streams {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(105); 
  def from(n: Int): Stream[Int] = n #:: from(n + 1);System.out.println("""from: (n: Int)Stream[Int]""");$skip(61); 

  val allEvens = from(1).filter(i => i % 2 == 0 && i < 20);System.out.println("""allEvens  : scala.collection.immutable.Stream[Int] = """ + $show(allEvens ))}

 
  
}
