package fr.esgi.al.funprog

import fr.esgi.al.funprog.models.{Coordinates, LawnMower, Order}
import play.api.libs.json._

import scala.annotation.tailrec

final case class Runner(limit: Coordinates, lawnMowerList: List[LawnMower]) {

  def run(): Runner = {
    Runner(limit, lawnMowerList.map(__startLawnMower))
  }

  private def __startLawnMower(lawnMower: LawnMower): LawnMower =
    lawnMower.currentPosition match {
      case lawnMower.startingPosition => __forLoop(lawnMower, lawnMower.orders)
      case _                          => lawnMower
    }

  @tailrec
  private def __forLoop(lawnMower: LawnMower, orders: List[Order]): LawnMower =
    orders match {
      case Nil           => lawnMower
      case order :: tail => __forLoop(followOrder(order, lawnMower), tail)
      case _             => lawnMower
    }

  def followOrder(order: Order, lawnMower: LawnMower): LawnMower = {
    order.order match {
      case 'A' => moveForward(lawnMower, limit)
      case 'D' => rotateRight(lawnMower)
      case 'G' => rotateLeft(lawnMower)
      case _   => lawnMower
    }
  }

  def moveForward(lawnMower: LawnMower, limit: Coordinates): LawnMower =
    LawnMower(
      lawnMower.startingPosition,
      lawnMower.currentPosition.move(limit),
      lawnMower.orders
    )

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
