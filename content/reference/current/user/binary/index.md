---
title: "Gatling Binary generation"
description: "Learn how to package Gatling simulations"
date: 2021-03-26T18:06:39+01:00
lastmod: 2021-08-16T17:55:36+02:00
weight: 10050
toc: true
---

## Gatling Enterprise Gatling Versions

Gatling Enterprise actually uses custom versions of the Gatling components. Those binaries are not open source and their usage is restricted to Gatling Enterprise.

When you'll be deploying tests with Gatling Enterprise, it will replace your Gatling OSS dependencies with their custom counterparts.

## Configuring Gatling Projects

### Maven

Please check the Gatling documentation about [how to configure a maven project](https://gatling.io/docs/gatling/reference/current/extensions/maven_plugin/). 

You can run `mvn gatling:enterprisePackage -DskipTests` in your terminal and check you get a jar containing all the classes and extra of your project.

### Gradle

Please check the Gatling documentation about [how to configure a gradle project](https://gatling.io/docs/gatling/reference/current/extensions/gradle_plugin/).

You can run `gradle gatlingEnterprisePackage` in your terminal and check you get a jar containing all the classes and extra dependencies of your project.

### SBT

Please check the Gatling documentation about [how to configure a sbt project](https://gatling.io/docs/gatling/reference/current/extensions/sbt_plugin/).

You can run `sbt -J-Xss100M Gatling/enterprisePackage` in your terminal and check you get a jar containing all the classes and extra dependencies of your project.

{{< alert warning >}}
We require sbt 1.1+.
{{< /alert >}}

{{< alert warning >}}
If you use the 'GatlingIt' config, you have to use a custom build command as the default one is for the 'test' config:
``sbt -J-Xss100M ;clean;GatlingIt/enterprisePackage -batch --error``
{{< /alert >}}

### Multi-Module Support

If your project is a multi-module one, make sure that only the one containing the Gatling Simulations gets configured with the Gatling related plugins describes above.

Gatling Enterprise will take care of deploying all available jars, so you can have Gatling module depend on the other ones.

## Note on Feeders

A typical mistake with Gatling and Gatling Enterprise is to rely on having an exploded maven/gradle/sbt project structure and try loading files from the project filesystem.

This filesystem structure will be gone once Gatling Enterprise will have compiled your project and uploaded your binaries on the injectors.

If your feeder files are packaged with your test sources, you must resolve them from the classpath.
This way will always work, both locally and with Gatling Enterprise.

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
`shard` is available in Gatling OSS DSL but is a noop there. It's only effective when running tests with Gatling Enterprise.
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
This System property is only defined when deploying with Gatling Enterprise.
It's not defined when running locally with any Gatling OSS launcher.
{{< /alert >}}

## Publishing Gatling Enterprise Packages into Binary Repositories

Instead of building tests from sources, you have the option of building binaries upstream and publishing them into a binary repository (JFrog Artifactory, Sonatype Nexus or AWS S3) so Gatling Enterprise just has to download them.

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

The package artifact will be automatically attached to your project and deployed with the `shaded` classifier.

```shell
mvn deploy
```

### Gradle

The main idea is to use the official maven publish plugin and ask it to use the task named `gatlingEnterprisePackage`, then define a repository:

```groovy
apply plugin: "maven-publish"

publishing {
  publications {
    mavenJava(MavenPublication) {
      artifact gatlingEnterprisePackage
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

```shell
gradle publish
```

An artifact will be published will the `tests` classifier.

### SBT

```scala
packageBin in Test := (assembly in Test).value
publishArtifact in Gatling := true
publishTo :=
	(if (isSnapshot.value)
		Some("private repo" at "REPLACE_WITH_YOUR_SNAPSHOTS_REPOSITORY_URL")
	else
		Some("private repo" at "REPLACE_WITH_YOUR_RELEASES_REPOSITORY_URL")
)
```

```shell
sbt test:publish
```

An artifact will be published will the `tests` classifier.
