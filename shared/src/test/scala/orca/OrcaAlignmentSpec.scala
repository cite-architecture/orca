package edu.holycross.shot.orca

import org.scalatest.FlatSpec
import edu.holycross.shot.cite._
import scala.io.Source

/**
*/
class OrcaAlignmentSpec extends FlatSpec {


  val ilreff = Vector(
    CtsUrn("urn:cts:greekLit:tlg0012.tlg001.fuPers:1.1"),
    CtsUrn("urn:cts:greekLit:tlg0012.tlg001.fuPers:1.2"),
    CtsUrn("urn:cts:greekLit:tlg0012.tlg001.fuPers:1.3"),
    CtsUrn("urn:cts:greekLit:tlg0012.tlg001.fuPers:1.4"),
    CtsUrn("urn:cts:greekLit:tlg0012.tlg001.fuPers:1.5"),
    CtsUrn("urn:cts:greekLit:tlg0012.tlg001.fuPers:1.6"),
    CtsUrn("urn:cts:greekLit:tlg0012.tlg001.fuPers:1.7"),
    CtsUrn("urn:cts:greekLit:tlg0012.tlg001.fuPers:1.8"),
    CtsUrn("urn:cts:greekLit:tlg0012.tlg001.fuPers:1.9"),
    CtsUrn("urn:cts:greekLit:tlg0012.tlg001.fuPers:1.10"),
    CtsUrn("urn:cts:greekLit:tlg0012.tlg001.fuPers:1.600"),
    CtsUrn("urn:cts:greekLit:tlg0012.tlg001.fuPers:1.601"),
    CtsUrn("urn:cts:greekLit:tlg0012.tlg001.fuPers:1.602"),
    CtsUrn("urn:cts:greekLit:tlg0012.tlg001.fuPers:1.603"),
    CtsUrn("urn:cts:greekLit:tlg0012.tlg001.fuPers:1.604"),
    CtsUrn("urn:cts:greekLit:tlg0012.tlg001.fuPers:1.605"),
    CtsUrn("urn:cts:greekLit:tlg0012.tlg001.fuPers:1.606"),
    CtsUrn("urn:cts:greekLit:tlg0012.tlg001.fuPers:1.607"),
    CtsUrn("urn:cts:greekLit:tlg0012.tlg001.fuPers:1.608"),
    CtsUrn("urn:cts:greekLit:tlg0012.tlg001.fuPers:1.609"),
    CtsUrn("urn:cts:greekLit:tlg0012.tlg001.fuPers:1.610"),
    CtsUrn("urn:cts:greekLit:tlg0012.tlg001.fuPers:1.611"),
    CtsUrn("urn:cts:greekLit:tlg0012.tlg001.fuPers:2.1"),
    CtsUrn("urn:cts:greekLit:tlg0012.tlg001.fuPers:2.2"),
    CtsUrn("urn:cts:greekLit:tlg0012.tlg001.fuPers:2.3"),
    CtsUrn("urn:cts:greekLit:tlg0012.tlg001.fuPers:2.4"),
    CtsUrn("urn:cts:greekLit:tlg0012.tlg001.fuPers:2.5"),
    CtsUrn("urn:cts:greekLit:tlg0012.tlg001.fuPers:2.6"),
    CtsUrn("urn:cts:greekLit:tlg0012.tlg001.fuPers:2.7"),
    CtsUrn("urn:cts:greekLit:tlg0012.tlg001.fuPers:2.8"),
    CtsUrn("urn:cts:greekLit:tlg0012.tlg001.fuPers:2.9"),
    CtsUrn("urn:cts:greekLit:tlg0012.tlg001.fuPers:2.10")
  )

  "An ORCA alignment" should "have a URN" in pending
  it should "have a passage URN" in pending
  it should "have an analysis URN" in pending
  it should "have a string value for text deformation" in pending

  it should "have a function to expand range references" in pending
  it should "have a function to expand containing references" in pending


  it should "preserve subreferences in expanded leaf nodes" in {
    val alignment = OrcaAlignment(Cite2Urn("urn:cite2:hmt:clausereading.v1:clause3"), CtsUrn("urn:cts:greekLit:tlg0012.tlg001.fuPers:1.3@π[1]"),Cite2Urn("urn:cite2:hmt:iliadicClauses.v1:indicative"),"πολλὰς δ᾽ ἰφθίμους ψυχὰς Ἄϊδι προΐαψεν ἡρώων")
    val expanded = alignment.expandUrn(ilreff)
    assert(expanded(0).passage == CtsUrn("urn:cts:greekLit:tlg0012.tlg001.fuPers:1.3@π[1]"))
  }

  it should "have a function to format the alignment as delimited text" in {
      val alignment = OrcaAlignment(Cite2Urn("urn:cite2:hmt:clausereading.v1:clause3"), CtsUrn("urn:cts:greekLit:tlg0012.tlg001.fuPers:1.3@π[1]-1.4@ν[1]"),Cite2Urn("urn:cite2:hmt:iliadicClauses.v1:indicative"),"πολλὰς δ᾽ ἰφθίμους ψυχὰς Ἄϊδι προΐαψεν ἡρώων")
      assert( alignment.rowString("#") == "urn:cite2:hmt:clausereading.v1:clause3#urn:cts:greekLit:tlg0012.tlg001.fuPers:1.3@π[1]-1.4@ν[1]#urn:cite2:hmt:iliadicClauses.v1:indicative#πολλὰς δ᾽ ἰφθίμους ψυχὰς Ἄϊδι προΐαψεν ἡρώων")
  }

}
