/*
 * Copyright 2014 HM Revenue & Customs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
import sbt.Keys._
import sbt._
import uk.gov.hmrc.DefaultBuildSettings._
import uk.gov.hmrc.versioning.SbtGitVersioning
import uk.gov.hmrc.SbtAutoBuildPlugin

object PluginBuild extends Build {

  val pluginName = "sbt-git-stamp"

  lazy val root = Project(pluginName, base = file("."), settings = Seq(
      targetJvm := "jvm-1.7",
      sbtPlugin := true,
      organization := "uk.gov.hmrc",
      name := pluginName,
      scalaVersion := "2.10.4",
      resolvers ++= Seq(Resolver.bintrayRepo("hmrc", "releases")),
      libraryDependencies ++= Seq(
        "uk.gov.hmrc" %% "git-stamp" % "0.2.0",
        "org.pegdown" % "pegdown" % "1.4.2" % "test"
      ),
      publishArtifact := true,
      publishArtifact in Test := false,
      sources in doc in Compile := List(),
      sources in doc in Test := List(),
      BuildDescriptionSettings()
    )
  ).enablePlugins(SbtAutoBuildPlugin, SbtGitVersioning)

}


object BuildDescriptionSettings {

  def apply() = pomExtra := (<url>https://www.gov.uk/government/organisations/hm-revenue-customs</url>
        <licenses>
          <license>
            <name>Apache 2</name>
            <url>http://www.apache.org/licenses/LICENSE-2.0.txt</url>
          </license>
        </licenses>
        <scm>
          <connection>scm:git@github.com:hmrc/sbt-git-stamp.git</connection>
          <developerConnection>scm:git@github.com:hmrc/sbt-git-stamp.git</developerConnection>
          <url>git@github.com:hmrc/sbt-git-stamp.git</url>
        </scm>
        <developers>
          <developer>
            <id>duncancrawford</id>
            <name>Duncan Crawford</name>
            <url>http://www.equalexperts.com</url>
          </developer>
        </developers>)
}
