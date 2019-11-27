import uk.gov.hmrc.DefaultBuildSettings.targetJvm

val pluginName = "sbt-git-stamp"

lazy val root = Project(pluginName, base = file("."))
  .enablePlugins(SbtAutoBuildPlugin, SbtGitVersioning, SbtArtifactory)
  .settings(
    sbtPlugin                        := true,
    majorVersion                     := 5,
    makePublicallyAvailableOnBintray := true,
    targetJvm                        := "jvm-1.7",
    organization                     := "uk.gov.hmrc",
    scalaVersion                     := "2.10.7",
    libraryDependencies              ++=
      Seq(
        "com.github.nscala-time"    %% "nscala-time"        % "2.22.0",
        "org.eclipse.jgit"          % "org.eclipse.jgit"    % "3.6.2.201501210735-r",
        "org.scalatest"             %% "scalatest"          % "3.1.0-M2"  % Test,
        "com.vladsch.flexmark"      % "flexmark-all"        % "0.35.10"   % Test
      )
  )
