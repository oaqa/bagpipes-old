package edu.cmu.lti.oaqa.cse
import edu.cmu.lti.oaqa.cse.scala.configuration.ConfigurationDescriptors._

object CommonTesting {
  trait yamls {
    val ex0 = """
configuration:
  name: oaqa-tutorial
  author: oaqa 
 
collection-reader:
  inherit: collection_reader.filesystem-collection-reader
"""

    val ex1 = """
configuration: 
  name: oaqa-tutorial
  author: oaqa

collection-reader:
  inherit: collection_reader.filesystem-collection-reader
  params:
    InputDirectory: data/

pipeline:
     - inherit: phases.phase  
       name: RoomNumberAnnotator
       options: 
         - inherit: tutorial.ex1.RoomNumberAnnotator  
"""

    val ex2 = """
configuration: 
  name: oaqa-tutorial
  author: oaqa

collection-reader:
  inherit: collection_reader.filesystem-collection-reader
  params:
    InputDirectory: data/

pipeline:
    - inherit: phases.phase  
      name: RoomNumberAnnotator
      options:
        - inherit: tutorial.ex1.RoomNumberAnnotator
        - inherit: tutorial.ex2.RoomNumberAnnotator   
      """
  }

  trait progConfigs {

    //Collection reader
    val collectionReader = CollectionReaderDescriptor("collection_reader.filesystem-collection-reader", collectionReaderParams)
    val collectionReaderParams = Map("Language" -> StringParameter("en"), "BrowseSubdirectories" -> BooleanParameter(false), "Encoding" -> StringParameter("UTF-8"))

    val config = Configuration("oaqa-tutorial", "oaqa")

    //annotators
    //Ex1: RoomNumberAnnotator:
    val roomAnnotator1 = ComponentDescriptor("org.apache.uima.tutorial.ex1.RoomNumberAnnotator", Map("test" -> StringParameter("param1")))
    //Ex2: RoomNumberAnnotator
    val roomAnnotator2 = ComponentDescriptor("org.apache.uima.tutorial.ex2.RoomNumberAnnotator", roomannotator2Params)
    //params
    val locations = List("Watson - Yorktown", "Watson - Hawthrone I", "Watson - Hawthorne II")
    val patterns = Map("numbered" -> """\\b[0-4]\\d[0-2]\\d\\d\\b""")
    val roomannotator2Params = Map[String, Parameter]("Locations" -> locations, "patterns" -> patterns)

    //phases
    val phase1 = PhaseDescriptor("RoomNumberAnnotator", List(roomAnnotator1))
    val phase2 = PhaseDescriptor("RoomNumberAnnotator", List(roomAnnotator1, roomAnnotator2))

    //examples
    val progEx0 = ConfigurationDescriptor(config, collectionReader, Nil)
    val progEx1 = ConfigurationDescriptor(config, collectionReader, List(phase1))
    val progEx2 = ConfigurationDescriptor(config, collectionReader, List(phase2))

  }

  trait yamlsAndParsedConfigs extends progConfigs with yamls
}