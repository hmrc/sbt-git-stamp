import uk.gov.hmrc.DefaultBuildSettings.targetJvm

lazy val root = Project("sbt-git-stamp", file("."))
  .settings(
    sbtPlugin           := true,
    majorVersion        := 6,
    isPublicArtefact    := true,
    organization        := "uk.gov.hmrc",
    scalaVersion        := "2.12.18",
    libraryDependencies ++=
      Seq(
        "com.github.nscala-time" %% "nscala-time"        % "2.22.0",
        "org.eclipse.jgit"       %  "org.eclipse.jgit"   % "4.11.9.201909030838-r",
        "org.scalatest"          %% "scalatest"          % "3.2.17"                % Test,
        "com.vladsch.flexmark"   %  "flexmark-all"       % "0.64.8"                % Test
      )
  )
