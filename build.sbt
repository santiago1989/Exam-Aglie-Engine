name := "Exam-Aglie-Engine"

version := "0.1"

scalaVersion := "2.12.7"

resolvers ++= Seq(
  Resolver.sonatypeRepo("releases"),
  Resolver.jcenterRepo
)

resolvers += Resolver.url("bintray-sbt-plugins", url("https://dl.bintray.com/eed3si9n/sbt-plugins/"))(Resolver.ivyStylePatterns)


addCompilerPlugin(
  "org.scalamacros" % "paradise" % "2.1.0" cross CrossVersion.full
)

addSbtPlugin("com.eed3si9n" % "sbt-assembly" % "0.14.3")

//util dependencies
libraryDependencies ++= Seq(
  "org.jsoup" % "jsoup" % "1.11.2",
  "com.typesafe.scala-logging" %% "scala-logging" % "3.8.0"
)