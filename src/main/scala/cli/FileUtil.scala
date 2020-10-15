package cli

import scala.io.{BufferedSource, Source}
import java.io.{File, FileWriter}

object FileUtil {

  def getTextContent(filename: String): Option[String] = {
    //Using Source.fromFile to open files

    var openedFile: BufferedSource = null

    try {
      openedFile = Source.fromFile(filename)
      Some(openedFile.getLines().mkString(" "))
    }finally {
      if(openedFile!=null)
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
