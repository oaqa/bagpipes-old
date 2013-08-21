package edu.cmu.lti.oaqa.cse.space
import edu.cmu.lti.oaqa.cse.CommonTesting._
import edu.cmu.lti.oaqa.cse.scala.space.ConfigurationSpace._
import edu.cmu.lti.oaqa.cse.scala.configuration._
package object test {
  trait confTrees extends progConfigs { 
    //tree1
    val confTree0 = Node(collectionReader, Nil)
    //tree2
    val confTree1 = Node[ExecutableConf](collectionReader,
      List( //children
        Leaf(roomAnnotator1)))
    //tree3
    val confTree2 = Node[ExecutableConf](collectionReader,
      List( //children
        Leaf(roomAnnotator1), Leaf(roomAnnotator2)))
  }
} 