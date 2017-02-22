package edu.holycross.shot.orca

import org.scalatest.FlatSpec
import edu.holycross.shot.cite._
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

/*
    val tokens = "ORCA_URN#AnalyzedText#Analysis#TextDeformation\n" + "urn:cite2:hmt:clausereading.v1:clause1#urn:cts:greekLit:tlg0012.tlg001.msA:1.1@Μῆνιν#urn:cite2:hmt:lextype:lexical#Μῆνιν\n"


      val orca = OrcaCollection(tokens,"#")
      assert (orca.size == 1)
      val ln1 = CitableNode(CtsUrn("urn:cts:greekLit:tlg0012.tlg001.msA:1.1"),"Μῆνιν ἄειδε θεὰ Πηληϊάδεω Ἀχιλῆος")
      val ln2 =  CitableNode(CtsUrn("urn:cts:greekLit:tlg0012.tlg001.msA:1.2"),"οὐλομένην· ἡ μυρί᾽ Ἀχαιοῖς ἄλγε᾽ ἔθηκεν")

      val c = Corpus(Vector(ln1, ln2))

      val analyzedCorpus  = c ~~ orca
      assert (analyzedCorpus.size == 1)
    }
    */



}
