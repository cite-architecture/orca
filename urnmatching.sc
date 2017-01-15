import edu.holycross.shot.orca._

import edu.holycross.shot.cite._
import scala.io.Source
val orca = OrcaCollection("src/test/resources/clauses.tsv")
val ilreff = Source.fromFile("src/test/resources/ilreff.txt").getLines.toVector.map(CtsUrn(_))


def ctsUrns = {
  println("*Equality:")
  val u1 = CtsUrn("urn:cts:greekLit:tlg0012.tlg001.msA:1.1")
  val u2 = CtsUrn("urn:cts:greekLit:tlg0012.tlg001.msA:1.1")
  println(" " + u1 + "\n\turnMatch \n " + u2 + " ? " + (u1 urnMatch u2))

  println("\n*Work containment:")
  val u3 = CtsUrn("urn:cts:greekLit:tlg0012.tlg001:1.1")
  println(" " + u1 + "\n\turnMatch \n " + u3 + " ? " + (u1 urnMatch u3))

  println("\n*Passage containment:")
  val u4 = CtsUrn("urn:cts:greekLit:tlg0012.tlg001.msA:1")
  println(" " + u1 + "\n\turnMatch \n " + u4 + " ? " + (u1 urnMatch u4))


  println("\n*Double containment:")
  val u5 = CtsUrn("urn:cts:greekLit:tlg0012.tlg001:1")
  println(" " + u1 + "\n\turnMatch \n " + u5 + " ? " + (u1 urnMatch u5))

  println("\n" + """*"Opposite-side" containment:""")
  println(" " + u3 + "\n\turnMatch \n " + u4 + " ? " + (u3 urnMatch u4))

  println("\n" + """*Contained range:""")
  val u6 = CtsUrn("urn:cts:greekLit:tlg0012.tlg001.msA:1.1@μῆνιν-1.1@θεά")
  println(" " + u1 + "\n\turnMatch \n " + u6 + " ? " + (u1 urnMatch u6))

  println("\n" + """*Overlapping range:""")
  val u7 = CtsUrn("urn:cts:greekLit:tlg0012.tlg001.msA:1.1@μῆνιν-1.2@οὐλομένην")
  println(" " + u1 + "\n\turnMatch \n " + u7 + " ? " + (u1 urnMatch u7))


  println("\n" + """*Contained range, example 2:""")
  println(" " + u4 + "\n\turnMatch \n " + u7 + " ? " + (u4 urnMatch u7))



  println("\n" + """*Contained range, example 3:""")
  val u8 = CtsUrn("urn:cts:greekLit:tlg0012.tlg001.msA:1.1-1.100")
  val u10 = CtsUrn("urn:cts:greekLit:tlg0012.tlg001.msA:1-2.1")
  println(" " + u8 + "\n\turnMatch \n " + u10 + " ? " + (u8 urnMatch u10))



  println("\n" + """*Indeterminate relation:""")

  val u9 = CtsUrn("urn:cts:greekLit:tlg0012.tlg001.msA:1.10-1.20")
  println(" " + u8 + "\n\turnMatch \n " + u9 + " ? " + (u8 urnMatch u9))
}


def cite2Urns = {
  println("*Equality:")
  val u1 = Cite2Urn("urn:cite2:hmt:msA.r1:12r")
  val u2 = Cite2Urn("urn:cite2:hmt:msA.r1:12r")
  println(" " + u1 + "\n\turnMatch \n " + u2 + " ? " + (u1 urnMatch u2))

  println("\n*Collection containment:")
  val u3 = Cite2Urn("urn:cite2:hmt:msA:12r")
  println(" " + u1 + "\n\turnMatch \n " + u3 + " ? " + (u1 urnMatch u3))

  println("\n*Object containment:")
  val u4 = Cite2Urn("urn:cite2:hmt:msA.r1:")
  println(" " + u1 + "\n\turnMatch \n " + u4 + " ? " + (u1 urnMatch u4))
/*

  println("\n*Double containment:")
  val u5 = CtsUrn("urn:cts:greekLit:tlg0012.tlg001:1")
  println(" " + u1 + "\n\turnMatch \n " + u5 + " ? " + (u1 urnMatch u5))

  println("\n" + """*"Opposite-side" containment:""")
  println(" " + u3 + "\n\turnMatch \n " + u4 + " ? " + (u3 urnMatch u4))
*/

}

def orcaFilters = {

}
def testSuite = {
  println("1. Test boolean URN matching on individual CTS URNs")
  ctsUrns

  println("\n\n2. Test boolean URN matching on individual Cite2 URNs")
  cite2Urns

  println("\n\n3. Test URN filtering on ORCA Collections")
  orcaFilters

}
