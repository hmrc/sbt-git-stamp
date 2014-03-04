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
package uk.gov.hmrc

import sbt._
import Keys._

import com.github.nscala_time.time.Imports._
import org.joda.time.format.ISODateTimeFormat
import org.eclipse.jgit.storage.file.FileRepositoryBuilder
import org.eclipse.jgit.lib._
import org.eclipse.jgit.api.Git

object GitStampPlugin extends Plugin {

  def repoInfo: List[(String, String)] = {
    val builder = new FileRepositoryBuilder
    val repository = builder.readEnvironment.findGitDir.build
    val head = repository.getRef(Constants.HEAD)
    val git = new Git(repository)
    val status = git.status.call
    val isClean = status.isClean
    val branch = repository.getBranch

    List("Git-Branch" -> branch,
          "Git-Repo-Is-Clean" -> isClean.toString, 
          "Git-Head-Rev" -> ObjectId.toString(head.getObjectId),
          "Git-Build-Date" ->  ISODateTimeFormat.dateTime.print(DateTime.now)
    )
  }

  val gitStampSettings =
    Seq(packageOptions <+= (packageOptions in Compile, packageOptions in packageBin) map {(a, b) =>
      Package.ManifestAttributes(repoInfo: _*)})
}