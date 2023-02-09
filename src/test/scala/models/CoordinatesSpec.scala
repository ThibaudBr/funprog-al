package models

import fr.esgi.al.funprog.models.{Coordinates, Direction}
import org.scalatest.funspec.AnyFunSpec

class CoordinatesSpec extends AnyFunSpec {
  describe("Coordiantes") {
    it("should be initialized with x and y") {
      val coords = Coordinates(1, 2)

      assert(coords.x == 1)
      assert(coords.y == 2)
    }

    it("should move north") {
      val coords = Coordinates(1, 2)
      val limit = Coordinates(5, 5)
      val newCoords = coords.move(Direction('N'), limit)

      assert(newCoords.x == 1)
      assert(newCoords.y == 3)
    }

    it("should move south") {
      val coords = Coordinates(1, 2)
      val limit = Coordinates(5, 5)
      val newCoords = coords.move(Direction('S'), limit)

      assert(newCoords.x == 1)
      assert(newCoords.y == 1)
    }

    it("should move east") {
      val coords = Coordinates(1, 2)
      val limit = Coordinates(5, 5)
      val newCoords = coords.move(Direction('E'), limit)

      assert(newCoords.x == 2)
      assert(newCoords.y == 2)
    }

    it("should move west") {
      val coords = Coordinates(1, 2)
      val limit = Coordinates(5, 5)
      val newCoords = coords.move(Direction('W'), limit)

      assert(newCoords.x == 0)
      assert(newCoords.y == 2)
    }

    it("should be transform to Json") {
      val coords = Coordinates(1, 2)

      val json = coords.toJson

      assert(json.toString == """{"x":1,"y":2}""")
    }

    it("should not move north if it's at the limit") {
      val coords = Coordinates(1, 5)
      val limit = Coordinates(5, 5)
      val newCoords = coords.move(Direction('N'), limit)

      assert(newCoords.x == 1)
      assert(newCoords.y == 5)
    }

    it("should not move south if it's at the limit") {
      val coords = Coordinates(1, 0)
      val limit = Coordinates(5, 5)
      val newCoords = coords.move(Direction('S'), limit)

      assert(newCoords.x == 1)
      assert(newCoords.y == 0)
    }

    it("should not move east if it's at the limit") {
      val coords = Coordinates(5, 2)
      val limit = Coordinates(5, 5)
      val newCoords = coords.move(Direction('E'), limit)

      assert(newCoords.x == 5)
      assert(newCoords.y == 2)
    }

    it("should not move west if it's at the limit") {
      val coords = Coordinates(0, 2)
      val limit = Coordinates(5, 5)
      val newCoords = coords.move(Direction('W'), limit)

      assert(newCoords.x == 0)
      assert(newCoords.y == 2)
    }

    it("should not move if it's at the limit") {
      val coords = Coordinates(0, 0)
      val limit = Coordinates(5, 5)
      val newCoords = coords.move(Direction('W'), limit)

      assert(newCoords.x == 0)
      assert(newCoords.y == 0)
    }
  }
}
