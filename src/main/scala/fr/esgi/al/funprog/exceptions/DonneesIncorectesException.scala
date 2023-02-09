package fr.esgi.al.funprog.exceptions

final case class DonneesIncorectesException(errorMessage: String)
    extends Exception(errorMessage) {
  println(s"[DonneesIncorectesException] Error : $errorMessage")
}
