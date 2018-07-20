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
    scalaVersion                     := "2.10.5",
    libraryDependencies              ++=
      Seq(
        "com.github.nscala-time" %% "nscala-time"     % "2.2.0",
        "org.eclipse.jgit"       % "org.eclipse.jgit" % "3.6.1.201501031845-r",
        "org.scalatest"          %% "scalatest"       % "2.2.4"                 % Test,
        "org.pegdown"            % "pegdown"          % "1.5.0"                 % Test
      )
  )
