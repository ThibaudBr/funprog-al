package fr.esgi.al.funprog.parser

import fr.esgi.al.funprog.exceptions.DonneesIncorectesException
import fr.esgi.al.funprog.models.{Coordinates, Direction, Order, Position}

@SuppressWarnings(Array("org.wartremover.warts.Throw"))
object ParseInput {

  def parseLimit(input: List[String]): Coordinates = input match {
    case line :: Nil =>
      line.split(" ").toList match {
        case limitX :: limitY :: Nil =>
          try {
            if (limitX.toInt < 0 || limitY.toInt < 0) {
              throw new DonneesIncorectesException("Given size is negative")
            } else {
              Coordinates(limitX.toInt, limitY.toInt)
            }
          } catch {
            case _: Throwable =>
              throw new DonneesIncorectesException("Given size is not a number")
          }
        case _ => throw new DonneesIncorectesException("Invalid limit input")
      }
    case _ => throw new DonneesIncorectesException("Invalid limit input")
  }

  def parsePosition(limit: Coordinates, input: List[String]): Position =
    input match {
      case line :: Nil =>
        line.split(" ").toList match {
          case x :: y :: direction :: Nil =>
            try {
              if (x.toInt > limit.x || y.toInt > limit.y || x.toInt < 0 || y.toInt < 0) {
                throw new DonneesIncorectesException(
                  "Given position is out of the limit"
                )
              } else if (!(direction.nonEmpty && List("N", "S", "E", "W")
                           .contains(direction.charAt(0).toString))) {
                throw new DonneesIncorectesException(
                  "Given direction is not valid"
                )
              } else {
                Position(
                  Coordinates(x.toInt, y.toInt),
                  Direction(direction.charAt(0))
                )
              }
            } catch {
              case _: Throwable =>
                throw new DonneesIncorectesException(
                  "Given position is not a number"
                )
            }
          case _ =>
            throw new DonneesIncorectesException("Invalid position input")
        }
      case _ => {
        println(input)
        throw new DonneesIncorectesException(
          "Invalid input for position"
        )
      }

    }

  def parseOrders(input: List[String]): List[Order] = input match {
    case line :: Nil =>
      line.toList.map { c: Char =>
        if (!List('G', 'D', 'A').contains(c)) {
          throw new Exception(s"Invalid character for order: ${c.toString}")
        } else {
          Order(c)
        }
      }
    case _ => throw new DonneesIncorectesException("Invalid orders input")
  }

}
