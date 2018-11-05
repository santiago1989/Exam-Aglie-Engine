name := "Exam-Aglie-Engine"

version := "0.1"

scalaVersion := "2.12.7"

resolvers ++= Seq(
  Resolver.sonatypeRepo("releases"),
  Resolver.jcenterRepo
)

addCompilerPlugin(
  "org.scalamacros" % "paradise" % "2.1.0" cross CrossVersion.full
)

//util dependencies
libraryDependencies ++= Seq(
  "org.jsoup" % "jsoup" % "1.11.2",
  "com.typesafe.scala-logging" %% "scala-logging" % "3.8.0"
)