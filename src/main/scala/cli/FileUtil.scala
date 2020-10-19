package cli

import scala.io.{BufferedSource, Source}
import java.io.{File, FileWriter}

import birds.Bird
import net.liftweb.json._

import scala.collection.mutable.ArrayBuffer

object FileUtil {

  def getJSONString(filename: String): JValue = {
    //Using Source.fromFile to open files

    var openedFile: BufferedSource = null

    try {
      openedFile = Source.fromFile(filename)
      val source = openedFile.mkString
      parse(source)
    } finally {
      if (openedFile != null)
        openedFile.close
    }
  }

  def getBirdsFromJSON(filename: String): List[Bird] = {
    var birds = ArrayBuffer[Bird]()
    val json = getJSONString(filename)
    var bandIdArray = ArrayBuffer[Option[String]]()
    var hatchYearArray = ArrayBuffer[Option[Int]]()
    var medsArray = ArrayBuffer[Option[List[String]]]()

    /** Gets list of bandId's */
    val bandIdList = for {
      JObject(bird) <- json
      JField("bandId", bandId) <- bird
    } yield {
      bandId match {
        case id => Some(compactRender(id))
        case JNull => None
        case _ => None
      }
    }

    for(elt<-bandIdList) {
      if(elt == Some(null))
        bandIdArray.append(None)
      else
        bandIdArray.append(elt)
    }
    val properBandIdList = bandIdArray.toList

    /** Gets list of names */
    val nameList = for {
      JObject(bird) <- json
      JField("name", JString(name)) <- bird
    } yield name

    /** Gets list of sex */
    val sexList = for {
      JObject(bird) <- json
      JField("sex", JString(sex)) <- bird
    } yield sex

    /** Gets list of species */
    val speciesList = for {
      JObject(bird) <- json
      JField("species", JString(species)) <- bird
    } yield species

    /** Gets list of breed */
    val breedList = for {
      JObject(bird) <- json
      JField("breed", JString(breed)) <- bird
    } yield breed

    /** Gets list of hatchYear */
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
    for(elt<-hatchYearList) {
      if(elt == Some(null))
        hatchYearArray.append(None)
      else
        hatchYearArray.append(elt)
    }
    val properHatchYearList = hatchYearArray.toList


    /** Gets list of medications */
    val medsList = (json \ "medication").children
    for(elts<-medsList) {
      if (elts.values == null)
        medsArray.append(None)
      else
        medsArray.append(Some(elts.values.asInstanceOf[List[String]]))
    }
    val properMedsList = medsArray.toList

    for(i<-0 until properBandIdList.length) {
      birds.append(Bird(properBandIdList(i),nameList(i),sexList(i),speciesList(i),
        breedList(i),properHatchYearList(i), properMedsList(i)))
    }
    birds.toList
  }

























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
