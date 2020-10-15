package cli

object FileUtilRunner extends App {

  FileUtil.getTextContent("test.csv").foreach(_.foreach(println(_)))
}
