
organization := "edu.holycross.shot"
name := "orca"

version := "1.0.0"

scalaVersion := "2.11.7"


resolvers += "uh-nexus" at "http://beta.hpcc.uh.edu/nexus/content/groups/public"

libraryDependencies += "edu.holycross.shot" %% "cite" % "3.0.0"

libraryDependencies += "org.scalatest" %% "scalatest" % "2.2.6" %  "test"
libraryDependencies += "org.scala-lang.modules" %% "scala-xml" % "1.0.2"

publishTo := Some("Sonatype Snapshots Nexus" at "http://beta.hpcc.uh.edu/nexus/content/repositories/releases/")

credentials += Credentials(Path.userHome / "nexusauth.txt" )
