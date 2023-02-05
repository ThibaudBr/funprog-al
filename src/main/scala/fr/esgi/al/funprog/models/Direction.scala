package fr.esgi.al.funprog.models

case class Direction(direction: Char) {
  direction match {
    case 'N' | 'S' | 'E' | 'W' =>
  }

  def rotateRight(): Direction = direction match {
    case 'N' => Direction('E')
    case 'E' => Direction('S')
    case 'S' => Direction('W')
    case 'W' => Direction('N')
  }

  def rotateLeft(): Direction = direction match {
    case 'N' => Direction('W')
    case 'W' => Direction('S')
    case 'S' => Direction('E')
    case 'E' => Direction('N')
  }

  override def toString: String = direction.toString
}
