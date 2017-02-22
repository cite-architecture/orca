import edu.holycross.shot.orca._

import edu.holycross.shot.cite._
import edu.holycross.shot.ohco2._

import scala.io.Source

val raw = OrcaSource.fromFile("jvm/src/test/resources/clauses.tsv")
val ilreff = Source.fromFile("jvm/src/test/resources/ilreff.txt").getLines.toVector.map(CtsUrn(_))

val orca = OrcaCollection(raw.expandUrns(ilreff))
val corpus = orca.toCorpus(CtsUrn("urn:cts:greekLit:tlg0012.tlg001.hmtclauses:"))


val xfcols = corpus.to82xfVector
