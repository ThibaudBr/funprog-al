package fr.esgi.al.funprog.parser

import fr.esgi.al.funprog.exceptions.DonneesIncorectesException
import fr.esgi.al.funprog.models.{Coordinates, Direction, Order, Position}

import scala.util.{Failure, Success, Try}

object ParseInput {

  def parseLimit(input: List[String]): Try[Coordinates] = input match {
    case line :: Nil =>
      line.split(" ").toList match {
        case limitX :: limitY :: Nil =>
          Try(limitX.toInt).flatMap(
            x =>
              Try(limitY.toInt)
                .map(
                  y =>
                    if (!(x < 0) && !(y < 0)) {
                      Success(Coordinates(x, y))
                    } else {
                      Failure(
                        DonneesIncorectesException("Given size is negative")
                      )
                    }
                )
                .flatMap(identity)
          )
        case _ => Failure(DonneesIncorectesException("Invalid limit input"))
      }
    case _ => Failure(DonneesIncorectesException("Invalid limit input"))
  }

  def parsePosition(limit: Coordinates, input: List[String]): Try[Position] = {
    input match {
      case line :: Nil =>
        line.split(" ").toList match {
          case x :: y :: direction :: Nil =>
            try {
              if (x.toInt > limit.x || y.toInt > limit.y || x.toInt < 0 || y.toInt < 0) {
                Failure(
                  DonneesIncorectesException(
                    "Given position is out of the limit"
                  )
                )
              } else if (!(direction.nonEmpty && List("N", "S", "E", "W")
                           .contains(direction.charAt(0).toString))) {
                Failure(
                  DonneesIncorectesException("Given direction is not valid")
                )
              } else {
                Success(
                  Position(
                    Coordinates(x.toInt, y.toInt),
                    Direction(direction.charAt(0))
                  )
                )
              }
            } catch {
              case _: NumberFormatException =>
                Failure(
                  DonneesIncorectesException("Invalid number for position")
                )
            }

          case _ =>
            Failure(DonneesIncorectesException("Invalid position input"))
        }
      case _ =>
        Failure(DonneesIncorectesException("Invalid input for position"))
    }
  }

  def parseOrders(input: List[String]): Try[List[Order]] = input match {
    case line :: Nil =>
      Try[List[Order]] {
        line.toList.map { c: Char =>
          if (!List('G', 'D', 'A').contains(c)) {
            Failure(
              DonneesIncorectesException(s"Invalid order for ${c.toString}")
            ).getOrElse(Order('Z'))
          } else {
            Order(c)
          }
        }
      } match {
        case Success(orders) =>
          Success(orders.collect { case order: Order => order }.toList)
        case Failure(e) =>
          Failure(
            DonneesIncorectesException(s"Invalid orders input: ${e.getMessage}")
          )
      }
    case _ => Failure(DonneesIncorectesException("Invalid orders input"))
  }

}
