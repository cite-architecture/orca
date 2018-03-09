package edu.holycross.shot.orca

import edu.holycross.shot.cite._
import edu.holycross.shot.ohco2._

//import scala.io.Source
import java.io._
import scala.collection.mutable.ArrayBuffer


import scala.scalajs.js
import scala.scalajs.js.annotation._


@JSExportAll case class OrcaCollection (alignments: Vector[OrcaAlignment]) {

  def size : Int = {
    alignments.size
  }
  def urns : Vector[Cite2Urn] = {
    alignments.map(_.urn)
  }
  def analyses : Vector[Cite2Urn] = {
    alignments.map(_.analysis)
  }
  def texts : Vector[CtsUrn] = {
    alignments.map(_.passage)
  }


  def ++(orca2: OrcaCollection): OrcaCollection = {
    val sumAligned = alignments ++ orca2.alignments
    OrcaCollection(sumAligned)
  }

  def --(orca2: OrcaCollection): OrcaCollection = {
    OrcaCollection( alignments diff orca2.alignments)
  }


  def ~~(filterUrn: CtsUrn): OrcaCollection = {
    OrcaCollection(alignments.filter(_ ~~ filterUrn))
  }

  def ~~(filterUrn: Cite2Urn) : OrcaCollection = {
    //urnMatch(filterUrn)
    OrcaCollection(alignments.filter(_.~~(filterUrn)))
  }
  /*
  def ~~(textUrn: CtsUrn, objectUrn: Cite2Urn): OrcaCollection = {
    urnMatch(textUrn, objectUrn)
  }
  */

  def  ~~(urnV: Vector[Urn]): OrcaCollection = {
    val rslts = Vector.empty
    this.~~(urnV, OrcaCollection(rslts))
  }
  def ~~(urnV : Vector[Urn], resultOrca: OrcaCollection): OrcaCollection = {
    if (urnV.isEmpty ) {
      resultOrca
    } else {
      urnV.head match {
        case cts: CtsUrn => {
          val subVect = this.~~(cts)
          val newTotal = resultOrca ++ subVect
          this ~~(urnV.tail, newTotal)
        }
        case cite: Cite2Urn => {
          val subVect = this ~~ cite
          val newTotal = resultOrca ++ subVect
          this ~~(urnV.tail, newTotal)
        }
      }

    }
  }

  def ~~(c: Corpus) : OrcaCollection = {
    this ~~ c.urns
  }

  def expandUrns(reff: Vector[CtsUrn]): Vector[OrcaAlignment] = {
    alignments.flatMap(oa => oa.expandUrn(reff))
  }

/*
  def getPassages(urn: Cite2Urn) = {
    ~~(urn)
  }
  def countPassages(urn: Cite2Urn) = {
    ~~(urn).alignments.size
  }

  def getAnalyses(urn: CtsUrn) = {
    ~~(urn)
  }
  def countAnalyses(urn: CtsUrn) = {
    ~~(urn).alignments.size
  }
*/

  def toDelimitedText(delimiter: String): String = {
    alignments.map(_.rowString(delimiter)).mkString("\n")
  }

/*
  def writeDelimitedTextFile(fName: String, delimiter: String) {
    writeDelimitedTextFile(new File(fName), delimiter)
  }
  def writeDelimitedTextFile(f: File, delimiter: String) {
    val pw = new PrintWriter(f)
    pw.write(toDelimitedText(delimiter) + "\n")
    pw.close
  }
  */

  // baseUrn is a version- or exemplar-level URN.
/*
  def toCorpus(baseUrn: CtsUrn): Corpus = {
    // vector of IndexedAlignments:
    val indexed = alignments.zipWithIndex.map { case (oa, i) => IndexedAlignment(oa, i) }

    // vector of TextTriples:
    val triples = indexed.map( ia => TextTriple(ia.idx, ia.oa.deformation, ia.oa.passage.dropSubref) )

    // map of URN -> vector of TextTriples
    val grouped = triples.groupBy(_.canonical)
    // map of URN -> vector of tuples of (TextTriple,Int)
     val clusterSorted = for ( (k,v) <- grouped) yield (k, v.sortBy(_.globalSeq).zipWithIndex)

     // Sequence of Vector of (TextTriple, Int) tuples
    val nodes = for ( (k,v) <- clusterSorted) yield (v)
    val nodesv = nodes.toVector

    val newTriples = nodesv.flatMap (v => for ((tr: TextTriple,i: Int) <- v) yield ( TextTriple(globalSeq = tr.globalSeq, txt = tr.txt, canonical = CtsUrn(baseUrn.toString + tr.canonical.passageNodeRef + "." + i.toString)) ))

    val cns = newTriples.sortBy(_.globalSeq).map(tr => CitableNode(text = tr.txt, urn = tr.canonical))
    val corpus = Corpus(cns)
    corpus
  }*/

}

object OrcaCollection {
  def apply(data: String, separator: String = "\t"): OrcaCollection = {
    val stringPairs = data.split("\n").toVector.map(_.split(separator))
    val alignments = stringPairs.tail.map( arr => OrcaAlignment(Cite2Urn(arr(0)), CtsUrn(arr(1)), Cite2Urn(arr(2)), arr(3)))
    OrcaCollection(alignments)
  }

}
