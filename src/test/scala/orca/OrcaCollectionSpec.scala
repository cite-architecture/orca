package edu.holycross.shot.orca
import org.scalatest.FlatSpec
import edu.holycross.shot.cite._
import scala.io.Source
import java.io._

/**
*/
class OrcaSpec extends FlatSpec {

  "An ORCA Collection" should "offer a constructor signature for instantiating a corpus from a delimited text file" in {
    val orca = OrcaCollection("src/test/resources/clauses.tsv")
    orca match {
      case oc: OrcaCollection => assert(true)
      case  _ => fail("Should have created an OrcaCollection")
    }
    assert (orca.alignments.size == 11)
  }

  it should "expand range references to explicitly map all contained leaf nodes"in {
    val orca = OrcaCollection("src/test/resources/clauses.tsv")
    val ilreff = Source.fromFile("src/test/resources/ilreff.txt").getLines.toVector.map(CtsUrn(_))

    val expanded = orca.expandUrns(ilreff)
    assert (expanded.size > orca.alignments.size)
    for (ex <- expanded) {
      //println(ex)
    }
  }


  it should "expand nodes to leaf nodes from the same version" in pending


  it should "preserve subreferences in expanded ranges containing multiple leaf nodes" in {
    val alignment = OrcaAlignment(Cite2Urn("urn:cite2:hmt:clausereading.v1:clause3"), CtsUrn("urn:cts:greekLit:tlg0012.tlg001.fuPers:1.3@π[1]-1.4@ν[1]"),Cite2Urn("urn:cite2:hmt:iliadicClauses.v1:indicative"),"πολλὰς δ᾽ ἰφθίμους ψυχὰς Ἄϊδι προΐαψεν ἡρώων")
    val ilreff = Source.fromFile("src/test/resources/ilreff.txt").getLines.toVector.map(CtsUrn(_))
    val expanded = alignment.expandUrn(ilreff)

    assert(expanded(0).passage == CtsUrn("urn:cts:greekLit:tlg0012.tlg001.fuPers:1.3@π[1]"))
    assert(expanded(1).passage == CtsUrn("urn:cts:greekLit:tlg0012.tlg001.fuPers:1.4@ν[1]"))


  }
  it should "preserve subreferences in expanded ranges referring to a single leaf node" in pending




  it should "have a method to write the collection in delimited text string" in {
    val orca = OrcaCollection("src/test/resources/clauses.tsv")
    val ilreff = Source.fromFile("src/test/resources/ilreff.txt").getLines.toVector.map(CtsUrn(_))

    val expanded = OrcaCollection(orca.expandUrns(ilreff))
    assert(expanded.toDelimitedText("#").split("\n").size == 16)
  }
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


}
