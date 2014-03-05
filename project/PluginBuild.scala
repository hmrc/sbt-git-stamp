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
import sbt._
import Keys._

object PluginBuild extends Build {

  lazy val pluginName = "sbt-git-stamp"
  lazy val pluginVersion = "2.0.0"

  lazy val root = Project(pluginName, base = file("."), settings = Project.defaultSettings ++ Seq(
    version := pluginVersion,
    sbtPlugin := true,
    organization := "uk.gov.hmrc",
    name := pluginName,
    scalaVersion := "2.10.3",
    resolvers ++= Seq(
      Resolver.mavenLocal,
      "jgit-repository" at "https://repo.eclipse.org/content/groups/releases/",
      Opts.resolver.sonatypeReleases,
      Opts.resolver.sonatypeSnapshots
    ),
    libraryDependencies ++= Seq(
      "com.github.nscala-time" %% "nscala-time" % "0.8.0",
      "org.eclipse.jgit" % "org.eclipse.jgit" % "3.3.0.201403021825-r",
      "org.scalatest" %% "scalatest" % "2.1.0" % "test"
    ),
    publishArtifact := true,
    publishArtifact in Test := false
  ) ++ SonatypeBuild()
  )

}


object SonatypeBuild {

  import xerial.sbt.Sonatype._

  def apply() = {
    sonatypeSettings ++ Seq(
      pomExtra := (<url>https://www.gov.uk/government/organisations/hm-revenue-customs</url>
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
    )
  }

}