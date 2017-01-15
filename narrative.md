## Using the ORCA library

Typical pattern of usage in Scala: import the library

    import edu.holycross.shot.orca._


## Build ORCA Collections

**1. read a delimited text file into an OrcaCollection object**

    val orca = OrcaCollection("src/test/resources/clauses.tsv")

The collection will have literal text urns as given in the source data.  To expand containing and range urns so that the ORCA mapping explicitly addresses all leaf nodes, proceed to step two.

**2. expand URNs to generate ORCA for leaf nodes**

You'll need a list of valid reff to do this.  If you have them in a simple file, one URN per line, you can do that as follows.  If you haven't  previously, import these:

    import scala.io.Source
    import edu.holycross.shot.cite._

Then,

    val reff = Source.fromFile("src/test/resources/ilreff.txt").getLines.toVector.map(CtsUrn(_))

This creates a Vector of `CtsUrn`s from your file.

Now you can expanded the URNs like so:

    val expanded = orca.expandUrns(reff)

You now have a complete ORCA collection for every leaf node covered in the reading/analysis.

## Analyze it!

Underneath the hood, the magic is to apply the `urnMatch` method on either the `passage` or `analysis` member of the `OrcaAlignment`.  Examples:

"Tell me everything you know about *Iliad* 1.1" ==

    val iliadUrn = CtsUrn("urn:cts:hmt:tlg0012.tlg001:1.1")
    val alignments = orca.filter(_.urnMatch(iliadUrn))


"Find every passage you have analyzed as a primary clause in the indicative" ==

    val clauseUrn = Cite2Urn("urn:cite2:hmt:iliadicClauses.v1:indicative")
    val alignments = orca.filter(_.urnMatch(clauseUrn))


In either case you can count the results with `alignments.size`.

Since scala `filter` generates the same kind of Vector it consumes, you can chain these.  That is very powerful:  we've now got an environemtn where anything you can say can be expressed with URN notation, and everything you say with URN notation can be realized.


## Service-like aliases

We can simplify the syntax (not yet tested!), to provide named functions like `findAnalyses` and `findPassages` or `countAnalyses`, `countPassages`
