package parser

import fr.esgi.al.funprog.models.{Coordinates, Direction, LawnMower, Order, Position}
import org.scalatest.funspec.AnyFunSpec
import fr.esgi.al.funprog.parser.ParseOutput
class ParserOutputSpec extends AnyFunSpec {

  describe("ParseOutput") {
    describe("parseJsonToScv") {
      it("should return a string") {
        val lawnMowerList: List[LawnMower] = List(
          LawnMower(Position(Coordinates(1, 2), Direction('N')), Position(Coordinates(1, 2), Direction('N')), List(Order('G'), Order('A'), Order('G'), Order('A'), Order('G'), Order('A'), Order('G'), Order('A'), Order('A'))),
        LawnMower(Position(Coordinates(3, 3), Direction('W')), Position(Coordinates(3, 3), Direction('W')), List(Order('A'), Order('A'), Order('D'), Order('A'), Order('A'), Order('D'), Order('A'), Order('D'), Order('D'), Order('A')))
        )
        val result = ParseOutput.parseJsonToCsv(lawnMowerList)

        assert(result == "numéro;début_x;début_y;début_direction;fin_x;fin_y;fin_direction;instructions\n" +
          "0;1;2;N;1;2;N;GAGAGAGAA\n" +
          "1;3;3;W;3;3;W;AADAADADDA"
        )
      }
    }
  }
}
