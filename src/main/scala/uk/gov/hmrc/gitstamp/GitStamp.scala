/*
 * Copyright 2016 HM Revenue & Customs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package uk.gov.hmrc.gitstamp

import com.github.nscala_time.time.Imports._
import org.eclipse.jgit.api.Git
import org.eclipse.jgit.lib.Constants._
import org.eclipse.jgit.lib.{ObjectId, Repository}
import org.eclipse.jgit.revwalk.RevCommit
import org.eclipse.jgit.storage.file.FileRepositoryBuilder
import org.joda.time.format.ISODateTimeFormat._
import scala.collection.JavaConversions._

object GitStamp{

  def gitStamp: Map[String, String] = gitStamp(new FileRepositoryBuilder().readEnvironment.findGitDir.build)

  def gitStamp(repository: Repository): Map[String, String] = {
    val git = new Git(repository)
    val headId = repository.getRef(HEAD).getObjectId
    val headIdStr = ObjectId.toString(headId)
    val describe = Option(git.describe().call()).getOrElse(headIdStr)
    val headRev = headCommit(git, headId)

    Map ("Build-Date" -> dateTime.print(DateTime.now),
      "Git-Branch" -> repository.getBranch,
      "Git-Repo-Is-Clean" -> repoIsClean(git),
      "Git-Head-Rev" -> headIdStr,
      "Git-Commit-Author" -> commitAuthorName(headRev),
      "Git-Commit-Date" -> commitDateTime(headRev),
      "Git-Describe" -> describe)
  }

  private def repoIsClean(git: Git): String = {
    git.status.call.isClean.toString
  }

  private def commitDateTime(headRev: Option[RevCommit]): String = {
    dateTime.print(headRev.map(_.getCommitTime.toLong * 1000).getOrElse(0L))
  }

  private def commitAuthorName(headRev: Option[RevCommit]): String = {
    headRev.map(_.getCommitterIdent.getName).getOrElse("")
  }

  private def headCommit(git: Git, headId: ObjectId): Option[RevCommit] = {
    git.log().add(headId).setMaxCount(1).call().toSeq.headOption
  }

  private def committerName: (RevCommit) => String = _.getCommitterIdent.getName
}
