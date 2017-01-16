package edu.holycross.shot

import edu.holycross.shot.cite._

package object orca {


  case class IndexedAlignment(oa: OrcaAlignment, idx: Int)
  case class TextTriple(globalSeq: Int, txt: String, canonical: CtsUrn )


  def prependSubref(reff: Vector[OrcaAlignment], sub: String) = {
    if ((reff.isEmpty) || (sub.isEmpty)) {
      reff
    } else {
      def urn1 = reff(0).passage
      if (urn1.isPoint) {
        val subrefUrn = CtsUrn(urn1.dropPassage + urn1.passageParts(0) + "@"+ sub)
        val subrefAlign = reff(0).copy(passage = subrefUrn)
        Vector(subrefAlign) ++ reff.drop(1)
      } else {
        reff
      }
    }
  }

  def appendSubref(reff: Vector[OrcaAlignment], sub: String) = {
    if (reff.isEmpty) {
      reff
    } else {
      def urn1 = reff.last.passage
      if (urn1.isPoint) {
        val subrefUrn = CtsUrn(urn1.dropPassage + urn1.passageParts(0) + "@"+ sub)
        val subrefAlign = reff.last.copy(passage = subrefUrn)
        reff.dropRight(1) ++ Vector(subrefAlign)
      } else {
        reff
      }
    }
  }
}
