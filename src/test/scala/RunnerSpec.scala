import fr.esgi.al.funprog.Runner
import fr.esgi.al.funprog.models.{
  Coordinates,
  Direction,
  LawnMower,
  Order,
  Position
}
import org.scalatest.funspec.AnyFunSpec

class RunnerSpec extends AnyFunSpec {
  describe("Runner") {
    it("should be initialized with a lawn and a list of lawnMowers") {
      val runner = Runner(
        Coordinates(5, 5),
        List(
          LawnMower(
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
          ),
          LawnMower(
            Position(
              Coordinates(3, 3),
              Direction('E')
            ),
            Position(
              Coordinates(3, 3),
              Direction('E')
            ),
            List(
              Order('A'),
              Order('A'),
              Order('D'),
              Order('A'),
              Order('A'),
              Order('D'),
              Order('A'),
              Order('D'),
              Order('D'),
              Order('A')
            )
          )
        )
      )

      assert(runner.lawnMowerList(0).currentPosition.coordinates.x == 1)
      assert(runner.lawnMowerList(0).currentPosition.coordinates.y == 2)
      assert(runner.lawnMowerList.size == 2)
    }

    it("should return Json Object") {
      val runner = Runner(
        Coordinates(5, 5),
        List(
          LawnMower(
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
          ),
          LawnMower(
            Position(
              Coordinates(3, 3),
              Direction('E')
            ),
            Position(
              Coordinates(3, 3),
              Direction('E')
            ),
            List(
              Order('A'),
              Order('A'),
              Order('D'),
              Order('A'),
              Order('A'),
              Order('D'),
              Order('A'),
              Order('D'),
              Order('D'),
              Order('A')
            )
          )
        )
      )

      assert(
        runner.toJson.toString ==
          """{"limite":{"x":5,"y":5},"tondeuses":[{"debut":{"point":{"x":1,"y":2},"direction":"N"},"instructions":["A","G","A","G","A","G","A","G","A"],"fin":{"point":{"x":1,"y":2},"direction":"N"}},{"debut":{"point":{"x":3,"y":3},"direction":"E"},"instructions":["A","A","D","A","A","D","A","D","D","A"],"fin":{"point":{"x":3,"y":3},"direction":"E"}}]}"""
      )
    }

    it("should follow order") {
      val runner = Runner(
        Coordinates(5, 5),
        List(
          LawnMower(
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
          ),
          LawnMower(
            Position(
              Coordinates(3, 3),
              Direction('E')
            ),
            Position(
              Coordinates(3, 3),
              Direction('E')
            ),
            List(
              Order('A'),
              Order('A'),
              Order('D'),
              Order('A'),
              Order('A'),
              Order('D'),
              Order('A'),
              Order('D'),
              Order('D'),
              Order('A')
            )
          )
        )
      )

      val test1 = runner.followOrder(Order('A'), runner.lawnMowerList(0))
      val test2 = runner.followOrder(Order('G'), runner.lawnMowerList(1))

      assert(test1.currentPosition.coordinates.x == 1)
      assert(test1.currentPosition.coordinates.y == 3)
      assert(test1.currentPosition.direction.direction == 'N')
      assert(test2.currentPosition.coordinates.x == 3)
      assert(test2.currentPosition.coordinates.y == 3)
      assert(test2.currentPosition.direction.direction == 'N')
    }

    it("should move forward") {
      val runner = Runner(
        Coordinates(5, 5),
        List(
          LawnMower(
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
          ),
          LawnMower(
            Position(
              Coordinates(3, 3),
              Direction('E')
            ),
            Position(
              Coordinates(3, 3),
              Direction('E')
            ),
            List(
              Order('A'),
              Order('A'),
              Order('D'),
              Order('A'),
              Order('A'),
              Order('D'),
              Order('A'),
              Order('D'),
              Order('D'),
              Order('A')
            )
          )
        )
      )

      val test1 = runner.moveForward(runner.lawnMowerList(0), Coordinates(5, 5))
      val test2 = runner.moveForward(runner.lawnMowerList(1), Coordinates(5, 5))

      assert(test1.currentPosition.coordinates.x == 1)
      assert(test1.currentPosition.coordinates.y == 3)
      assert(test1.currentPosition.direction.direction == 'N')
      assert(test2.currentPosition.coordinates.x == 4)
      assert(test2.currentPosition.coordinates.y == 3)
      assert(test2.currentPosition.direction.direction == 'E')
    }

    it("should rotate left") {
      val runner = Runner(
        Coordinates(5, 5),
        List(
          LawnMower(
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
          ),
          LawnMower(
            Position(
              Coordinates(3, 3),
              Direction('E')
            ),
            Position(
              Coordinates(3, 3),
              Direction('E')
            ),
            List(
              Order('A'),
              Order('A'),
              Order('D'),
              Order('A'),
              Order('A'),
              Order('D'),
              Order('A'),
              Order('D'),
              Order('D'),
              Order('A')
            )
          )
        )
      )

      val test1 = runner.rotateLeft(runner.lawnMowerList(0))
      val test2 = runner.rotateLeft(runner.lawnMowerList(1))

      assert(test1.currentPosition.coordinates.x == 1)
      assert(test1.currentPosition.coordinates.y == 2)
      assert(test1.currentPosition.direction.direction == 'W')
      assert(test2.currentPosition.coordinates.x == 3)
      assert(test2.currentPosition.coordinates.y == 3)
      assert(test2.currentPosition.direction.direction == 'N')
    }

    it("should rotate right") {
      val runner = Runner(
        Coordinates(5, 5),
        List(
          LawnMower(
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
          ),
          LawnMower(
            Position(
              Coordinates(3, 3),
              Direction('E')
            ),
            Position(
              Coordinates(3, 3),
              Direction('E')
            ),
            List(
              Order('A'),
              Order('A'),
              Order('D'),
              Order('A'),
              Order('A'),
              Order('D'),
              Order('A'),
              Order('D'),
              Order('D'),
              Order('A')
            )
          )
        )
      )

      val test1 = runner.rotateRight(runner.lawnMowerList(0))
      val test2 = runner.rotateRight(runner.lawnMowerList(1))

      assert(test1.currentPosition.coordinates.x == 1)
      assert(test1.currentPosition.coordinates.y == 2)
      assert(test1.currentPosition.direction.direction == 'E')
      assert(test2.currentPosition.coordinates.x == 3)
      assert(test2.currentPosition.coordinates.y == 3)
      assert(test2.currentPosition.direction.direction == 'S')
    }
  }
}
