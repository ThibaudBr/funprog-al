package fr.esgi.al.funprog.models

case class Direction(direction: Char) {
  direction match {
    case 'N' | 'S' | 'E' | 'W' =>
    case _                     =>
  }

  def rotateRight(): Direction = direction match {
    case 'N' => Direction('E')
    case 'E' => Direction('S')
    case 'S' => Direction('W')
    case 'W' => Direction('N')
    case _   => this
  }

  def rotateLeft(): Direction = direction match {
    case 'N' => Direction('W')
    case 'W' => Direction('S')
    case 'S' => Direction('E')
    case 'E' => Direction('N')
    case _   => this
  }

  override def toString: String = direction.toString
}
