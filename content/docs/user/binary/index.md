---
title: "Gatling Binary generation"
description: "Learn how to package Gatling simulations"
date: 2021-03-26T18:06:39+01:00
lastmod: 2021-03-26T18:06:39+01:00
draft: false
images: []
menu:
  docs:
    parent: "user"
weight: 10050
toc: true
---

## FrontLine Gatling Versions

FrontLine actually uses custom versions of the Gatling components. Those binaries are not open sources and their usage is restricted to FrontLine.

When you'll be deploying tests with FrontLine, it will replace your Gatling OSS dependencies with their custom counterparts.

## Configuring Gatling Projects

### Maven

In your `pom.xml`, you have to add in:

* pull Gatling dependencies
* add the maven plugin for Scala, so your code gets compiled
* add the maven plugin for FrontLine, so it can package your code into a deployable artifact

```xml
{{< include-static "pom.xml" gatlingVersion mavenJarPluginVersion scalaMavenPluginVersion frontLineMavenPluginVersion >}}
```

You can run `mvn package -DskipTests` in your terminal and check you get a jar containing all the dependencies of the simulation.

You can also exclude dependencies you don't want to ship, eg:

```xml
{{< include-static "pom-excludes.xml" frontLineMavenPluginVersion scalaMajorVersion  >}}
```

### SBT

In a sbt project, you have to:

* pull Gatling dependencies
* add the sbt plugin for FrontLine, so it can package your code into a deployable artifact

A `build.sbt` file should look like this:

```sbt
{{< include-static "build.sbt" scalaVersion gatlingVersion  >}}
```

{{< alert warning >}}
Don’t forget to replace the UUID with the one you were given.
{{< /alert >}}

{{< alert warning >}}
We only support sbt 1+, not sbt 0.13.
{{< /alert >}}

{{< alert warning >}}
If you use the 'it' config, you have to use a custom build command as the defauit one is for the 'test' config:
``sbt -J-Xss100M ;clean;it:assembly -batch --error``
{{< /alert >}}

{{< alert warning >}}
We recommend disabling Coursier for now. There are several bugs in the sbt/Coursier integration that makes our plugin work in a suboptimal fashion.
{{< /alert >}}

{{< alert tip >}}
The `gatling-test-framework`dependencies is only needed if you intend to run locally and use the gatling-sbt plugin.
{{< /alert >}}

{{< alert tip >}}
If you use very long method calls chains in your Gatling code, you might have to increase sbt's thread stack size:
{{< /alert >}}

```
$ export SBT_OPTS="-Xss100M"
```

You will also need the following lines in the `project/plugins.sbt` file:

```sbt
// only if you intend to use the gatling-sbt plugin for running Gatling locally
addSbtPlugin("io.gatling" % "gatling-sbt" % "{{< var gatlingSbtPluginVersion >}}")
// so sbt can build a package for FrontLine
addSbtPlugin("io.gatling.frontline" % "sbt-frontline" % "{{< var frontLineSbtPluginVersion >}}")
```

You can run `sbt test:assembly` (or `sbt it:assembly` if you've configured the plugin for integration tests) in your terminal and check you get a jar containing all the dependencies of the simulation.

{{< alert tip >}}
The `gatling-sbt` is optional.
{{< /alert >}}

### Gradle

In a Gradle project, you have to:

* pull Gatling dependencies
* add the gradle plugin for FrontLine, so it can package your code into a deployable artifact

A `build.gradle` file should look like this:

```text
{{< include-static "build.gradle" frontLineGradlePluginVersion gatlingVersion  >}}
```

{{< alert warning >}}
Don’t forget to replace the UUID with the one you were given.
{{< /alert >}}

You can run `gradle frontLineJar` in your terminal and check you get a jar containing all the dependencies of the simulation.

### Multi-Module Support

If your project is a multi-module one, make sure that only the one containing the Gatling Simulations gets configured with the Gatling related plugins describes above.

FrontLine will take care of deploying all available jars so you can have Gatling module depend on the other ones.

## Note on Feeders

A typical mistake with Gatling and FrontLine is to rely on having an exploded maven/gradle/sbt project structure and try loading files from the project filesystem.

This filesystem structure will be gone once FrontLine will have compiled your project and uploaded your binaries on the injectors.

If your feeder files are packaged with your test sources, you must resolve them from the classpath.
This way will always work, both locally and with FrontLine.

```scala
// incorrect
val feeder = csv("src/test/resources/foo.csv")

// correct
val feeder = csv("foo.csv")
```

## Specific Gatling Features

### Load Sharding

Injection rates and throttling rates are automatically distributed amongst nodes.

However, Feeders data is not automatically sharded, as it might not be the desired behavior.

If you want data to be unique cluster-wide, you have to explicitly tell Gatling to shard the data, e.g.:

```scala
val feeder = csv("foo.csv").shard
```

Assuming a CSV file contains 1000 entries, and 3 Gatling nodes, the entries will be distributed the following way:

- First node will access the first 333 entries
- Second node will access the next 333 entries
- Third node will access the last 334 entries

{{< alert tip >}}
`shard` is available in Gatling OSS DSL but is a noop there. It's only effective when running tests with FrontLine.
{{< /alert >}}

## Resolving Injector Location in Simulation

When running a distributed test from multiple locations, you could be interested in knowing where a given injector is deployed in order to trigger specific behaviors depending on location.

For example, you might want to hit `https://mydomain.co.uk` `baseUrl` if injector is deployed on AWS London, and `https://mydomain.com` otherwise.

You can resolve in your simulation code the name of the pool a given injector is deployed on:

```scala
val poolName = System.getProperty("gatling.frontline.poolName")
val baseUrl = if (poolName == "London") "https://domain.co.uk" else "https://domain.com"
```

{{< alert tip >}}
This System property is only defined when deploying with FrontLine.
It's not defined when running locally with any Gatling OSS launcher.
{{< /alert >}}

## Publishing Fatjars into Binary Repositories

Instead of building tests from sources, you have the option of building binaries upstream and publishing them into a binary repository (JFrog Artifactory, Sonatype Nexus or AWS S3) so FrontLine just has to download them.

{{< alert tip >}}
Please check your build tool documentation and the standards in your organization about the way to set credentials.
{{< /alert >}}

### Maven

You'll have to configure either `repository` or `snapshotRepository` block whether you want to deploy releases or snapshots.

```xml
<distributionManagement>
  <repository>
    <id>your.releases.repository.id</id>
    <url>REPLACE_WITH_YOUR_RELEASES_REPOSITORY_URL</url>
  </repository>
  <snapshotRepository>
    <id>your.snapshots.repository.id</id>
    <url>REPLACE_WITH_YOUR_SNAPSHOTS_REPOSITORY_URL</url>
  </snapshotRepository>
</distributionManagement>
```

You'll need `frontline-maven-plugin` version 1.0.3 at least.
Fatjar artifact will be automatically attached to your project and deployed with the `shaded` classifier.

```bash
mvn deploy
```

### Gradle

The main idea is to use the official maven publish plugin and ask it to use the task named `frontLineJar`, then define a repository:

```groovy
apply plugin: "maven-publish"

publishing {
  publications {
    mavenJava(MavenPublication) {
      artifact frontLineJar
    }
  }
  repositories {
    maven {
      if (project.version.endsWith("-SNAPSHOT")) {
        url "REPLACE_WITH_YOUR_SNAPSHOTS_REPOSITORY_URL"
      } else {
        url "REPLACE_WITH_YOUR_RELEASES_REPOSITORY_URL"
      }
    }
  }
}
```

You can deploy the test jar with the following command:

```bash
gradle publish
```

An artifact will be published will the `tests` classifier.

### Sbt

```scala
packageBin in Test := (assembly in Test).value
publishArtifact in Test := true
publishTo :=
	(if (isSnapshot.value)
		Some("private repo" at "REPLACE_WITH_YOUR_SNAPSHOTS_REPOSITORY_URL")
	else
		Some("private repo" at "REPLACE_WITH_YOUR_RELEASES_REPOSITORY_URL")
)
```

```bash
sbt test:publish
```

An artifact will be published will the `tests` classifier.
