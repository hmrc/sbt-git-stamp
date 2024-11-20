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
        "org.eclipse.jgit"       %  "org.eclipse.jgit"   % "7.0.0.202409031743-r",
        "org.scalatest"          %% "scalatest"          % "3.2.17"                % Test,
        "com.vladsch.flexmark"   %  "flexmark-all"       % "0.64.8"                % Test
      )
  )
