package edu.holycross.shot
package orca {

  case class OrcaException(message: String = "", cause: Option[Throwable] = None) extends Exception(message) {
    cause.foreach(initCause)
  }

}
