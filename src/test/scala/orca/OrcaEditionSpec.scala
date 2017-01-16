package edu.holycross.shot.orca
import org.scalatest.FlatSpec
import edu.holycross.shot.cite._
import scala.io.Source

/**
*/
class OrcaEditionSpec extends FlatSpec {

  "An ORCA Collection" should "make editions" in  {
    val raw = OrcaCollection("src/test/resources/clauses.tsv")
    val ilreff = Source.fromFile("src/test/resources/ilreff.txt").getLines.toVector.map(CtsUrn(_))

    val orca = OrcaCollection(raw.expandUrns(ilreff))
    val corpus = orca.toCorpus(CtsUrn("urn:cts:greekLit:tlg0012.tlg001.hmtclauses:"))

    // test results for right total size:
    val expectedClauses = 16
    assert( corpus.texts.size == expectedClauses)


    // test a sample of the generated URNs:
    val expectedFirstThree = Vector(
      CtsUrn("urn:cts:greekLit:tlg0012.tlg001.hmtclauses:1.1.0"),
      CtsUrn("urn:cts:greekLit:tlg0012.tlg001.hmtclauses:1.2.0"),
      CtsUrn("urn:cts:greekLit:tlg0012.tlg001.hmtclauses:1.2.1")
    )
    assert (corpus.texts.slice(0,3).map(_.urn) == expectedFirstThree)
  }



}
