package edu.holycross.shot
package orca {


  import scala.scalajs.js
  import scala.scalajs.js.annotation._


  @JSExportAll case class OrcaException(message: String = "", cause: Option[Throwable] = None) extends Exception(message) {
    cause.foreach(initCause)
  }

}
