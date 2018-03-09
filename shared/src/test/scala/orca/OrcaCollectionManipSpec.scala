package edu.holycross.shot.orca

import org.scalatest.FlatSpec
import edu.holycross.shot.cite._
import edu.holycross.shot.ohco2._
import scala.io.Source

/**
*/
class OrcaCollectionManipSpec extends FlatSpec {


  "An OrcaCollection" should "allow adding of a second collection" in {
    val ln1first = "ORCA_URN#AnalyzedText#Analysis#TextDeformation\n" + "urn:cite2:hmt:clausereading.v1:clause1#urn:cts:greekLit:tlg0012.tlg001.msA:1.1@Μῆνιν#urn:cite2:hmt:lextype:lexical#Μῆνιν\n"
    val orca1 = OrcaCollection(ln1first,"#")


    val ln2first = "ORCA_URN#AnalyzedText#Analysis#TextDeformation\n" + "urn:cite2:hmt:clausereading.v1:clause1#urn:cts:greekLit:tlg0012.tlg001.msA:1.1@οὐλομένην#urn:cite2:hmt:lextype:lexical#οὐλομένην\n"
    val orca2 = OrcaCollection(ln2first,"#")

    val twoOrca = orca1 ++ orca2
    assert (twoOrca.size == 2)

  }

  it should "allow subtraction of a second collection" in {
    val firstWords = "ORCA_URN#AnalyzedText#Analysis#TextDeformation\n" + "urn:cite2:hmt:clausereading.v1:clause1#urn:cts:greekLit:tlg0012.tlg001.msA:1.1@Μῆνιν#urn:cite2:hmt:lextype:lexical#Μῆνιν\n" + "urn:cite2:hmt:clausereading.v1:clause1#urn:cts:greekLit:tlg0012.tlg001.msA:1.2@οὐλομένην#urn:cite2:hmt:lextype:lexical#οὐλομένην\n" + "urn:cite2:hmt:clausereading.v1:clause1#urn:cts:greekLit:tlg0012.tlg001.msA:1.3@πολλὰς#urn:cite2:hmt:lextype:lexical#πολλὰς\n"
    val orca1 = OrcaCollection(firstWords,"#")


    val ln1first = "ORCA_URN#AnalyzedText#Analysis#TextDeformation\n" + "urn:cite2:hmt:clausereading.v1:clause1#urn:cts:greekLit:tlg0012.tlg001.msA:1.1@Μῆνιν#urn:cite2:hmt:lextype:lexical#Μῆνιν\n"
    val orca2 = OrcaCollection(ln1first,"#")

    assert(orca1.size == 3)
    val orcaDiff = orca1 -- orca2
    assert(orcaDiff.size == 2)
  }


  it should "allow filtering by a Vector of CtsUrns" in {
    val firstWords = "ORCA_URN#AnalyzedText#Analysis#TextDeformation\n" + "urn:cite2:hmt:clausereading.v1:clause1#urn:cts:greekLit:tlg0012.tlg001.msA:1.1@Μῆνιν#urn:cite2:hmt:lextype:lexical#Μῆνιν\n" + "urn:cite2:hmt:clausereading.v1:clause1#urn:cts:greekLit:tlg0012.tlg001.msA:1.2@οὐλομένην#urn:cite2:hmt:lextype:lexical#οὐλομένην\n" + "urn:cite2:hmt:clausereading.v1:clause1#urn:cts:greekLit:tlg0012.tlg001.msA:1.3@πολλὰς#urn:cite2:hmt:lextype:lexical#πολλὰς\n"
    val orca = OrcaCollection(firstWords,"#")

    val twoLines = Vector(CtsUrn("urn:cts:greekLit:tlg0012.tlg001:1.1"), CtsUrn("urn:cts:greekLit:tlg0012.tlg001:1.2"))

    val orcaFiltered = orca ~~ twoLines
    assert(orcaFiltered.size == 2)

  }


  it should "allow filtering by a Vector of Cite2Urns" in  {
    val analyses = "ORCA_URN#AnalyzedText#Analysis#TextDeformation\n" + "urn:cite2:hmt:clauseclausereading.v1:clause7#urn:cts:greekLit:tlg0012.tlg001.fuPers:1.8#urn:cite2:hmt:iliadicClauses.v1:interrogative#Τίς τάρ σφωε θεῶν ἔριδι ξυνέηκε μάχεσθαι;\n" + "urn:cite2:hmt:clausereading.v1:clause2#urn:cts:greekLit:tlg0012.tlg001.fuPers:1.2@ἣ[1]-1.2@ε[2]#urn:cite2:hmt:iliadicClauses.v1:indicative#ἣ μυρί᾽ Ἀχαιοῖς ἄλγε᾽ ἔθηκε\n"
    val orca = OrcaCollection(analyses,"#")

    val interrog = Cite2Urn("urn:cite2:hmt:iliadicClauses:indicative")
    val questOrca = orca ~~ interrog

    assert (orca.size == 2)
    assert(questOrca.size == 1)


  }


  it should "allow filtering by a Corpus" in {
    val tokens = "ORCA_URN#AnalyzedText#Analysis#TextDeformation\n" + "urn:cite2:hmt:clausereading.v1:clause1#urn:cts:greekLit:tlg0012.tlg001.msA:1.1@Μῆνιν#urn:cite2:hmt:lextype:lexical#Μῆνιν\n"


      val orca = OrcaCollection(tokens,"#")
      assert (orca.size == 1)
      val ln1 = CitableNode(CtsUrn("urn:cts:greekLit:tlg0012.tlg001.msA:1.1"),"Μῆνιν ἄειδε θεὰ Πηληϊάδεω Ἀχιλῆος")
      val ln2 =  CitableNode(CtsUrn("urn:cts:greekLit:tlg0012.tlg001.msA:1.2"),"οὐλομένην· ἡ μυρί᾽ Ἀχαιοῖς ἄλγε᾽ ἔθηκεν")

      val c = Corpus(Vector(ln1, ln2))

      val analyzedCorpus  = orca ~~ c
      assert (analyzedCorpus.size == 1)
    }




}
