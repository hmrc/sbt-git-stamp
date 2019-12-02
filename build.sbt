import uk.gov.hmrc.DefaultBuildSettings.targetJvm

val pluginName = "sbt-git-stamp"

lazy val root = Project(pluginName, base = file("."))
  .enablePlugins(SbtAutoBuildPlugin, SbtGitVersioning, SbtArtifactory)
  .settings(
    sbtPlugin                        := true,
    majorVersion                     := 6,
    makePublicallyAvailableOnBintray := true,
    organization                     := "uk.gov.hmrc",
    scalaVersion                     := "2.10.7",   //Pinned to 2.10 while on sbt 0.13 to match ^ cross build inferred version
    crossSbtVersions                 := Vector("0.13.18", "1.3.4"),
    libraryDependencies              ++=
      Seq(
        "com.github.nscala-time"    %% "nscala-time"        % "2.22.0",
        "org.eclipse.jgit"          % "org.eclipse.jgit"    % "4.11.9.201909030838-r",
        "org.scalatest"             %% "scalatest"          % "3.1.0"                   % Test,
        "com.vladsch.flexmark"      % "flexmark-all"        % "0.35.10"                 % Test
      )
  )
