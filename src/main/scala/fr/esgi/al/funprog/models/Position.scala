package fr.esgi.al.funprog.models

import play.api.libs.json._

case class Position(coordinates: Coordinates, direction: Direction) {
  def move(): Position = Position(coordinates.move(direction), direction)
  def rotateRight(): Position = Position(coordinates, direction.rotateRight())
  def rotateLeft(): Position = Position(coordinates, direction.rotateRight())

  def toJson: JsValue = Json.obj(
    "point"     -> coordinates.toJson,
    "direction" -> direction.toString
  )
}
