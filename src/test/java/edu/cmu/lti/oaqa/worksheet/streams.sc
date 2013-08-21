package edu.cmu.lti.oaqa.worksheet

object streams {
  def from(n: Int): Stream[Int] = n #:: from(n + 1)
                                                  //> from: (n: Int)Stream[Int]

  val allEvens = from(1).filter(i => i % 2 == 0 && i < 20)
                                                  //> allEvens  : scala.collection.immutable.Stream[Int] = Stream(2, ?)

 
  
}