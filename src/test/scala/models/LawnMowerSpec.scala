package models

import fr.esgi.al.funprog.models.{
  Coordinates,
  Direction,
  LawnMower,
  Order,
  Position
}
import org.scalatest.funspec.AnyFunSpec

class LawnMowerSpec extends AnyFunSpec {
  describe("LawnMower") {
    it("should be initialized with a position and a direction") {
      val lawnMower = LawnMower(
        Position(
          Coordinates(1, 2),
          Direction('N')
        ),
        Position(
          Coordinates(1, 2),
          Direction('N')
        ),
        List(
          Order('A'),
          Order('G'),
          Order('A'),
          Order('G'),
          Order('A'),
          Order('G'),
          Order('A'),
          Order('G'),
          Order('A')
        )
      )

      assert(lawnMower.startingPosition.coordinates.x == 1)
      assert(lawnMower.startingPosition.coordinates.y == 2)
      assert(lawnMower.startingPosition.direction.direction == 'N')
      assert(lawnMower.currentPosition.coordinates.x == 1)
      assert(lawnMower.currentPosition.coordinates.y == 2)
      assert(lawnMower.currentPosition.direction.direction == 'N')
      assert(lawnMower.orders.size == 9)
    }

    it("should return Json Object") {
      val lawnMower = LawnMower(
        Position(
          Coordinates(1, 2),
          Direction('N')
        ),
        Position(
          Coordinates(1, 2),
          Direction('N')
        ),
        List(
          Order('A'),
          Order('G'),
          Order('A'),
          Order('G'),
          Order('A'),
          Order('G'),
          Order('A'),
          Order('G'),
          Order('A')
        )
      )

      assert(
        lawnMower.toJson
          .toString() == """{"debut":{"point":{"x":1,"y":2},"direction":"N"},"instructions":["A","G","A","G","A","G","A","G","A"],"fin":{"point":{"x":1,"y":2},"direction":"N"}}"""
      )
    }
  }

}
