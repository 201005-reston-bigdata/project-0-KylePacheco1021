/*package birds

import birds.Bird
import org.bson.BsonNull
import org.mongodb.scala.{MongoClient, MongoCollection, Observable}
import org.mongodb.scala.bson.codecs.Macros._
import org.bson.codecs.configuration.CodecRegistries.{fromProviders, fromRegistries}
import org.mongodb.scala.model.Filters
import org.mongodb.scala.model.Filters._
import org.mongodb.scala.model.Updates._
import org.mongodb.scala.model.Sorts._

import scala.concurrent.Await
import scala.concurrent.duration.{Duration, SECONDS}

object Runner extends App {

  val codecRegistry = fromRegistries(fromProviders(classOf[Bird]), MongoClient
    .DEFAULT_CODEC_REGISTRY)

  val client = MongoClient()
  val db = client.getDatabase("birdsdb").withCodecRegistry(codecRegistry)
  val collection: MongoCollection[Bird] = db.getCollection("birds")

  def getResults[T](obs: Observable[T]): Seq[T] = {
    Await.result(obs.toFuture(),Duration(60, SECONDS))
  }

  def printResults[T](obs: Observable[T]): Unit = {
    getResults(obs).foreach(println(_))
  }

  //Deletes all documents with a name field
  //TODO comment this out when not testing
  printResults(collection.deleteMany(Filters.exists("name")))

  printResults(collection.insertOne(Bird("Popcorn", "Male", "Cockatiel", "Lutino",
    Some(2019), None)))

  client.close()

}*/
