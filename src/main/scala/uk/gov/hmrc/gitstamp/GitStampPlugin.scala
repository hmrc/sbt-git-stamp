/*
 * Copyright 2019 HM Revenue & Customs
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

import sbt.Keys._
import sbt.Package.ManifestAttributes
import sbt._

object GitStampPlugin extends AutoPlugin {

  val gitStampSettings: Seq[Def.Setting[Task[scala.Seq[PackageOption]]]] =
    Seq(packageOptions += ManifestAttributes(GitStamp.gitStamp.toSeq: _*))

}