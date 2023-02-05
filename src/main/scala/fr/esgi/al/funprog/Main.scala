package fr.esgi.al.funprog

import com.typesafe.config.Config
import com.typesafe.config.ConfigFactory
import better.files._
import fr.esgi.al.funprog.models.{Coordinates, LawnMower, Order, Position}
import fr.esgi.al.funprog.parser.{ParseInput, ParseOutput}

import java.nio.charset.StandardCharsets
import java.nio.file.{Files, Paths}
object Main extends App {
  val conf: Config = ConfigFactory.load()

  val inputFileName: String = conf.getString("application.input-file")
  val outputJsonFileName: String =
    conf.getString("application.output-json-file")
  val outputCsvFileName: String = conf.getString("application.output-csv-file")

  val inputFile = File(inputFileName)
  val dataInputFile = inputFile.lines.toList

  val limit: Coordinates = ParseInput.parseLimit(dataInputFile.take(1))

  val position1: Position =
    ParseInput.parsePosition(limit, dataInputFile.slice(1, 2))
  val order1: List[Order] = ParseInput.parseOrders(dataInputFile.slice(2, 3))

  val position2: Position =
    ParseInput.parsePosition(limit, dataInputFile.slice(3, 4))
  val order2: List[Order] = ParseInput.parseOrders(dataInputFile.slice(4, 5))

  val lawnMowers: List[LawnMower] = List(
    LawnMower(position1, position1, order1),
    LawnMower(position2, position2, order2)
  )

  val runner: Runner = Runner(limit, lawnMowers).run()

  val outputJsonPath = Files.write(
    Paths.get(outputJsonFileName),
    runner.toJson.toString().getBytes(StandardCharsets.UTF_8)
  )

  val outputCsvPath = Files.write(
    Paths.get(outputCsvFileName),
    ParseOutput
      .parseJsonToCsv(runner.lawnMowerList)
      .getBytes(StandardCharsets.UTF_8)
  )

  println(s"Json file path ${outputJsonPath.toString}")
  println(s"CSV file path ${outputCsvPath.toString}")
}
