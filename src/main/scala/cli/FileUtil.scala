package cli

import scala.io.{BufferedSource, Source}
import java.io.{File, FileWriter}
import spray.json._
import DefaultJsonProtocol._


import scala.collection.mutable.ArrayBuffer

object FileUtil {

  def getJSONString(filename: String): String = {
    //Using Source.fromFile to open files

    var openedFile: BufferedSource = null

    try {
      openedFile = Source.fromFile(filename)
      val source = openedFile.mkString
      source
    } finally {
      if (openedFile != null)
        openedFile.close
    }
  }

 // def parseJSONStringToBird(jsonString: String): Unit = {
 //   jsonString.split("")
 // }

 /* def writeTextContent(filename: String, content: String) = {
    val fileWriter = new FileWriter(filename)
    fileWriter.write(content)
    fileWriter.close()
  }

  def deleteFile(filename: String): Unit = {
    val file = new File(filename)
    file.delete()
  }*/
}
