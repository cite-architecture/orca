package edu.holycross.shot.orca

import edu.holycross.shot.cite._
import scala.io.Source
import java.io._






object OrcaSource {
  def fromFile(f: String, delimiter: String = "\t"): OrcaCollection = {
    val delimitedText = Source.fromFile(f).getLines.mkString("\n")
    OrcaCollection(delimitedText, delimiter)
  }


}
