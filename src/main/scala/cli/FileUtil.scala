package cli

import scala.io.{BufferedSource, Source}
import java.io.{File, FileWriter}

import scala.collection.mutable.ArrayBuffer

object FileUtil {

  def getTextContent(filename: String): ArrayBuffer[Array[String]] = {
    //Using Source.fromFile to open files

    var openedFile: BufferedSource = null

    try {
      openedFile = Source.fromFile(filename)
      var array = new ArrayBuffer[Array[String]]
      for (line <- openedFile.getLines) {
        array.addOne(line.split(",").map(_.trim))
      }
      array
    } finally {
      if (openedFile != null)
        openedFile.close
    }
  }

  def writeTextContent(filename: String, content: String) = {
    val fileWriter = new FileWriter(filename)
    fileWriter.write(content)
    fileWriter.close()
  }

  def deleteFile(filename: String): Unit = {
    val file = new File(filename)
    file.delete()
  }
}
