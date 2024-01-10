ThisBuild / scalaVersion := "2.13.12"
ThisBuild / version      := "0.1.0"
ThisBuild / organization := "com.github.yongruifang"

val chiselVersion = "5.1.0"
val chiseltestVersion = "5.0.2"
lazy val root = (project in file("."))
	.settings(
		name := "easypath",
		libraryDependencies ++= Seq(
			"org.chipsalliance" %% "chisel" % chiselVersion,
			"edu.berkeley.cs" %% "chiseltest" % chiseltestVersion % "test"
		),
		scalacOptions ++= Seq(
			"-language:reflectiveCalls",
			"-feature",
			"-Xcheckinit",
			"-Ymacro-annotations",
		),
		addCompilerPlugin("org.chipsalliance" % "chisel-plugin" % chiselVersion cross CrossVersion.full),
	)

