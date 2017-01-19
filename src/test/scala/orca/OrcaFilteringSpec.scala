package edu.holycross.shot.orca
import org.scalatest.FlatSpec
import edu.holycross.shot.cite._
import scala.io.Source

/**
*/
class OrcaFilteringSpec extends FlatSpec {

  "An ORCA Collection" should "filter by URN matching on passage analyzed" in {
    // "what do you know about Iliad 1.1?"
    val psg = CtsUrn("urn:cts:greekLit:tlg0012.tlg001:1.2")
    val orca = OrcaCollection("src/test/resources/clauses.tsv")
    val ilreff = Source.fromFile("src/test/resources/ilreff.txt").getLines.toVector.map(CtsUrn(_))
    val expanded = orca.expandUrns(ilreff)
    val firstLineAnalyses = expanded.filter(_.urnMatch(psg))
    for (orcaAnalysis <- firstLineAnalyses) {
      //println(orcaAnalysis)
    }
    assert (firstLineAnalyses.size == 2)
  }
  it should "filter by URN matching on analysis" in {
    // "what passages have you analyzed as indicative principal clause?"
    val analysis = Cite2Urn("urn:cite2:hmt:iliadicClauses.v1:indicative")
    val orca = OrcaCollection("src/test/resources/clauses.tsv")
    val ilreff = Source.fromFile("src/test/resources/ilreff.txt").getLines.toVector.map(CtsUrn(_))
    val expanded = orca.expandUrns(ilreff)
    val indicatives = expanded.filter(_.urnMatch(analysis))

    assert(indicatives.size == 11)

  }
  it should "support simultaneous filtering on passage and analysis" in {
    val analysis = Cite2Urn("urn:cite2:hmt:iliadicClauses.v1:indicative")
    val book1 = CtsUrn("urn:cts:greekLit:tlg0012.tlg001:1")

    val orca = OrcaCollection("src/test/resources/clauses.tsv")
    val ilreff = Source.fromFile("src/test/resources/ilreff.txt").getLines.toVector.map(CtsUrn(_))
    val expanded = orca.expandUrns(ilreff)
    val bk1indicatives = expanded.filter(_.urnMatch(book1, analysis))
    assert(bk1indicatives.size == 10)
  }
  it should "support chained filters" in {
    val analysis = Cite2Urn("urn:cite2:hmt:iliadicClauses.v1:indicative")
    val book1 = CtsUrn("urn:cts:greekLit:tlg0012.tlg001:1")

    val orca = OrcaCollection("src/test/resources/clauses.tsv")
    val ilreff = Source.fromFile("src/test/resources/ilreff.txt").getLines.toVector.map(CtsUrn(_))
    val expanded = orca.expandUrns(ilreff)
    val bk1indicatives = expanded.filter(_.urnMatch(book1)).filter(_.urnMatch(analysis))
    assert(bk1indicatives.size == 10)
  }

  it should "offer service-like aliases for counting passages" in {
    // "how many passages have you analyzed as indicative principal clause?"
    val analysis = Cite2Urn("urn:cite2:hmt:iliadicClauses.v1:indicative")
    val orca = OrcaCollection("src/test/resources/clauses.tsv")
    val ilreff = Source.fromFile("src/test/resources/ilreff.txt").getLines.toVector.map(CtsUrn(_))
    val expanded = orca.expandUrns(ilreff)
    assert(OrcaCollection(expanded).countPassages(analysis) == 11)
  }
  it should "offer service-like aliases for collecting passages" in {
    // "what passages have you analyzed as indicative principal clause?"
    val analysis = Cite2Urn("urn:cite2:hmt:iliadicClauses.v1:indicative")
    val orca = OrcaCollection("src/test/resources/clauses.tsv")
    val ilreff = Source.fromFile("src/test/resources/ilreff.txt").getLines.toVector.map(CtsUrn(_))
    val expanded = orca.expandUrns(ilreff)
    val indicatives = OrcaCollection(expanded).getPassages(analysis)

    assert(indicatives.alignments.size == 11)

  }

  it should "offer service-like aliases for collecting analyses" in {
    // "what do you know about Iliad 1.2?"
    val psg = CtsUrn("urn:cts:greekLit:tlg0012.tlg001:1.2")
    val orca = OrcaCollection("src/test/resources/clauses.tsv")
    val ilreff = Source.fromFile("src/test/resources/ilreff.txt").getLines.toVector.map(CtsUrn(_))
    val expanded = orca.expandUrns(ilreff)
    val firstLineAnalyses = OrcaCollection(expanded).getAnalyses(psg)

    assert (firstLineAnalyses.alignments.size == 2)
  }
  it should "offer service-like aliases for counting analyses" in {
    // "how many analyses do you have for Iliad 1.2?"
    val psg = CtsUrn("urn:cts:greekLit:tlg0012.tlg001:1.2")
    val orca = OrcaCollection("src/test/resources/clauses.tsv")
    val ilreff = Source.fromFile("src/test/resources/ilreff.txt").getLines.toVector.map(CtsUrn(_))
    val expanded = orca.expandUrns(ilreff)
    assert( OrcaCollection(expanded).countAnalyses(psg) == 2)

  }

}
