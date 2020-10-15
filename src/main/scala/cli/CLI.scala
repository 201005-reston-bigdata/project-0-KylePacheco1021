package cli

import java.io.FileNotFoundException

import scala.io.StdIn
import scala.util.matching.Regex

/** CLI that interacts with wccli  */
class CLI {

  // RegEx are a very handy tool that lets us pattern match strings and extracts pieces
  // of strings that match patterns.

  val commandArgPattern: Regex = "(\\w+)\\s*(.*)".r

  def printWelcome():Unit = {
    println("Welcome to BirdsDB")
  }

  def printOptions():Unit = {
    println("file [filename]: wordcount the contents of a file")
    println("exit: close WCCLI")
    println("please enter an option")
  }

  /** Runs the menu prompting + listening for user input */
  def menu():Unit = {
    printWelcome()
    var continueMenuLoop = true

    //this loop prompts, listens, runs code, and repeat
    while(continueMenuLoop) {
      printOptions()
      //get user input with StdIn.readLine
      StdIn.readLine() match {

        case commandArgPattern(cmd,arg) if cmd.equalsIgnoreCase("file") => {
          try {
            FileUtil.getTextContent(arg)
              .getOrElse("No Content")
              .replaceAll("\\p{Punct}", "") //remove all punctuation
              .split("\\s+") //split string into words
              .groupMapReduce(w => w)(w => 1)(_ + _) //
              .foreach { case (word, count) => println(s"$word: $count")}
          } catch {
            case fnf: FileNotFoundException => println(s"Failed to find file $arg")
          }
        }

        case commandArgPattern(cmd,arg) if cmd.equalsIgnoreCase("exit") => {
          continueMenuLoop = false
        }
        case notRecognized => println(s"$notRecognized is not a recognized command.")
      }
    }
  }


}
