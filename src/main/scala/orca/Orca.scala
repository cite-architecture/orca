package edu.holycross.shot.orca

import edu.holycross.shot.cite._
import scala.io.Source
import scala.collection.mutable.ArrayBuffer

case class OrcaAlignment(urn: Cite2Urn, passage: CtsUrn, analysis: Cite2Urn, deformation: String) {

  def urnMatch(filterUrn: CtsUrn) = {
    passage.urnMatch(filterUrn)
  }
  def urnMatch(filterUrn: Cite2Urn) = {
      analysis.urnMatch(filterUrn)
  }
  def urnMatch(textUrn: CtsUrn, objectUrn: Cite2Urn) = {
    (passage.urnMatch(textUrn) && analysis.urnMatch(objectUrn) )
  }


  /// orca work


  // index of range begin of u in reff
  def rangeBeginIndex (u: CtsUrn, reff: Vector[CtsUrn]) = {
    def trimmed = u.dropPassage
    val bgnRef = trimmed.toString + u.rangeBeginParts(0)
    val psgUrn = CtsUrn(bgnRef)
    val canonical = reff.filter(_.urnMatch(psgUrn))
    var beginIndex = reff.indexOf(canonical(0))
    beginIndex
  }

  def rangeEndIndex (u: CtsUrn, reff: Vector[CtsUrn]) = {
    def trimmed = u.dropPassage
    val bgnRef = trimmed.toString + u.rangeEndParts(0)
    val psgUrn = CtsUrn(bgnRef)
    val canonical = reff.filter(_.urnMatch(psgUrn))
    var beginIndex = reff.indexOf(canonical(0))
    beginIndex
  }

  def sliceRange(u: CtsUrn, reff: Vector[CtsUrn]) = {
    val i1 = rangeBeginIndex(u, reff)
    val i2 = rangeEndIndex(u, reff) + 1
    reff.slice(i1,i2)
  }
  def sliceNode(u: CtsUrn, reff: Vector[CtsUrn]) = {
    val trimmed = u.dropPassage
    val psgUrn = CtsUrn(trimmed.toString + u.passageNodeParts(0) )
    reff.filter(_.urnMatch(psgUrn))
  }



  def expandRange(reff: Vector[CtsUrn]) = {
    var expanded = ArrayBuffer[OrcaAlignment]()
    for (singleNode <- sliceRange(passage, reff)) {
      val newAlignment = this.copy(passage = singleNode)
      expanded +=  newAlignment
    }
    expanded.toVector
  }
  def expandPoint(reff: Vector[CtsUrn]) = {
    var expanded = ArrayBuffer[OrcaAlignment]()
    for (singleNode <- sliceNode(passage,reff)) {
      val newAlignment = this.copy(passage = singleNode)
      expanded +=  newAlignment
    }
    expanded.toVector
  }
  def expandUrn(reff: Vector[CtsUrn]) = {
    if (passage.isRange) {
      expandRange(reff)
    } else if (passage.isPoint){
      expandPoint(reff)
    } else {
      //?  deal with this on upgrade to cite lib allowing
      // third value
      Vector(this)
    }
  }

}

case class OrcaCollection (analyses: Vector[OrcaAlignment]) {
  def urnMatch(filterUrn: CtsUrn) = {
    analyses.filter(_.urnMatch(filterUrn))
  }
  def urnMatch(filterUrn: Cite2Urn) = {
    analyses.filter(_.urnMatch(filterUrn))
  }
  def urnMatch(textUrn: CtsUrn, objectUrn: Cite2Urn) = {
    analyses.filter(_.urnMatch(textUrn, objectUrn))
  }

  def expandUrns(reff: Vector[CtsUrn]) = {
    analyses.flatMap(oa => oa.expandUrn(reff))
  }

  def getPassages(urn: Cite2Urn) = {
    urnMatch(urn)
  }
  def countPassages(urn: Cite2Urn) = {
    urnMatch(urn).size
  }
  def getAnalyses(urn: CtsUrn) = {
    urnMatch(urn)
  }
  def countAnalyses(urn: CtsUrn) = {
    urnMatch(urn).size
  }


}

object OrcaCollection {

  def apply(f: String, separator: String = "\t"): OrcaCollection = {
    val stringPairs = Source.fromFile(f).getLines.toVector.map(_.split(separator))
    val alignments = stringPairs.tail.map( arr => OrcaAlignment(Cite2Urn(arr(0)), CtsUrn(arr(1)), Cite2Urn(arr(2)), arr(3)))
    OrcaCollection(alignments)
  }

}



    /** Identifier for the passage of text analyzed.
    *
    * Rdf verbs orca/rdf/analyzes <-> analyzedBy
    */

    /** Identifier for the result of the analysis.
    *
    * Rdf verbs orca/rdf/hasAnalysis <-> analysisFor
    */

    /** Text reading or deformation resulting from the analysis.
    *
    * Rdf verb -> orca/rdf/textDeformation
    */
