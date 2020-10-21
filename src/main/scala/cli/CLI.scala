package cli

import java.io.FileNotFoundException

import birds.Bird
import birdsdao.BirdDAO
import org.mongodb.scala.MongoClient
import scala.io.StdIn

/** CLI that interacts with birdsdb  */
class CLI {

  val client = MongoClient()
  val birdDAO = new BirdDAO(client)
  var continueMenuLoop = true

  def printWelcome(): Unit = {
    println("Welcome to BirdsDB")
  }

  def printOptions(): Unit = {
    println("-----------------------------------------------------------------------------")
    println(s"Please enter an option: \n")
    println("JSON: Uploads .JSON file to bird database")
    println("AllBirds: Retrieves all birds from database")
    println("AddBird: Prompts user to fill out fields about bird to be entered into " +
      "database")
    println("Bird: Gets information on bird by name")
    println("BandID: Gets information on bird by bandId")
    println("RemoveBird: Removes bird from database by name")
    println("RemoveBandId: Removes bird from database by bandId")
    println("Drop: Clears entire database")
    println("Exit: Leave program")
    println("-----------------------------------------------------------------------------")
  }

  /** Runs the menu prompting + listening for user input */
  def menu(): Unit = {
    printWelcome()

    //this loop prompts, listens, runs code, and repeat
    while (continueMenuLoop) {
      printOptions()
      //get user input with StdIn.readLine
      val input = StdIn.readLine()
      executeUserInput(input)
      if(continueMenuLoop==false) {
        client.close()
      }
    }
  }
  private def executeUserInput(input: String): Unit = {

    if (input.equalsIgnoreCase("json")) {
      println("Enter filename:")
      val jsonFile = StdIn.readLine()
      try {
        FileUtil.getBirdsFromJSON(jsonFile)
          .foreach(birdDAO.addBird(_))
        println(s"Added data from file $jsonFile. \n")
      } catch {
        case fnf: FileNotFoundException => println(s"Failed to find file: $jsonFile \n")
      }
    } else if(input.equalsIgnoreCase("allbirds")) {
      birdDAO.getAll().foreach(println(_))
      println()
    } else if(input.equalsIgnoreCase("addbird")) {
      val bird = getBirdInput()
      if(birdDAO.addBird(bird))
        println(s"Added ${bird.name} to database. \n")
      else
        println(s"Could not add ${bird.name} to database. \n")
    } else if(input.equalsIgnoreCase("bird")) {
      println("Enter bird's name")
      val name = StdIn.readLine()
      println(s"Information for $name:")
      birdDAO.getByName(name).foreach(println(_))
      println()
    } else if(input.equalsIgnoreCase("bandid")) {
      println("Enter bird's bandID:")
      val bandId = StdIn.readLine()
      println(s"Information for bird with bandID $bandId:\n")
      birdDAO.getByBandId(bandId).foreach(println(_))
    } else if (input.equalsIgnoreCase("removebird")) {
      println("Enter bird's name:")
      val name = StdIn.readLine()
      if(birdDAO.deleteByName(name))
        println(s"Deleted $name from database.\n")
      else
        println(s"Could not find bird named $name.\n")
    } else if(input.equalsIgnoreCase("removebandid")) {
      println("Enter bird's bandID:")
      val bandId = StdIn.readLine()
      if(birdDAO.deleteByBandId(bandId))
        println(s"Delete bird with bandID $bandId from database.\n")
      else
        println(s"Could not find bird with bandID $bandId.\n")
    }else if(input.equalsIgnoreCase("exit")) {
      println("Goodbye")
      continueMenuLoop=false
    } else if(input.equalsIgnoreCase("drop")) {
      birdDAO.deleteDatabase()
    } else
      println("Command not recognized.\n")
  }

  private def getBirdInput(): Bird = {

    var bandId: Option[String] = None
    var hatchYear: Option[Int] = None
    var medList: Option[List[String]] = None
    println("Enter bird's name (everyone deserves a name):")
    val name = StdIn.readLine()
    println("Enter bird's bandID (enter None if none exists):")
    val temp = StdIn.readLine()
    if(!temp.equalsIgnoreCase("none")) {
      bandId = Some(temp)
    }
    println("Enter bird's sex (Male/Female):")
    val sex = StdIn.readLine()
    println("Enter bird's species:")
    val species = StdIn.readLine()
    println("Enter bird's breed:")
    val breed = StdIn.readLine()
    println("Enter bird's hatch year (enter None if you do not know):")
    val tempInt = StdIn.readLine()
    if(!tempInt.equalsIgnoreCase("none")) {
      hatchYear = Some(tempInt.toInt)
    }
    println("Enter bird's medication (separate each medication by comma; enter None if " +
      "there are no medications):")
    val medString = StdIn.readLine()
    if(!medString.equalsIgnoreCase("none")) {
      medList = Some(medString.split(",").toList)
    }
    Bird(bandId, name, sex, species, breed, hatchYear, medList)
  }
}
