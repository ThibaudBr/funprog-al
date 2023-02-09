package parser

import fr.esgi.al.funprog.models.{Coordinates, Direction, Position}
import fr.esgi.al.funprog.parser.ParseInput
import org.scalatest.funspec.AnyFunSpec

class ParserInputSpec extends AnyFunSpec {

  describe("ParserInput") {
    describe("parseLimit") {
      it("should parse limit") {
        val input = "5 5"
        val limit =
          ParseInput.parseLimit(List(input)).getOrElse(Coordinates(0, 0))

        assert(limit.x == 5)
        assert(limit.y == 5)
      }

      it(
        "should return a Failure of DonneesIncorrectException Invalid limit input when input is empty"
      ) {
        val input = ""
        assert(
          ParseInput.parseLimit(List(input)).isFailure
        )
      }

      it(
        "should return a Failure of  DonneesIncorrectException if input not a number"
      ) {
        val input = "5 a"
        assert(
          ParseInput.parseLimit(List(input)).isFailure
        )
      }

      it(
        "should return a Failure of DonneesIncorrectException if input negative"
      ) {
        val input = "-5 5"
        assert(
          ParseInput.parseLimit(List(input)).isFailure
        )
      }
    }

    describe("ParsePosition") {
      it("should parse position") {
        val input = "1 2 N"
        val limit =
          ParseInput.parseLimit(List("5 5")).getOrElse(Coordinates(0, 0))
        val position = ParseInput
          .parsePosition(limit, List(input))
          .getOrElse(Position(Coordinates(0, 0), Direction('Z')))

        assert(position.coordinates.x == 1)
        assert(position.coordinates.y == 2)
        assert(position.direction.toString == "N")
      }

      it("should return a Failure of  DonneesIncorrectException if input empty") {
        val input = ""
        val limit =
          ParseInput.parseLimit(List("5 5")).getOrElse(Coordinates(0, 0))
        assert(
          ParseInput.parsePosition(limit, List(input)).isFailure
        )
      }

      it(
        "should return a Failure of DonneesIncorrectException if input not a number"
      ) {
        val input = "1 a N"
        val limit =
          ParseInput.parseLimit(List("5 5")).getOrElse(Coordinates(0, 0))
        assert(
          ParseInput.parsePosition(limit, List(input)).isFailure
        )
      }

      it(
        "should return a Failure of DonneesIncorrectException if input negative"
      ) {
        val input = "-1 2 N"
        val limit =
          ParseInput.parseLimit(List("5 5")).getOrElse(Coordinates(0, 0))
        assert(
          ParseInput.parsePosition(limit, List(input)).isFailure
        )
      }

      it(
        "should return a Failure of DonneesIncorrectException if input out of limit"
      ) {
        val input = "6 2 N"
        val limit =
          ParseInput.parseLimit(List("5 5")).getOrElse(Coordinates(0, 0))
        assert(
          ParseInput.parsePosition(limit, List(input)).isFailure
        )
      }

      it(
        "should return a Failure of DonneesIncorrectException if input invalid direction"
      ) {
        val input = "1 2 A"
        val limit =
          ParseInput.parseLimit(List("5 5")).getOrElse(Coordinates(0, 0))
        assert(
          ParseInput.parsePosition(limit, List(input)).isFailure
        )
      }
    }

    describe("ParseOrders") {
      it("should parse orders") {
        val input = "AAGAGAGAA"
        val orders = ParseInput.parseOrders(List(input)).getOrElse(List.empty)

        assert(orders.size == 9)
      }

      it(
        "should return a Failure of DonneesIncorrectException if input invalid order"
      ) {
        val input = "AVVVAGAGAGAAZ"
        assert(
          ParseInput.parseOrders(List(input)).isFailure
        )
      }

      it(
        "should return a Failure of DonneesIncorrectException if input data contain space"
      ) {
        val input = "AAGAGAGAA AAGAGAGAA"
        assert(
          ParseInput.parseOrders(List(input)).isFailure
        )
      }
    }
  }

}
