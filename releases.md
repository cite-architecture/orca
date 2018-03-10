#`orca`: release notes

**4.0.1**: Updated `ohco2` dependency fixes a bug in handling CTS catalogs in CEX format.

**4.0.0**: Breaks compatibility with earlier versions only by removing JVM-specific functions, and adding automated cross-building for multiple Scala versions in both JVM and ScalaJS environments.

**3.0**: to `OrcaCollection`, adds ++ and -- functions,  application of `~~` to Vectors of URNs, and filtering by `Corpus`.  Removes `toCorpus` method.

**2.3**: adds several higher-order functions to the `OrcaCollection` class.

**2.2**: adds `OrcaSource` to JVM subproject for instantiating ORCA Collections from local file sources.

**2.1**: introduces the twiddle operator ~~ for URN matching.

**2.0**: initial release implementing the code library from https://github.com/cite-architecture/orca in a cross-compiling build.
