package edu.holycross.shot.orca
import org.scalatest.FlatSpec
import edu.holycross.shot.cite._
import scala.io.Source
import java.io._



/**
*/
class OrcaCollectionSpec extends FlatSpec {
  val clauses = """ORCA_URN	AnalyzedText	Analysis	textDeformation
urn:cite2:hmt:clausereading.v1:clause1	urn:cts:greekLit:tlg0012.tlg001.fuPers:1.1-1.2@ν[2]	urn:cite2:hmt:iliadicClauses.v1:imperative	Μῆνιν ἄειδε θεὰ Πηληϊάδεω Ἀχιλῆος οὐλομένην
urn:cite2:hmt:clausereading.v1:clause2	urn:cts:greekLit:tlg0012.tlg001.fuPers:1.2@ἣ[1]-1.2@ε[2]	urn:cite2:hmt:iliadicClauses.v1:indicative	ἣ μυρί᾽ Ἀχαιοῖς ἄλγε᾽ ἔθηκε
urn:cite2:hmt:clausereading.v1:clause3	urn:cts:greekLit:tlg0012.tlg001.fuPers:1.3@π[1]-1.4@ν[1]	urn:cite2:hmt:iliadicClauses.v1:indicative	πολλὰς δ᾽ ἰφθίμους ψυχὰς Ἄϊδι προΐαψεν ἡρώων
urn:cite2:hmt:clausereading.v1:clause4	urn:cts:greekLit:tlg0012.tlg001.fuPers:1.4@α[1]-1.5@ι[1]	urn:cite2:hmt:iliadicClauses.v1:indicative	αὐτοὺς δὲ ἑλώρια τεῦχε κύνεσσιν οἰωνοῖσί τε πᾶσ
urn:cite2:hmt:clausereading.v1:clause5	urn:cts:greekLit:tlg0012.tlg001.fuPers:1.5@Δ[1]-1.5@ή[1]	urn:cite2:hmt:iliadicClauses.v1:indicative	Διὸς δ᾽ ἐτελείετο βουλή
urn:cite2:hmt:clausereading.v1:clause6	urn:cts:greekLit:tlg0012.tlg001.fuPers:1.6-1.7	urn:cite2:hmt:iliadicClauses.v1:subordinate	ἐξ οὗ δὴ τὰ πρῶτα διαστήτην ἐρίσαντε Ἀτρεΐδης τε ἄναξ ἀνδρῶν καὶ δῖος Ἀχιλλεύς.§§f
urn:cite2:hmt:clauseclausereading.v1:clause7	urn:cts:greekLit:tlg0012.tlg001.fuPers:1.8	urn:cite2:hmt:iliadicClauses.v1:interrogative	Τίς τάρ σφωε θεῶν ἔριδι ξυνέηκε μάχεσθαι;
urn:cite2:hmt:clausereading.v1:clause8	urn:cts:greekLit:tlg0012.tlg001.fuPers:1.9@Λ[1]-1.9@ς[3]	urn:cite2:hmt:iliadicClauses.v1:indicative	Λητοῦς καὶ Διὸς υἱός [ξυνέηκε]
urn:cite2:hmt:clausereading.v1:clause9	urn:cts:greekLit:tlg0012.tlg001.fuPers:1.9@ὃ[1]-1.10@ν[5]	urn:cite2:hmt:iliadicClauses.v1:indicative	ὃ γὰρ βασιλῆϊ χολωθεὶς νοῦσον ἀνὰ στρατὸν ὄρσε κακήν
urn:cite2:hmt:clausereading.v1:clause10	urn:cts:greekLit:tlg0012.tlg001.fuPers:1.10@ὀ[1]-1.10@ί[1]	urn:cite2:hmt:iliadicClauses.v1:indicative	ὀλέκοντο δὲ λαοί
urn:cite2:hmt:clausereading.v1:clause11	urn:cts:greekLit:tlg0012.tlg001.fuPers:2.1@οὐδέ[1]-2.1@ἐΐσης[1]	urn:cite2:hmt:iliadicClauses.v1:indicative	οὐδέ τι θυμὸς ἐδεύετο δαιτὸς ἐΐσης
"""

  val orca = OrcaCollection(clauses)

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
