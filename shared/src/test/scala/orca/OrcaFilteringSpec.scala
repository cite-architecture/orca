package edu.holycross.shot.orca
import org.scalatest.FlatSpec
import edu.holycross.shot.cite._
import scala.io.Source

/**
*/
class OrcaFilteringSpec extends FlatSpec {
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


  "An ORCA Collection" should "filter by URN matching on passage analyzed" in pending /*{
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
*/
}
