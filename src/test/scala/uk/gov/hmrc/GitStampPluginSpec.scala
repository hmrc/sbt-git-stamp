package uk.gov.hmrc

import org.scalatest.{Matchers, WordSpec}


class GitStampPluginSpec extends WordSpec with Matchers{

  "Git repo information" in {
    val info = GitStampPlugin.repoInfo
    info.size shouldBe 4
  }

}
