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
      val newCoords = coords.move(Direction('N'))

      assert(newCoords.x == 1)
      assert(newCoords.y == 3)
    }

    it("should move south") {
      val coords = Coordinates(1, 2)
      val newCoords = coords.move(Direction('S'))

      assert(newCoords.x == 1)
      assert(newCoords.y == 1)
    }

    it("should move east") {
      val coords = Coordinates(1, 2)
      val newCoords = coords.move(Direction('E'))

      assert(newCoords.x == 2)
      assert(newCoords.y == 2)
    }

    it("should move west") {
      val coords = Coordinates(1, 2)
      val newCoords = coords.move(Direction('W'))

      assert(newCoords.x == 0)
      assert(newCoords.y == 2)
    }

    it("should be transform to Json") {
      val coords = Coordinates(1, 2)
      val json = coords.toJson

      assert(json.toString == """{"x":1,"y":2}""")
    }
  }
}
