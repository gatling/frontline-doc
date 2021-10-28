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

In your `pom.xml`, you have to:

* pull Gatling dependencies
* add the maven plugin for Scala, so your code gets compiled
* add the maven plugin for Gatling Enterprise, so it can package your code into a deployable artifact

```xml
<project>
  <dependencies>
    <dependency>
      <groupId>io.gatling.highcharts</groupId>
      <artifactId>gatling-charts-highcharts</artifactId>
      <version>{{< var gatlingVersion >}}</version>
      <scope>test</scope>
    </dependency>
  </dependencies>

  <build>
    <!-- so maven compiles src/test/scala and not only src/test/java -->
    <testSourceDirectory>src/test/scala</testSourceDirectory>
    <plugins>
      <plugin>
        <artifactId>maven-jar-plugin</artifactId>
        <version>{{< var mavenJarPluginVersion >}}</version>
      </plugin>
      <!-- so maven can compile your scala code -->
      <plugin>
        <groupId>net.alchim31.maven</groupId>
        <artifactId>scala-maven-plugin</artifactId>
        <version>{{< var scalaMavenPluginVersion >}}</version>
        <executions>
          <execution>
            <goals>
              <goal>testCompile</goal>
            </goals>
            <configuration>
              <recompileMode>all</recompileMode>
              <jvmArgs>
                <jvmArg>-Xss100M</jvmArg>
              </jvmArgs>
              <args>
                <arg>-target:jvm-1.8</arg>
                <arg>-deprecation</arg>
                <arg>-feature</arg>
                <arg>-unchecked</arg>
                <arg>-language:implicitConversions</arg>
                <arg>-language:postfixOps</arg>
              </args>
            </configuration>
          </execution>
        </executions>
      </plugin>

      <!-- so maven can build a package for Gatling Enterprise -->
      <plugin>
        <groupId>io.gatling.frontline</groupId>
        <artifactId>frontline-maven-plugin</artifactId>
        <version>{{< var frontLineMavenPluginVersion >}}</version>
        <executions>
          <execution>
            <goals>
              <goal>package</goal>
            </goals>
          </execution>
        </executions>
      </plugin>
    </plugins>
  </build>
</project>
```

You can run `mvn package -DskipTests` in your terminal and check you get a jar containing all the dependencies of the simulation.

You can also exclude dependencies you don't want to ship, eg:

```xml
<plugin>
  <groupId>io.gatling.frontline</groupId>
  <artifactId>frontline-maven-plugin</artifactId>
  <version>{{< var frontLineMavenPluginVersion >}}</version>
  <executions>
    <execution>
      <goals>
        <goal>package</goal>
      </goals>
      <configuration>
        <excludes>
          <exclude>
            <groupId>org.scalatest</groupId>
            <artifactId>scalatest_{{< var scalaMajorVersion >}}</artifactId>
          </exclude>
        </excludes>
      </configuration>
    </execution>
  </executions>
</plugin>
```

### SBT

In a sbt project, you have to:

* pull Gatling dependencies
* add the sbt plugin for Gatling Enterprise, so it can package your code into a deployable artifact

A `build.sbt` file should look like this:

```sbt
enablePlugins(GatlingPlugin, FrontLinePlugin)

// If you want to package simulations from the 'it' scope instead
// inConfig(IntegrationTest)(_root_.io.gatling.frontline.sbt.FrontLinePlugin.frontlineSettings(IntegrationTest))

scalaVersion := "{{< var scalaVersion >}}"
scalacOptions := Seq("-encoding", "UTF-8", "-target:jvm-1.8", "-deprecation", "-feature", "-unchecked", "-language:implicitConversions", "-language:postfixOps")

val gatlingVersion = "{{< var gatlingVersion >}}"

libraryDependencies += "io.gatling.highcharts" % "gatling-charts-highcharts" % gatlingVersion % "test"
// only required if you intend to use the gatling-sbt plugin
libraryDependencies += "io.gatling" % "gatling-test-framework" % gatlingVersion % "test"
```

{{< alert warning >}}
Don’t forget to replace the UUID with the one you were given.
{{< /alert >}}

{{< alert warning >}}
We only support sbt 1+, not sbt 0.13.
{{< /alert >}}

{{< alert warning >}}
If you use the 'it' config, you have to use a custom build command as the default one is for the 'test' config:
``sbt -J-Xss100M ;clean;it:assembly -batch --error``
{{< /alert >}}

{{< alert tip >}}
You only need the `gatling-test-framework`dependency if you intend to run locally and use the gatling-sbt plugin.
{{< /alert >}}

{{< alert tip >}}
If you use very long method calls chains in your Gatling code, you might have to increase sbt's thread stack size:
{{< /alert >}}

```bash
export SBT_OPTS="-Xss100M"
```

You will also need the following lines in the `project/plugins.sbt` file:

```sbt
// only if you intend to use the gatling-sbt plugin for running Gatling locally
addSbtPlugin("io.gatling" % "gatling-sbt" % "{{< var gatlingSbtPluginVersion >}}")
// so sbt can build a package for Gatling Enterprise
addSbtPlugin("io.gatling.frontline" % "sbt-frontline" % "{{< var frontLineSbtPluginVersion >}}")
```

You can run `sbt test:assembly` (or `sbt it:assembly` if you've configured the plugin for integration tests) in your terminal and check you get a jar containing all the dependencies of the simulation.

{{< alert tip >}}
The `gatling-sbt` is optional.
{{< /alert >}}

### Gradle

In a Gradle project, you have to:

* pull Gatling dependencies
* add the gradle plugin for Gatling Enterprise, so it can package your code into a deployable artifact

A `build.gradle` file should look like this:

```groovy
plugins {
  // The following line allows to load io.gatling.gradle plugin and directly apply it
  id 'io.gatling.frontline.gradle' version '{{< var frontLineGradlePluginVersion >}}'
}

// This is needed to let io.gatling.gradle plugin to loads gatling as a dependency
repositories {
  jcenter()
  mavenCentral()
}

gatling {
  toolVersion = '{{< var gatlingVersion >}}'
}
```

{{< alert warning >}}
Don’t forget to replace the UUID with the one you were given.
{{< /alert >}}

You can run `gradle frontLineJar` in your terminal and check you get a jar containing all the dependencies of the simulation.

### Multi-Module Support

If your project is a multi-module one, make sure that only the one containing the Gatling Simulations gets configured with the Gatling related plugins describes above.

Gatling Enterprise will take care of deploying all available jars so you can have Gatling module depend on the other ones.

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

## Publishing Fatjars into Binary Repositories

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

You'll need `frontline-maven-plugin` version 1.0.3 at least.
Fatjar artifact will be automatically attached to your project and deployed with the `shaded` classifier.

```shell
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

```shell
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

```shell
sbt test:publish
```

An artifact will be published will the `tests` classifier.
