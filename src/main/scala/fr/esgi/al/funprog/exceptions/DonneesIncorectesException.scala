package fr.esgi.al.funprog.exceptions

import com.typesafe.config.{Config, ConfigFactory}

import java.nio.charset.StandardCharsets
import java.nio.file.{Files, Paths, StandardOpenOption}
import scala.util.{Failure, Success, Try}

final case class DonneesIncorectesException(errorMessage: String)
    extends Exception(errorMessage) {
  val conf: Config = ConfigFactory.load()
  private val outputLogFileName: String =
    conf.getString("application.output-log-file")
  private val Timestamp = java.time.LocalDateTime.now.toString
  private val errorMessageWithTimestamp =
    s"$Timestamp: [Error] $errorMessage \n"

  private val writeToFile = Try(
    Files.write(
      Paths.get(outputLogFileName),
      errorMessageWithTimestamp.getBytes(StandardCharsets.UTF_8),
      StandardOpenOption.APPEND
    )
  )

  writeToFile match {
    case Success(_) =>
    case Failure(e) => {
      println(s"Failed to write to file: ${e.getMessage}")
      println(errorMessageWithTimestamp)
    }
  }
}
