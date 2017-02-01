package edu.holycross.shot.orca
import edu.holycross.shot.cite._
object Main {
  def main(args: Array[String]): Unit = {

    val alignment = OrcaAlignment(Cite2Urn("urn:cite2:hmt:clausereading.v1:clause3"), CtsUrn("urn:cts:greekLit:tlg0012.tlg001.fuPers:1.3@π[1]"),Cite2Urn("urn:cite2:hmt:iliadicClauses.v1:indicative"),"πολλὰς δ᾽ ἰφθίμους ψυχὰς Ἄϊδι προΐαψεν ἡρώων")

    println("Passage analyzed: " + alignment.passage)
    println("Analysis: " + alignment.analysis)

  }
}
