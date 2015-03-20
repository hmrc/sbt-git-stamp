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

import com.github.nscala_time.time.Imports._
import org.eclipse.jgit.api.Git
import org.eclipse.jgit.lib._
import org.eclipse.jgit.revwalk.RevCommit
import org.eclipse.jgit.storage.file.FileRepositoryBuilder
import org.joda.time.format.ISODateTimeFormat
import sbt.Keys._
import sbt._

import scala.collection.JavaConversions._

object GitStampPlugin extends Plugin {

  def repoInfo: List[(String, String)] = {
    val builder = new FileRepositoryBuilder
    val repository = builder.readEnvironment.findGitDir.build
    val git = new Git(repository)
    val head = repository.getRef(Constants.HEAD)
    val headId = head.getObjectId
    val headIdStr = ObjectId.toString(headId)
    val status = git.status.call
    val isClean = status.isClean
    val branch = repository.getBranch
    val describe = Option(git.describe().call()).getOrElse(headIdStr)
    val headRev = headCommit(git, headId)

    List("Build-Date" -> ISODateTimeFormat.dateTime.print(DateTime.now),
         "Git-Branch" -> branch,
         "Git-Repo-Is-Clean" -> isClean.toString,
         "Git-Head-Rev" -> headIdStr,
         "Git-Commit-Author" -> commitAuthorName(headRev),
         "Git-Commit-Date" -> commitDateTime(headRev),
         "Git-Describe" -> describe
    )
  }

  private def commitDateTime(headRev: Option[RevCommit]): String = {
    ISODateTimeFormat.dateTime.print(headRev.map(_.getCommitTime.toLong * 1000).getOrElse(0L))
  }

  private def commitAuthorName(headRev: Option[RevCommit]): String = {
    headRev.map(_.getCommitterIdent.getName).getOrElse("")
  }

  private def headCommit(git: Git, headId: ObjectId): Option[RevCommit] = {
    git.log().add(headId).setMaxCount(1).call().toSeq.headOption
  }

  private def committerName: (RevCommit) => String = _.getCommitterIdent.getName

  val gitStampSettings =
    Seq(packageOptions <+= (packageOptions in Compile, packageOptions in packageBin) map {(a, b) =>
      Package.ManifestAttributes(repoInfo: _*)})
}