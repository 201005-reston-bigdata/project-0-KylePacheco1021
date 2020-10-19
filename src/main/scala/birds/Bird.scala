package birds

import org.bson.types.ObjectId

import scala.collection.mutable.ArrayBuffer


case class Bird(_id: ObjectId, bandId: Option[String], name: String, sex: String,
                species: String, breed: String, hatchYear: Option[Int]=None,
                medication: Option[List[String]]=None) {}

object Bird {
  def apply(bandId: Option[String], name: String, sex: String, species: String,
            breed: String, hatchYear: Option[Int], medication: Option[List[String]])
  : Bird = Bird(new ObjectId(), bandId, name, sex, species, breed, hatchYear,
    medication)
  /*implicit object BirdFormat extends RootJsonFormat[Bird] {
    def write(bird: Bird): JsValue = {
      JsObject(
        "bandId" -> bird.bandId.toJson,
        "name" -> bird.name.toJson,
        "sex" -> bird.sex.toJson,
        "species" -> bird.species.toJson,
        "breed" -> bird.breed.toJson,
        "hatchYear" -> bird.hatchYear.toJson,
        "medication" -> bird.medication.toJson
      )
    }

    def read(value: JsValue) = value match {
      case JsArray(Vector(JsString(bandId), JsString(name), JsString(sex), JsString(species),
      JsString(breed), JsNumber(hatchYear), JsArray(medication))) => {
        var meds = new ArrayBuffer[String]()
        for(i<-medication.toList) {
          meds.append(i.convertTo[String])
        }
        Bird.apply(Some(bandId), name, sex, species, breed, Some(hatchYear.toInt), Some
        (meds.toList))
      }


    }
  }*/
}


/*object MyJsonProtocol extends DefaultJsonProtocol {
  implicit val birdJsonFormat = new RootJsonFormat[Bird] {
    def write(bird: Bird): JsValue = {
      JsObject(
        "bandId" -> bird.bandId.toJson,
        "name" -> bird.name.toJson,
        "sex" -> bird.sex.toJson,
        "species" -> bird.species.toJson,
        "breed" -> bird.breed.toJson,
        "hatchYear" -> bird.hatchYear.toJson,
        "medication" -> bird.medication.toJson
      )
    }

    def read(value: JsValue) = value match {
      case JsArray(Vector(JsString(bandId), JsString(name), JsString(sex), JsString(species),
      JsString(breed),JsNumber(hatchYear), JsArray(medication))) =>
        Bird(Some(bandId),name,sex,species,breed,Some(hatchYear.toInt),Some
        (medication.toList))
    }
    def read(value: JsValue): Bird = {
      value.asJsObject.getFields("bandId","name","sex","species","breed","hatchYear",
        "medication") match {
        case Seq(JsString(bandId), JsString(name), JsString(sex), JsString(species),
        JsString(breed),JsNumber(hatchYear), JsArray(medication)) =>
          Bird(Some(bandId),name,sex,species,breed,Some(hatchYear.toInt),Some
          (medication.toList))
      }
    }
  }
}*/