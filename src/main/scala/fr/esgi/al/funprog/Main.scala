package fr.esgi.al.funprog

import com.typesafe.config.Config
import com.typesafe.config.ConfigFactory
import better.files._
import fr.esgi.al.funprog.models.{
  Coordinates,
  Direction,
  LawnMower,
  Order,
  Position
}
import fr.esgi.al.funprog.parser.{ParseInput, ParseOutput}

import java.nio.charset.StandardCharsets
import java.nio.file.{Files, Paths}
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

  private val position1: Position =
    ParseInput
      .parsePosition(limit, dataInputFile.slice(1, 2))
      .getOrElse(
        Position(Coordinates(0, 0), Direction('Z'))
      )
  private val order1: List[Order] =
    ParseInput.parseOrders(dataInputFile.slice(2, 3)).getOrElse(List.empty)

  private val position2: Position =
    ParseInput
      .parsePosition(limit, dataInputFile.slice(3, 4))
      .getOrElse(
        Position(Coordinates(0, 0), Direction('Z'))
      )
  private val order2: List[Order] = ParseInput
    .parseOrders(dataInputFile.slice(4, 5))
    .getOrElse(
      List.empty
    )

  val lawnMowers: List[LawnMower] = List(
    LawnMower(position1, position1, order1),
    LawnMower(position2, position2, order2)
  )

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
