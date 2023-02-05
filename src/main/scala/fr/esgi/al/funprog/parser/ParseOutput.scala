package fr.esgi.al.funprog.parser

import fr.esgi.al.funprog.models.LawnMower

object ParseOutput {

  def parseJsonToCsv(lawnMowers: List[LawnMower]): String =
    "numéro;début_x;début_y;début_direction;fin_x;fin_y;fin_direction;instructions" + forLoopCsv(
      lawnMowers,
      0
    )

  def forLoopCsv(lawnMowers: List[LawnMower], id: Int): String =
    lawnMowers match {
      case Nil => ""
      case head :: tail =>
        s"\n${id.toString};${head.startingPosition.coordinates.x.toString};${head.startingPosition.coordinates.y.toString};${head.startingPosition.direction.toString};${head.currentPosition.coordinates.x.toString};${head.currentPosition.coordinates.y.toString};${head.currentPosition.direction.toString};${head.orders.mkString}" + forLoopCsv(
          tail,
          id + 1
        )
    }
}
