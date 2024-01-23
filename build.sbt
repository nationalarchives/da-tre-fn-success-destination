ThisBuild / scalaVersion     := "2.13.12"
ThisBuild / organization     := "uk.gov.nationalarchives"

val awsVersion = "2.20.79"

lazy val root = (project in file("."))
  .settings(
    name := "da-tre-fn-success-destination",
    libraryDependencies ++= Seq(
      "com.amazonaws" % "aws-lambda-java-runtime-interface-client" % "2.3.1",
      "com.amazonaws" % "aws-lambda-java-events" % "3.11.1",
      "software.amazon.awssdk" % "sns" % awsVersion,
      "software.amazon.awssdk" % "sso" % awsVersion,
      "software.amazon.awssdk" % "ssooidc" % awsVersion,
      "org.scalatest" %% "scalatest" % "3.2.15" % Test,
      "org.scalatestplus" %% "mockito-4-11" % "3.2.16.0" % Test
    )
  ).settings(
    assembly / assemblyOutputPath := file("target/da-tre-fn-success-destination.jar")
  )

assemblyMergeStrategy in assembly := {
  case PathList("META-INF", "MANIFEST.MF") => MergeStrategy.discard
  case _ => MergeStrategy.first
}
