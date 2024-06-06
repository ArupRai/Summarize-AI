import Dependencies._

ThisBuild / scalaVersion     := "2.13.12"
ThisBuild / version          := "0.1.0-SNAPSHOT"
ThisBuild / organization     := "com.example"
ThisBuild / organizationName := "example"

lazy val root = (project in file("."))
  .settings(
    name := "My Scala-lib",
    libraryDependencies ++= Seq(
      // https://mvnrepository.com/artifact/net.sf.py4j/py4j
       "net.sf.py4j" % "py4j" % "0.10.9.3",
      "org.scalaj" %% "scalaj-http" % "2.4.2",
      "ch.qos.logback" % "logback-classic" % "1.2.10",
      "org.slf4j" % "slf4j-api" % "1.7.32",
      "com.typesafe.akka" %% "akka-actor-typed" % "2.6.20",
      "com.typesafe.akka" %% "akka-stream" % "2.6.20",
      "com.typesafe.akka" %% "akka-http" % "10.2.9",
      munit % Test
    ),
    resolvers += "jitpack" at "https://jitpack.io"
  )

// See https://www.scala-sbt.org/1.x/docs/Using-Sonatype.html for instructions on how to publish to Sonatype.
