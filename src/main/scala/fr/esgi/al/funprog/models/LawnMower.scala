package fr.esgi.al.funprog.models

import play.api.libs.json._

case class LawnMower(
    startingPosition: Position,
    currentPosition: Position,
    orders: List[Order]
) {
  def toJson: JsValue = Json.obj(
    "debut"        -> startingPosition.toJson,
    "instructions" -> orders.map(_.toString),
    "fin"          -> currentPosition.toJson
  )
}
