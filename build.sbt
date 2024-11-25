import uk.gov.hmrc.DefaultBuildSettings.targetJvm

lazy val root = Project("sbt-git-stamp", file("."))
  .settings(
    sbtPlugin           := true,
    majorVersion        := 6,
    isPublicArtefact    := true,
    organization        := "uk.gov.hmrc",
    scalaVersion        := "2.12.20",
    libraryDependencies ++=
      Seq(
        "org.eclipse.jgit"       %  "org.eclipse.jgit"   % "6.10.0.202406032230-r", // 7.x requires all client to be on Java 21
        "org.scalatest"          %% "scalatest"          % "3.2.17"                % Test,
        "com.vladsch.flexmark"   %  "flexmark-all"       % "0.64.8"                % Test
      )
  )
