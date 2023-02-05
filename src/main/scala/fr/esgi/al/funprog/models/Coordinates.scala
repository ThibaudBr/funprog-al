package fr.esgi.al.funprog.models

import play.api.libs.json.{JsValue, Json}

case class Coordinates(x: Int, y: Int) {
  def move(direction: Direction): Coordinates = direction.direction match {
    case 'N' => Coordinates(x, y + 1)
    case 'S' => Coordinates(x, y - 1)
    case 'E' => Coordinates(x + 1, y)
    case 'W' => Coordinates(x - 1, y)
  }

  def toJson: JsValue = Json.obj(
    "x" -> x,
    "y" -> y
  )
}
