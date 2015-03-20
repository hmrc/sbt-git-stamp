package uk.gov.hmrc

import org.scalatest.{Matchers, WordSpec}


class GitStampPluginSpec extends WordSpec with Matchers{

  "Git repo information" in {
    val info = GitStampPlugin.repoInfo.toString()
    info.contains("Build-Date") shouldBe true
    info.contains("Git-Branch") shouldBe true
    info.contains("Git-Repo-Is-Clean") shouldBe true
    info.contains("Git-Head-Rev") shouldBe true
    info.contains("Git-Commit-Author") shouldBe true
    info.contains("Git-Commit-Date") shouldBe true
    info.contains("Git-Describe") shouldBe true
  }

}
