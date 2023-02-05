package fr.esgi.al.funprog.models

case class Order(order: Char) {
  order match {
    case 'A' | 'D' | 'G' =>
  }

  override def toString: String = order.toString
}
