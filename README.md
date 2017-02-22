# `orca`


##What it is

`orca` is a cross-platform library for working with citable readings of citable texts.

##Current version: 2.2

Status:  **active development**. [Release notes](releases.md)

## License

[GPL 3.0](https://opensource.org/licenses/gpl-3.0.html)

## Using, building, testing

`orca` is compiled for both the JVM and ScalaJS using scala versions 2.10, 2.11 and 2.12. Binaries for all platforms are available from jcenter. If you are using sbt, include Resolver.jcenterRepoin your list of resolvers

    resolvers += Resolver.jcenterRepo

and add this to your library dependencies

    "edu.holycross.shot.cite" %%% "orca" %  VERSION

To build from source and test, use normal sbt commands (`compile`, `test` ...).
