sbt-git-stamp
=============

[![Build Status](https://travis-ci.org/hmrc/sbt-git-stamp.svg)](https://travis-ci.org/hmrc/sbt-git-stamp) [ ![Download](https://api.bintray.com/packages/hmrc/sbt-plugin-releases/sbt-git-stamp/images/download.svg) ](https://bintray.com/hmrc/sbt-plugin-releases/sbt-git-stamp/_latestVersion)

An SBT plugin that add some basic git data to the artefact's `MANIFEST.MF` file.

We cannot take the credit for the idea of this plugin as the source is a fork from Patrick Kaeding's [creation](https://bitbucket.org/pkaeding/sbt-git-stamp)

## What it does ##

This plugin will include some basic information about the state of the repository that the artifact was built from,
at the time it was built.  This information includes:

* Head revision
* Branch name
* Whether or not there were uncommitted changes
* Build date

This information is included in the `MANIFEST.MF` file in the jar produced by the `package` (or `assembly`) tasks.  This can
help you track down where a build came from.

## How to use it ##

Add the following to your `project/plugins.sbt`:

```scala
resolvers += Resolver.url("hmrc-sbt-plugin-releases",
  url("https://dl.bintray.com/hmrc/sbt-plugin-releases"))(Resolver.ivyStylePatterns)
  
addSbtPlugin("uk.gov.hmrc" % "sbt-git-stamp" % "INSERT_VERSION")
```    

Add the following near the top of your `build.sbt` file (if you are using a full build config, I'm sure you can figure
it out):

    import uk.gov.hmrc.gitstamp.GitStampPlugin._

Then, add this lower down:

    Seq( gitStampSettings: _* )

Then, just build as normal. This plugin won't add any tasks, or otherwise change the way you interact with SBT.

Your artifacts will just come out with a `MANIFEST.MF` file that looks something like this:

    Manifest-Version: 1.0
    Implementation-Vendor: My-Company
    Implementation-Title: My Project
    Implementation-Version: 0.1
    Implementation-Vendor-Id: My-Company
    Specification-Vendor: My-Company
    Git-Repo-Is-Clean: false
    Git-Branch: gitstamp
    Specification-Title: My Company
    Git-Build-Date: 2013-04-26T17:22:58.538-07:00
    Specification-Version: 0.1
    Git-Head-Rev: b0d5a67d59dc7c0133aecce2e2ceb18fc8d23597
    Git-Describe: release/0.3.5-b0d5a67

The entries starting with `Git-` were added by this plugin.

## License ##
 
This code is open source software licensed under the [Apache 2.0 License]("http://www.apache.org/licenses/LICENSE-2.0.html").
