package edu.cmu.lti.oaqa.cse
import edu.cmu.lti.oaqa.cse.scala.configuration.Configuration._
import edu.cmu.lti.oaqa.cse.scala.configuration._
import edu.cmu.lti.oaqa.cse.scala.configuration.Parameters._

object CommonTesting {
  trait yamls {
    val ex0 = """
configuration:
  name: oaqa-tutorial
  author: oaqa 
  
collection-reader:
  inherit: collection_reader.filesystem-collection-reader"""
    val ex1 = ex0 + """
pipeline:
  - inherit: phases.phase  
    name: RoomNumberAnnotators
    options: 
     - inherit: tutorial.ex1.RoomNumberAnnotator"""
    val ex2 = ex1 + """
     - inherit: tutorial.ex2.RoomNumberAnnotator"""
    val exPhase2 = """ 
  - inherit: phases.phase  
    name: DateTimeAnnotators
    options: 
     - inherit: tutorial.ex3.SimpleTutorialDateTime
     - inherit: tutorial.ex3.TutorialDateTime"""
    val ex3 = ex1 + exPhase2
    val ex4 = ex2 + exPhase2
  }

  trait progConfigs {

    //Collection reader
    val collectionReaderParams = Map("Language" -> StringParameter("en"), "BrowseSubdirectories" -> BooleanParameter(false), "Encoding" -> StringParameter("UTF-8"))
    val collectionReader = CollectionReaderDescriptor("org.apache.uima.examples.cpe.FileSystemCollectionReader", collectionReaderParams)

    val config = Configuration("oaqa-tutorial", "oaqa")

    private val classPath = "org.apache.uima.tutorial"
    private def getPath(exNum: Int, name: String) = classPath + ".ex%d.%s" format (exNum, name)

    //params
    val testParams = Map("test" -> StringParameter("param1"))
    val locations = List("Watson - Yorktown", "Watson - Hawthorne I", "Watson - Hawthorne II")
    val patterns = Map("numbered" -> "\\b[0-4]\\d[0-2]\\d\\d\\b")
    val roomannotator2Params = Map[String, Parameter]("Locations" -> locations, "Patterns" -> patterns)

    //annotators
    //Ex1: RoomNumberAnnotator:
    val roomAnnotator1 = ComponentDescriptor(getPath(1, "RoomNumberAnnotator"), testParams)
    //Ex2: RoomNumberAnnotator
    val roomAnnotator2 = ComponentDescriptor(getPath(2, "RoomNumberAnnotator"), roomannotator2Params)

    val simpleDateTimeAnnotator = ComponentDescriptor(getPath(3, "SimpleTutorialDateTime"), testParams)
    val dateTimeAnnotator = ComponentDescriptor(getPath(3, "TutorialDateTime"), testParams)

    //phases
    val phase1 = PhaseDescriptor("RoomNumberAnnotators", List(roomAnnotator1))
    val phase2 = PhaseDescriptor("RoomNumberAnnotators", List(roomAnnotator1, roomAnnotator2))
    val phase3 = PhaseDescriptor("DateTimeAnnotators", List(simpleDateTimeAnnotator, dateTimeAnnotator))

    //examples
    val confEx0 = ConfigurationDescriptor(config, collectionReader, Nil)
    val confEx1 = ConfigurationDescriptor(config, collectionReader, List(phase1))
    val confEx2 = ConfigurationDescriptor(config, collectionReader, List(phase2))
    val confEx3 = ConfigurationDescriptor(config, collectionReader, List(phase1, phase3))
    val confEx4 = ConfigurationDescriptor(config, collectionReader, List(phase2, phase3))
  }

  trait yamlsAndParsedConfigs extends progConfigs with yamls
}