package cli

import spray.json._
import birds.Bird
import spray.json.DefaultJsonProtocol._

object FileUtilRunner extends App {

  FileUtil.getJSONString("test.json").toJson.convertTo[Bird]
}
