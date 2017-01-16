package edu.holycross.shot.orca

import edu.holycross.shot.cite._
import edu.holycross.shot.ohco2._

import scala.io.Source
import scala.collection.mutable.ArrayBuffer


case class OrcaCollection (alignments: Vector[OrcaAlignment]) {
  def urnMatch(filterUrn: CtsUrn): Vector[OrcaAlignment] = {
    alignments.filter(_.urnMatch(filterUrn))
  }
  def urnMatch(filterUrn: Cite2Urn) : Vector[OrcaAlignment] = {
    alignments.filter(_.urnMatch(filterUrn))
  }
  def urnMatch(textUrn: CtsUrn, objectUrn: Cite2Urn): Vector[OrcaAlignment] = {
    alignments.filter(_.urnMatch(textUrn, objectUrn))
  }

  def expandUrns(reff: Vector[CtsUrn]): Vector[OrcaAlignment] = {
    alignments.flatMap(oa => oa.expandUrn(reff))
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

  // baseUrn is a version- or exemplar-level URN.
  def toCorpus(baseUrn: CtsUrn): Corpus = {

/*
    // vector of IndexedAlignment s:
    val indexed = orca.zipWithIndex.map { case (oa, i) => IndexedAlignment(oa, i) }

    // vector of TextTriple s:
    val triples = indexed.map( ia => TextTriple(ia.idx, ia.oa.deformation, ia.oa.passage.dropSubref) )

    // map of URN -> vector of TextTriple
    val grouped = triples.groupBy(_.canonical)
    // map of URN -> vector of tuyples of (TextTriple,Int)
     val clusterSorted = for ( (k,v) <- grouped) yield (k, v.sortBy(_.globalSeq).zipWithIndex)

     // Sequence of Vector of (TextTriple, Int) tuples
    val nodes = for ( (k,v) <- clusterSorted) yield (v)
    val nodesv = nodes.toVector

    val newTriples = nodesv.flatMap (v => for ((tr: TextTriple,i: Int) <- v) yield ( TextTriple(globalSeq = tr.globalSeq, txt = tr.txt, canonical = CtsUrn(baseUrn.toString + tr.canonical.passageNodeRef + "." + i)) ))

    val cns = newTriples.sortBy(_.globalSeq).map(tr => CitableNode(text = tr.txt, urn = tr.canonical))
    val corpus = Corpus(cns)*/
/*
zip to index
group by urn node
sort by index using for yield
number per group
flatmap out to single vector
*/
    val nodes = Vector.empty[CitableNode]
    Corpus(nodes)
  }
}
