name := "akka-streams"

version := "0.1"

scalaVersion := "2.12.7"


coverageMinimum := 80
coverageFailOnMinimum := false

libraryDependencies += "com.typesafe.akka" %% "akka-stream" % "2.5.18"
libraryDependencies += "com.typesafe.akka" %% "akka-http" % "10.1.5"
libraryDependencies += "com.typesafe.akka" %% "akka-http-spray-json" % "10.1.5"
libraryDependencies += "org.mongodb.scala" %% "mongo-scala-driver" % "2.5.0"

libraryDependencies += "com.typesafe.scala-logging" %% "scala-logging" % "3.9.0"
libraryDependencies += "ch.qos.logback" % "logback-classic" % "1.2.3"


libraryDependencies += "org.scalactic" %% "scalactic" % "3.0.5"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.5" % "test"
libraryDependencies += "org.mockito" %% "mockito-scala" % "1.0.4" // % "test"
