package edu.holycross.shot.orca

import edu.holycross.shot.cite._
import edu.holycross.shot.ohco2._

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
    if (canonical.size != 1) {
      throw OrcaException("No URNs found matching " + psgUrn )
    } else {
      var beginIndex = reff.indexOf(canonical(0))
      beginIndex
    }
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
      val expanded = expandRange(reff)
      val subref1 = prependSubref(expanded,passage.rangeBeginSubrefOption.getOrElse(""))
      appendSubref(subref1,passage.rangeEndSubrefOption.getOrElse(""))



    } else if (passage.isPoint){
      val expanded = expandPoint(reff)
      passage.passageNodeSubrefOption match {
        case None => expanded
        case s: Some[String] =>  prependSubref(expanded, s.get)
      }

    } else {
      //?  deal with this on upgrade to cite lib allowing
      // third value
      Vector(this)
    }
  }

}