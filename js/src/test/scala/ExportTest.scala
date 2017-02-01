package edu.holycross.shot.orca

import edu.holycross.shot.cite._
import org.scalatest._

class ExportTest extends FlatSpec {

  "The orca library"  should "expose methods of OrcaAlignment" in {
      val alignment = OrcaAlignment(Cite2Urn("urn:cite2:hmt:clausereading.v1:clause3"), CtsUrn("urn:cts:greekLit:tlg0012.tlg001.fuPers:1.3@π[1]"),Cite2Urn("urn:cite2:hmt:iliadicClauses.v1:indicative"),"πολλὰς δ᾽ ἰφθίμους ψυχὰς Ἄϊδι προΐαψεν ἡρώων")

    assert(alignment.passage == CtsUrn("urn:cts:greekLit:tlg0012.tlg001.fuPers:1.3@π[1]") )
    assert(alignment.analysis == Cite2Urn("urn:cite2:hmt:iliadicClauses.v1:indicative"))
  }

}
