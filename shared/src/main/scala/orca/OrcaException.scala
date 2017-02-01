package edu.holycross.shot
package orca {


  import scala.scalajs.js
  import js.annotation.JSExport


  @JSExport case class OrcaException(message: String = "", cause: Option[Throwable] = None) extends Exception(message) {
    cause.foreach(initCause)
  }

}
