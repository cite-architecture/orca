package edu.holycross.shot.orca
import org.scalatest.FlatSpec
import edu.holycross.shot.cite._
import scala.io.Source
import java.io._


/**
*/
class OrcaSourceSpec extends FlatSpec {


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

  val orca = OrcaSource.fromFile("jvm/src/test/resources/clauses.tsv")

  "An ORCA Collection" should "offer a constructor signature for instantiating a corpus from a delimited text file" in {
    orca match {
      case oc: OrcaCollection => assert(true)
      case  _ => fail("Should have created an OrcaCollection")
    }
    assert (orca.alignments.size == 11)
  }

  it should "expand range references to explicitly map all contained leaf nodes"in  pending /*{
    val ilreff = Source.fromFile("src/test/resources/ilreff.txt").getLines.toVector.map(CtsUrn(_))

    val expanded = orca.expandUrns(ilreff)
    assert (expanded.size > orca.alignments.size)
    for (ex <- expanded) {
      //println(ex)
    }
  }*/


  it should "expand nodes to leaf nodes from the same version" in pending


  it should "preserve subreferences in expanded ranges containing multiple leaf nodes" in pending /* {
    val alignment = OrcaAlignment(Cite2Urn("urn:cite2:hmt:clausereading.v1:clause3"), CtsUrn("urn:cts:greekLit:tlg0012.tlg001.fuPers:1.3@π[1]-1.4@ν[1]"),Cite2Urn("urn:cite2:hmt:iliadicClauses.v1:indicative"),"πολλὰς δ᾽ ἰφθίμους ψυχὰς Ἄϊδι προΐαψεν ἡρώων")
    val ilreff = Source.fromFile("src/test/resources/ilreff.txt").getLines.toVector.map(CtsUrn(_))
    val expanded = alignment.expandUrn(ilreff)

    assert(expanded(0).passage == CtsUrn("urn:cts:greekLit:tlg0012.tlg001.fuPers:1.3@π[1]"))
    assert(expanded(1).passage == CtsUrn("urn:cts:greekLit:tlg0012.tlg001.fuPers:1.4@ν[1]"))


  } */

  it should "preserve subreferences in expanded ranges referring to a single leaf node" in pending




  it should "have a method to write the collection in delimited text string" in pending /*{
    val ilreff = Source.fromFile("src/test/resources/ilreff.txt").getLines.toVector.map(CtsUrn(_))

    val expanded = OrcaCollection(orca.expandUrns(ilreff))
    assert(expanded.toDelimitedText("#").split("\n").size == 16)
  }*/



  /*
  it should "have a method to write the collection in delimited text format to a named file" in {
    val orca = OrcaCollection("src/test/resources/clauses.tsv")
    val ilreff = Source.fromFile("src/test/resources/ilreff.txt").getLines.toVector.map(CtsUrn(_))

    val expanded = OrcaCollection(orca.expandUrns(ilreff))
    val testFileName = "src/test/resources/testoutput.txt"
    expanded.writeDelimitedTextFile(testFileName, "#")
    val outputLines = Source.fromFile(testFileName).getLines.toVector
    assert(outputLines.size == expanded.alignments.size)
    val testFile = new File(testFileName)
    testFile.delete()
  }

  it should "have a method to write the collection in delimited text format to an existing file object" in {
    val orca = OrcaCollection("src/test/resources/clauses.tsv")
    val ilreff = Source.fromFile("src/test/resources/ilreff.txt").getLines.toVector.map(CtsUrn(_))

    val expanded = OrcaCollection(orca.expandUrns(ilreff))
    val testFileName = "src/test/resources/testoutput.txt"
    val testFile = new File(testFileName)
    expanded.writeDelimitedTextFile(testFile, "#")
    val outputLines = Source.fromFile(testFileName).getLines.toVector
    assert(outputLines.size == expanded.alignments.size)

    testFile.delete()
  }
*/

}
