package birds

import org.bson.types.ObjectId


case class Bird(_id: ObjectId, bandId: Option[String], name: String, sex: String,
                species: String, breed: String, hatchYear: Option[Int]=None,
                medication: Option[List[String]]=None) {}

object Bird {
  def apply(bandId: Option[String], name: String, sex: String, species: String,
            breed: String, hatchYear: Option[Int], medication: Option[List[String]])
  : Bird = Bird(new ObjectId(), bandId, name, sex, species, breed, hatchYear,
    medication)

}


