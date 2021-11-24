---
title: "Gatling Enterprise 1.16 Highlights"
description: "Learn about the main new features of Gatling Enterprise 1.16"
lead: "Gatling Enterprise 1.16 introduces a redesign of the application and a new public API"
date: 2021-11-24T14:00:00+02:00
lastmod: 2021-11-24T14:00:00+02:00
weight: 6015
---

## Gatling Enterprise 1.16

### Gatling 3.7 Support

Gatling 3.7 is the latest generation of Gatling.

It introduces many features, including a Java DSL that can also be used with other languages such as Kotlin.

### Java 17 Certified Images

Java 17 is the latest Java LTS (Long Term Support) version.

We now provide Java 17 based certified images for AMI, Azure, GCE and Docker.

### ARM Support

We now support deploying Gatling Enterprise components including injectors on ARM based servers.

On AWS, we also provide an ARM based certified image **for Java 17 only**, that's automatically picked based on the family of the instance type you've configured in your pool, eg `c6g.xlarge`.

### Switch to OSS Build Plugins

{{< alert warning >}}
We've moved all the Enterprise-specific features into the standard open-source maven, gradle and sbt plugins.

As a result:
* the configuration is now the same when you're using Gatling the open-source way or the Enterprise one, you no longer need 2 different plugins
* we recommend that you upgrade to the latest version of those plugins:
    * [maven](https://gatling.io/docs/gatling/reference/current/extensions/maven_plugin/): `io.gatling:gatling-maven-plugin:4.0.1`. Beware that this new version of the maven plugin no longer compiles Scala code itself, so you must configure the standard `scala-maven-plugin`.
    * [gradle](https://gatling.io/docs/gatling/reference/current/extensions/gradle_plugin/): `io.gatling.gradle:3.7.1`
    * [sbt](https://gatling.io/docs/gatling/reference/current/extensions/sbt_plugin/): `io.gatling:gatling-sbt:4.0.0`
* after upgrading your Gatling projects, you will need to change the
[build configuration]({{< ref "../../user/simulations/#step-2-build-configuration" >}})
for your existing simulations, to use the non-deprecated plugins. For example, replace "Maven project (deprecated plugin)"
with "Maven project".
* the Enterprise-specific plugins are deprecated and will no longer be maintained

{{< /alert >}}
