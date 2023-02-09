package fr.esgi.al.funprog.models

import play.api.libs.json.{JsValue, Json}

case class Coordinates(x: Int, y: Int) {
  def move(direction: Direction, limit: Coordinates): Coordinates =
    direction.direction match {
      case 'N' => Coordinates(x, Math.min(y + 1, limit.y))
      case 'S' => Coordinates(x, Math.max(y - 1, 0))
      case 'E' => Coordinates(Math.min(x + 1, limit.x), y)
      case 'W' => Coordinates(Math.max(x - 1, 0), y)
      case _   => this
    }

  def toJson: JsValue = Json.obj(
    "x" -> x,
    "y" -> y
  )
}
