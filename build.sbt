name := """image-upload-play"""
organization := "com.radovan.play"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.13.17"

libraryDependencies ++= Seq(
  guice,
  ehcache,
  jdbc,
  ws,
  "org.hibernate.orm" % "hibernate-core" % "7.1.4.Final",
  "jakarta.persistence" % "jakarta.persistence-api" % "3.2.0",
  "jakarta.servlet" % "jakarta.servlet-api" % "6.1.0" % "provided",
  "org.postgresql" % "postgresql" % "42.7.8",
  "com.zaxxer" % "HikariCP" % "7.0.2",
  "com.fasterxml.jackson.core" % "jackson-databind" % "2.20.0",
  "com.fasterxml.jackson.module" %% "jackson-module-scala" % "2.20.0",
  "org.modelmapper" % "modelmapper" % "3.2.5",
  "commons-io" % "commons-io" % "2.20.0"
)
