package edu.holycross.shot.orca

import edu.holycross.shot.cite._

case class OrcaAlignment(urn: CiteUrn, passage: CtsUrn, analysis: CiteUrn, deformation: String) {

  def urnMatch(filterUrn: CtsUrn) = {
    passage.urnMatch(filterUrn)
  }
  def urnMatch(filterUrn: CiteUrn) = {
    throw OrcaException("urnMatch not yet implemented on CiteUrns")
  }
  def urnMatch(textUrn: CtsUrn, objectUrn: CiteUrn) = {
    // to be implemented
  }
}

case class OrcaCollection (analyses: Vector[OrcaAlignment])



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
