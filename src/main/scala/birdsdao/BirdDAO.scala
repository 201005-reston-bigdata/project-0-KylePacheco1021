package birdsdao

import birds.Bird
import org.bson.codecs.configuration.CodecRegistries.{fromProviders, fromRegistries}
import org.mongodb.scala.bson.codecs.Macros._
import org.mongodb.scala.model.Filters
import org.mongodb.scala.model.Filters._
import org.mongodb.scala.{MongoClient, MongoCollection, Observable}

import scala.concurrent.Await
import scala.concurrent.duration.{Duration, SECONDS}

class BirdDAO(mongoClient: MongoClient) {
  /** A Data Access Object for Birds. The goal of this class is to encapsulate all the
   * MongoDB related parts of retrieving Birds so the rest of our application doesn't
   * have to concern itself with mongo
   *  */

  val codecRegistry = fromRegistries(fromProviders(classOf[Bird]), MongoClient
      .DEFAULT_CODEC_REGISTRY)
  val db = mongoClient.getDatabase("birdsdb").withCodecRegistry(codecRegistry)
  val collection: MongoCollection[Bird] = db.getCollection("birds")

  private def getResults[T](obs: Observable[T]): Seq[T] = {
    Await.result(obs.toFuture(),Duration(10, SECONDS))
  }

  private def printResults[T](obs: Observable[T]): Unit = {
    getResults(obs).foreach(println(_))
  }

  /** Add a Bird to database */

  def addBird(bird: Bird): Boolean = {
    try {
      getResults(collection.insertOne(bird))
      true
    } catch {
      case e: Exception => {
        e.printStackTrace()
        false
      }
    }
  }

  /** Retrieve all Birds */
  def getAll(): Seq[Bird] = getResults(collection.find())

  /** Retrieve Bird by bandId */
  def getByBandId(bandId: String) : Seq[Bird] = {
    getResults(collection.find(equal("bandId",bandId)))
  }

  /** Delete Bird by bandId */
  def deleteByBandId(bandId: String) = {
    try {
      getResults(collection.deleteMany(equal("bandId", bandId)))(0)
        .getDeletedCount > 0
    } catch {
      case e: Exception => {
        e.printStackTrace()
        false
      }
    }
  }

  /** Retrieve bird by name */
  def getByName(name: String) : Seq[Bird] = {
    getResults(collection.find(equal("name",name)))
  }

  /**Delete bird by name*/
  def deleteByName(name: String) = {
    try {
      getResults(collection.deleteMany(equal("name", name)))(0)
        .getDeletedCount > 0
    } catch {
      case e: Exception => {
        e.printStackTrace()
        false
      }
    }
  }

  /** Delete all entries in database */
  def deleteDatabase() = {
    getResults(collection.deleteMany(Filters.exists("_id")))
  }


}
