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

  lazy val root = Project(pluginName, base = file("."))
    .enablePlugins(SbtAutoBuildPlugin, SbtGitVersioning)
    .settings(
      targetJvm := "jvm-1.7",
      sbtPlugin := true,
      organization := "uk.gov.hmrc",
      scalaVersion := "2.10.5",
      libraryDependencies ++= Seq(
        "com.github.nscala-time" %% "nscala-time" % "2.2.0",
        "org.eclipse.jgit" % "org.eclipse.jgit" % "3.6.1.201501031845-r",
        "org.scalatest" %% "scalatest" % "2.2.4" % "test",
        "org.pegdown" % "pegdown" % "1.5.0" % "test"
      )
    )
}
