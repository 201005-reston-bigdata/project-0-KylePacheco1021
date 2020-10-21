/*package birdsdao

import birds.Bird
import org.mongodb.scala.MongoClient
import cli.FileUtil._

object DAORunner extends App {

  val client = MongoClient()
  val birdDAO = new BirdDAO(client)

  birdDAO.deleteDatabase()

  val popcorn: Bird = Bird(Some("OEB2595"), "Popcorn", "Male", "Cockatiel", "Lutino",
    Some(2019), None)
  birdDAO.addBird(popcorn)
  println(birdDAO.getByName("Popcorn"))
  println(birdDAO.deleteByName("Popcorn"))

  /*val popcorn: Bird = Bird(Some("OEB2595"), "Popcorn", "Male", "Cockatiel", "Lutino",
    Some(2019), None)
  val jojo: Bird = Bird(None, "JoJo", "Male", "Conure", "Duskey Headed", Some
  (2000),
    Some(List("Vibramycin", "Flucanozole", "Metaclopromide")))

  println(birdDAO.addBird(popcorn))
  println(birdDAO.getByName("Popcorn"))*/
  println("Getting bird by band ID")
  println(birdDAO.getByBandId("OEB2595"))

  /*println(birdDAO.addBird(jojo))
  println(birdDAO.getByName("JoJo"))
  //birdDAO.deleteByName("Popcorn")*/

  client.close()
}*/
