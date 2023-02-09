package models

import fr.esgi.al.funprog.models.{Coordinates, Direction, Position}
import org.scalatest.funspec.AnyFunSpec

class PositionSpec extends AnyFunSpec {

  describe("Position") {
    it("should be initialized with x, y and direction") {
      val position = Position(Coordinates(1, 2), Direction('N'))

      assert(position.coordinates.x == 1)
      assert(position.coordinates.y == 2)
      assert(position.direction.direction == 'N')
    }

    it("should move forward from North") {
      val position = Position(Coordinates(1, 2), Direction('N'))
      val limit = Coordinates(5, 5)
      val newPosition = position.move(limit)

      assert(newPosition.coordinates.x == 1)
      assert(newPosition.coordinates.y == 3)
      assert(newPosition.direction.direction == 'N')
    }

    it("should move forward from South") {
      val position = Position(Coordinates(1, 2), Direction('S'))
      val limit = Coordinates(5, 5)
      val newPosition = position.move(limit)

      assert(newPosition.coordinates.x == 1)
      assert(newPosition.coordinates.y == 1)
      assert(newPosition.direction.direction == 'S')
    }

    it("should move forward from East") {
      val position = Position(Coordinates(1, 2), Direction('E'))
      val limit = Coordinates(5, 5)
      val newPosition = position.move(limit)

      assert(newPosition.coordinates.x == 2)
      assert(newPosition.coordinates.y == 2)
      assert(newPosition.direction.direction == 'E')
    }

    it("should move forward from West") {
      val position = Position(Coordinates(1, 2), Direction('W'))
      val limit = Coordinates(5, 5)
      val newPosition = position.move(limit)

      assert(newPosition.coordinates.x == 0)
      assert(newPosition.coordinates.y == 2)
      assert(newPosition.direction.direction == 'W')
    }

    it("should rotate to the left from North") {
      val position = Position(Coordinates(1, 2), Direction('N'))
      val newPosition = position.rotateLeft()

      assert(newPosition.coordinates.x == 1)
      assert(newPosition.coordinates.y == 2)
      assert(newPosition.direction.direction == 'W')
    }

    it("should rotate to the left from West") {
      val position = Position(Coordinates(1, 2), Direction('W'))
      val newPosition = position.rotateLeft()

      assert(newPosition.coordinates.x == 1)
      assert(newPosition.coordinates.y == 2)
      assert(newPosition.direction.direction == 'S')
    }

    it("should rotate to the left from South") {
      val position = Position(Coordinates(1, 2), Direction('S'))
      val newPosition = position.rotateLeft()

      assert(newPosition.coordinates.x == 1)
      assert(newPosition.coordinates.y == 2)
      assert(newPosition.direction.direction == 'E')
    }

    it("should rotate to the left from East") {
      val position = Position(Coordinates(1, 2), Direction('E'))
      val newPosition = position.rotateLeft()

      assert(newPosition.coordinates.x == 1)
      assert(newPosition.coordinates.y == 2)
      assert(newPosition.direction.direction == 'N')
    }

    it("should rotate to the right from North") {
      val position = Position(Coordinates(1, 2), Direction('N'))
      val newPosition = position.rotateRight()

      assert(newPosition.coordinates.x == 1)
      assert(newPosition.coordinates.y == 2)
      assert(newPosition.direction.direction == 'E')
    }

    it("should rotate to the right from East") {
      val position = Position(Coordinates(1, 2), Direction('E'))
      val newPosition = position.rotateRight()

      assert(newPosition.coordinates.x == 1)
      assert(newPosition.coordinates.y == 2)
      assert(newPosition.direction.direction == 'S')
    }

    it("should rotate to the right from South") {
      val position = Position(Coordinates(1, 2), Direction('S'))
      val newPosition = position.rotateRight()

      assert(newPosition.coordinates.x == 1)
      assert(newPosition.coordinates.y == 2)
      assert(newPosition.direction.direction == 'W')
    }

    it("should rotate to the right from West") {
      val position = Position(Coordinates(1, 2), Direction('W'))
      val newPosition = position.rotateRight()

      assert(newPosition.coordinates.x == 1)
      assert(newPosition.coordinates.y == 2)
      assert(newPosition.direction.direction == 'N')
    }

    it("should return json representation") {
      val position = Position(Coordinates(1, 2), Direction('N'))
      val json = position.toJson.toString

      assert(json == """{"point":{"x":1,"y":2},"direction":"N"}""")
    }

  }
}
