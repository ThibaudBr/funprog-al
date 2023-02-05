package models

import fr.esgi.al.funprog.models.Direction
import org.scalatest.funspec.AnyFunSpec

class DirectionSpec extends AnyFunSpec {

  describe("Direction") {
    it("should be initialized with a North") {
      val direction = Direction('N')

      assert(direction.direction == 'N')
    }

    it("should be initialized with a South") {
      val direction = Direction('S')

      assert(direction.direction == 'S')
    }

    it("should be initialized with a East") {
      val direction = Direction('E')

      assert(direction.direction == 'E')
    }

    it("should be initialized with a West") {
      val direction = Direction('W')

      assert(direction.direction == 'W')
    }

    it("should rotate to the left from North") {
      val direction = Direction('N')

      assert(direction.rotateLeft().direction == 'W')
    }

    it("should rotate to the left from West") {
      val direction = Direction('W')

      assert(direction.rotateLeft().direction == 'S')
    }

    it("should rotate to the left from South") {
      val direction = Direction('S')

      assert(direction.rotateLeft().direction == 'E')
    }

    it("should rotate to the left from East") {
      val direction = Direction('E')

      assert(direction.rotateLeft().direction == 'N')
    }

    it("should rotate to the right from North") {
      val direction = Direction('N')

      assert(direction.rotateRight().direction == 'E')
    }

    it("should rotate to the right from East") {
      val direction = Direction('E')

      assert(direction.rotateRight().direction == 'S')
    }

    it("should rotate to the right from South") {
      val direction = Direction('S')

      assert(direction.rotateRight().direction == 'W')
    }

    it("should rotate to the right from West") {
      val direction = Direction('W')

      assert(direction.rotateRight().direction == 'N')
    }

    it("should return String object") {
      val direction = Direction('N')

      assert(direction.toString == "N")
    }
  }
}
