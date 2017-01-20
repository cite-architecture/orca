package edu.holycross.shot.orca

import org.scalatest.FlatSpec
import edu.holycross.shot.cite._
import scala.io.Source

/**
*/
class OrcaAlignmentSpec extends FlatSpec {

  "An ORCA alignment" should "have a URN" in pending
  it should "have a passage URN" in pending
  it should "have an analysis URN" in pending
  it should "have a string value for text deformation" in pending

  it should "have a function to expand range references" in pending
  it should "have a function to expand containing references" in pending
  it should "preserve subreferences in expanded leaf nodes" in {
    val alignment = OrcaAlignment(Cite2Urn("urn:cite2:hmt:clausereading.v1:clause3"), CtsUrn("urn:cts:greekLit:tlg0012.tlg001.fuPers:1.3@π[1]"),Cite2Urn("urn:cite2:hmt:iliadicClauses.v1:indicative"),"πολλὰς δ᾽ ἰφθίμους ψυχὰς Ἄϊδι προΐαψεν ἡρώων")

    val ilreff = Source.fromFile("src/test/resources/ilreff.txt").getLines.toVector.map(CtsUrn(_))
    val expanded = alignment.expandUrn(ilreff)
    assert(expanded(0).passage == CtsUrn("urn:cts:greekLit:tlg0012.tlg001.fuPers:1.3@π[1]"))
  }

  it should "have a function to format the alignment as delimited text" in {
      val alignment = OrcaAlignment(Cite2Urn("urn:cite2:hmt:clausereading.v1:clause3"), CtsUrn("urn:cts:greekLit:tlg0012.tlg001.fuPers:1.3@π[1]-1.4@ν[1]"),Cite2Urn("urn:cite2:hmt:iliadicClauses.v1:indicative"),"πολλὰς δ᾽ ἰφθίμους ψυχὰς Ἄϊδι προΐαψεν ἡρώων")
      assert( alignment.rowString("#") == "urn:cite2:hmt:clausereading.v1:clause3#urn:cts:greekLit:tlg0012.tlg001.fuPers:1.3@π[1]-1.4@ν[1]#urn:cite2:hmt:iliadicClauses.v1:indicative#πολλὰς δ᾽ ἰφθίμους ψυχὰς Ἄϊδι προΐαψεν ἡρώων")
  }
}
