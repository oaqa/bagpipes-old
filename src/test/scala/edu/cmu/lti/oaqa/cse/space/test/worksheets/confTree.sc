package edu.cmu.lti.oaqa.cse.space.scala.test.worksheets
import net.liftweb.json.JsonAST._
import net.liftweb.json.Extraction._
import net.liftweb.json.Printer._
import scala.collection.JavaConversions._
import scala.collection.JavaConverters._
import net.liftweb.json.parse
import net.liftweb.json.DefaultFormats
import net.liftweb.json.{ Serialization, ShortTypeHints }
import scala.util.parsing.json.JSONObject
import org.junit.runner.RunWith
import org.scalatest.FeatureSpec
import org.junit._
import org.scalatest.junit.JUnitRunner
import scala.reflect.ClassManifest
import edu.cmu.lti.oaqa.cse.scala.configuration.YAMLParser
import edu.cmu.lti.oaqa.cse.scala.configuration.Parser
import edu.cmu.lti.oaqa.cse.scala.configuration.ConfigurationDescriptors._
import edu.cmu.lti.oaqa.cse.scala.configuration.Implicits._
import edu.cmu.lti.oaqa.cse.scala.space.ConfigurationSpace
import edu.cmu.lti.oaqa.cse.CommonTesting._
object confTree {
new yamls {
  val parser: Parser = YAMLParser
 // val parsedEx0 = parser.parse(ex0)
 // println(parsedEx0)
//  val parsedEx1 = parser.parse(ex1)
  val confSpace = ConfigurationSpace

 // confSpace.populateTree(parsedEx1)
  val parsedEx2 = parser.parse(ex2)
  val space2 = confSpace.populateTree(parsedEx2)
  println(space2)
}                                                 //> configMap: Map(configuration -> Map(name -> oaqa-tutorial, author -> oaqa),
                                                  //|  collection-reader -> Map(class -> org.apache.uima.examples.cpe.FileSystemC
                                                  //| ollectionReader, params -> Map(InputDirectory -> StringParameter(data/), La
                                                  //| nguage -> StringParameter(en), BrowseSubdirectories -> BooleanParameter(fal
                                                  //| se), Encoding -> StringParameter(UTF-8))), pipeline -> List(Map(class -> ed
                                                  //| u.cmu.lti.oaqa.ecd.phase.BasePhase, params -> Map(), name -> RoomNumberAnno
                                                  //| tator, options -> List(Map(class -> org.apache.uima.tutorial.ex2.RoomNumber
                                                  //| Annotator, params -> Map(Locations -> ListParameter(List(StringParameter(Wa
                                                  //| tson - Yorktown), StringParameter(Watson - Hawthrone I), StringParameter(Wa
                                                  //| tson - Hawthorne II))), Patterns -> MapParameter(Map(numbered -> StringPara
                                                  //| meter(\b[0-4]\d[0-2]\d\d\b)))))))))
                                                  //| Node(CollectionReaderDescriptor(org.apache.uima.examples.cpe.FileSystemColl
                                                  //| ectionReader,Map(InputDirectory -> StringParameter(data/), Lan
                                                  //| Output exceeds cutoff limit.
}