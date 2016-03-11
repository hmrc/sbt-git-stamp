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

import org.scalatest.{Matchers, WordSpec}

class GitStampSpec extends WordSpec with Matchers{

  "Git repo information" in {
    val info = GitStamp.gitStamp.toString
    info.contains("Build-Date") shouldBe true
    info.contains("Git-Branch") shouldBe true
    info.contains("Git-Repo-Is-Clean") shouldBe true
    info.contains("Git-Head-Rev") shouldBe true
    info.contains("Git-Commit-Author") shouldBe true
    info.contains("Git-Commit-Date") shouldBe true
    info.contains("Git-Describe") shouldBe true
  }
}
