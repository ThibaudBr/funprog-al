package parser

import fr.esgi.al.funprog.exceptions.DonneesIncorectesException
import fr.esgi.al.funprog.parser.ParseInput
import org.scalatest.funspec.AnyFunSpec

class ParserInputSpec extends AnyFunSpec {

  describe("ParserInput") {
    describe("parseLimit") {
      it("should parse limit") {
        val input = "5 5"
        val limit = ParseInput.parseLimit(List(input))

        assert(limit.x == 5)
        assert(limit.y == 5)
      }

      it("should throw an DonneesIncorrectException if input empty") {
        val input = ""
        val exception = intercept[DonneesIncorectesException] {
          ParseInput.parseLimit(List(input))
        }
        assert(exception.getMessage == "Invalid limit input")
      }

      it("should throw an DonneesIncorrectException if input not a number") {
        val input = "5 a"
        val exception = intercept[DonneesIncorectesException] {
          ParseInput.parseLimit(List(input))
        }
        assert(
          exception.getMessage == "Given size is not a number or negative value"
        )
      }

      it("should throw an DonneesIncorrectException if input negative") {
        val input = "-5 5"
        val exception = intercept[DonneesIncorectesException] {
          ParseInput.parseLimit(List(input))
        }
        assert(
          exception.getMessage == "Given size is not a number or negative value"
        )
      }
    }

    describe("ParsePosition") {
      it("should parse position") {
        val input = "1 2 N"
        val limit = ParseInput.parseLimit(List("5 5"))
        val position = ParseInput.parsePosition(limit, List(input))

        assert(position.coordinates.x == 1)
        assert(position.coordinates.y == 2)
        assert(position.direction.toString == "N")
      }

      it("should throw an DonneesIncorrectException if input empty") {
        val input = ""
        val limit = ParseInput.parseLimit(List("5 5"))
        val exception = intercept[DonneesIncorectesException] {
          ParseInput.parsePosition(limit, List(input))
        }
        assert(exception.getMessage == "Invalid position input")
      }

      it("should throw an DonneesIncorrectException if input not a number") {
        val input = "1 a N"
        val limit = ParseInput.parseLimit(List("5 5"))
        val exception = intercept[DonneesIncorectesException] {
          ParseInput.parsePosition(limit, List(input))
        }
        assert(exception.getMessage == "Error with given Position")
      }

      it("should throw an DonneesIncorrectException if input negative") {
        val input = "-1 2 N"
        val limit = ParseInput.parseLimit(List("5 5"))
        val exception = intercept[DonneesIncorectesException] {
          ParseInput.parsePosition(limit, List(input))
        }
        assert(exception.getMessage == "Error with given Position")
      }

      it("should throw an DonneesIncorrectException if input out of limit") {
        val input = "6 2 N"
        val limit = ParseInput.parseLimit(List("5 5"))
        val exception = intercept[DonneesIncorectesException] {
          ParseInput.parsePosition(limit, List(input))
        }
        assert(exception.getMessage == "Error with given Position")
      }

      it("should throw an DonneesIncorrectException if input invalid direction") {
        val input = "1 2 A"
        val limit = ParseInput.parseLimit(List("5 5"))
        val exception = intercept[DonneesIncorectesException] {
          ParseInput.parsePosition(limit, List(input))
        }
        assert(exception.getMessage == "Error with given Position")
      }
    }

    describe("ParseOrders") {
      it("should parse orders") {
        val input = "AAGAGAGAA"
        val orders = ParseInput.parseOrders(List(input))

        assert(orders.size == 9)
      }

      it("should throw an DonneesIncorrectException if input invalid order") {
        val input = "AVVVAGAGAGAAZ"
        val exception = intercept[DonneesIncorectesException] {
          ParseInput.parseOrders(List(input))
        }

        assert(exception.getMessage == "Invalid character for order: V")
      }

      it(
        "should throw an DonneesIncorrectException if input data contain space"
      ) {
        val input = "AAGAGAGAA AAGAGAGAA"
        val exception = intercept[DonneesIncorectesException] {
          ParseInput.parseOrders(List(input))
        }

        assert(exception.getMessage == "Invalid character for order:  ")
      }
    }
  }

}
