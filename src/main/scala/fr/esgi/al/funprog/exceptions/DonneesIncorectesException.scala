package fr.esgi.al.funprog.exceptions

final case class DonneesIncorectesException(errorMessage: String)
    extends Exception(errorMessage)
