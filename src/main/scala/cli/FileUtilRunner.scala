package cli

import birds.Bird
import net.liftweb.json._
import scala.collection.mutable.ArrayBuffer

object FileUtilRunner extends App {


  println(FileUtil.getBirdsFromJSON("test.json"))
  /*var jojo = Bird(None, "JoJo", "Male", "Conure", "Dusky Headed", None, None)
  val json = FileUtil.getJSONString("test.json")
  //println(json)
  val bandIdList = for {
    JObject(bird) <- json
    JField("bandId", bandId) <- bird
    //JField("medication", JArray(medication)) <- bird
  } yield {
    bandId match {
      case id => Some(compactRender(id))
      case JNull => None
      case _ => None
    }
  }
  println(bandIdList)

  val nameList = for {
    JObject(bird) <- json
    JField("name", JString(name)) <- bird
  } yield name
  println(nameList)

  val sexList = for {
    JObject(bird) <- json
    JField("sex", JString(sex)) <- bird
  } yield sex
  println(sexList)

  val speciesList = for {
    JObject(bird) <- json
    JField("species", JString(species)) <- bird
  } yield species
  println(speciesList)

  val breedList = for {
    JObject(bird) <- json
    JField("breed", JString(breed)) <- bird
  } yield breed
  println(breedList)

  val hatchYearList = for {
    JObject(bird) <- json
    JField("hatchYear", hatchYear) <- bird
  } yield {
    hatchYear match {
      case year => Some(compactRender(year).toInt)
      case JNull => None
      case _ => None
    }
  }
  println(hatchYearList)

  val medsList = (json \ "medication").children
  println(medsList)

  var medsArray = ArrayBuffer[Option[List[String]]]()
  for(elts<-medsList) {
    println(elts.values)
    if (elts.values == null)
      medsArray.append(None)
    else
      medsArray.append(Some(elts.values.asInstanceOf[List[String]]))
  }

  println(medsArray)*/


}
