package fr.esgi.al.funprog

import fr.esgi.al.funprog.models.{Coordinates, LawnMower, Order}
import play.api.libs.json._

final case class Runner(limit: Coordinates, lawnMowerList: List[LawnMower]) {

  def run(): Runner = {
    Runner(limit, lawnMowerList.map(__startLawnMower))
  }

  private def __startLawnMower(lawnMower: LawnMower): LawnMower =
    lawnMower.currentPosition match {
      case lawnMower.startingPosition => __forLoop(lawnMower, lawnMower.orders)
      case _                          => lawnMower
    }

  private def __forLoop(lawnMower: LawnMower, orders: List[Order]): LawnMower =
    orders match {
      case Nil           => lawnMower
      case order :: tail => __forLoop(followOrder(order, lawnMower), tail)
    }

  def followOrder(order: Order, lawnMower: LawnMower): LawnMower = {
    order.order match {
      case 'A' => moveForward(lawnMower)
      case 'D' => rotateRight(lawnMower)
      case 'G' => rotateLeft(lawnMower)
    }
  }

  def moveForward(lawnMower: LawnMower): LawnMower =
    lawnMower.currentPosition.direction.direction match {
      case 'N' =>
        LawnMower(
          lawnMower.startingPosition,
          lawnMower.currentPosition.move(),
          lawnMower.orders
        )
      case 'S' =>
        LawnMower(
          lawnMower.startingPosition,
          lawnMower.currentPosition.move(),
          lawnMower.orders
        )
      case 'E' =>
        LawnMower(
          lawnMower.startingPosition,
          lawnMower.currentPosition.move(),
          lawnMower.orders
        )
      case 'W' =>
        LawnMower(
          lawnMower.startingPosition,
          lawnMower.currentPosition.move(),
          lawnMower.orders
        )
    }

  def rotateRight(mower: LawnMower): LawnMower = {
    LawnMower(
      mower.startingPosition,
      mower.currentPosition.rotateRight(),
      mower.orders
    )
  }

  def rotateLeft(mower: LawnMower): LawnMower = {
    LawnMower(
      mower.startingPosition,
      mower.currentPosition.rotateLeft(),
      mower.orders
    )
  }

  def toJson: JsValue = Json.obj(
    "limite"    -> limit.toJson,
    "tondeuses" -> lawnMowerList.map(_.toJson)
  )
}

object Runner {
  def apply(limit: Coordinates, lawnMowerList: List[LawnMower]): Runner =
    new Runner(limit, lawnMowerList)

}
