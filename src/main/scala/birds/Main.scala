package birds

import birds.Bird
import birdsdao.BirdDAO
import cli.CLI
import org.mongodb.scala.MongoClient




object Main extends App {
  new CLI().menu()
}
