ThisBuild / scalaVersion     := "2.13.16"
ThisBuild / organization     := "uk.gov.nationalarchives"

val awsVersion = "2.33.13"

lazy val root = (project in file("."))
  .settings(
    name := "da-tre-fn-success-destination",
    libraryDependencies ++= Seq(
      "com.amazonaws" % "aws-lambda-java-runtime-interface-client" % "2.8.6",
      "com.amazonaws" % "aws-lambda-java-events" % "3.16.1",
      "software.amazon.awssdk" % "sns" % awsVersion,
      "software.amazon.awssdk" % "sso" % awsVersion,
      "software.amazon.awssdk" % "ssooidc" % awsVersion,
      "org.scalatest" %% "scalatest" % "3.2.19" % Test,
      "org.scalatestplus" %% "mockito-4-11" % "3.2.18.0" % Test
    )
  ).settings(
    assembly / assemblyOutputPath := file("target/da-tre-fn-success-destination.jar")
  )

assemblyMergeStrategy in assembly := {
  case PathList("META-INF", "MANIFEST.MF") => MergeStrategy.discard
  case _ => MergeStrategy.first
}
