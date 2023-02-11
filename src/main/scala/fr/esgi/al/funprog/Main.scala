package fr.esgi.al.funprog

import com.typesafe.config.Config
import com.typesafe.config.ConfigFactory
import better.files._
import fr.esgi.al.funprog.models.{Coordinates, Direction, LawnMower, Position}
import fr.esgi.al.funprog.parser.{ParseInput, ParseOutput}

import java.nio.charset.StandardCharsets
import java.nio.file.{Files, Paths}
import scala.annotation.tailrec
object Main extends App {
  val conf: Config = ConfigFactory.load()

  private val inputFileName: String = conf.getString("application.input-file")
  private val outputJsonFileName: String =
    conf.getString("application.output-json-file")
  private val outputCsvFileName: String =
    conf.getString("application.output-csv-file")
  val outputYmlFileName: String = conf.getString("application.output-yml-file")

  private val inputFile = File(inputFileName)
  private val dataInputFile = inputFile.lines.toList

  val limit: Coordinates =
    ParseInput.parseLimit(dataInputFile.take(1)).getOrElse(Coordinates(0, 0))

  @tailrec
  private def processLawnMower(
      dataInputFile: List[String],
      lawnMowers: List[LawnMower]
  ): List[LawnMower] = {
    dataInputFile match {
      case position :: orders :: tail =>
        val parsedPosition = ParseInput
          .parsePosition(limit, List(position))
          .getOrElse(
            Position(Coordinates(-1, -1), Direction('Z'))
          )
        val parsedOrders = ParseInput
          .parseOrders(List(orders))
          .getOrElse(
            List.empty
          )
        processLawnMower(
          tail,
          lawnMowers :+ LawnMower(parsedPosition, parsedPosition, parsedOrders)
        )
      case _ => lawnMowers
    }
  }

  val lawnMowers: List[LawnMower] =
    processLawnMower(dataInputFile.slice(1, dataInputFile.size), List.empty)

  val runner: Runner = Runner(limit, lawnMowers).run()

  private val outputJsonPath = Files.write(
    Paths.get(outputJsonFileName),
    runner.toJson.toString().getBytes(StandardCharsets.UTF_8)
  )

  private val outputCsvPath = Files.write(
    Paths.get(outputCsvFileName),
    ParseOutput
      .parseJsonToCsv(runner.lawnMowerList)
      .getBytes(StandardCharsets.UTF_8)
  )

  println(s"Json file path ${outputJsonPath.toString}")
  println(s"CSV file path ${outputCsvPath.toString}")
}
