---
title: "FrontLine 1.13 Highlights"
description: "Learn about the main new features of FrontLine 1.13"
lead: "FrontLine 1.13 introduce support for multiple versions of Gatling and performance fixes"
date: 2021-04-06T17:55:36+02:00
lastmod: 2021-04-06T17:55:36+02:00
draft: false
images: []
menu:
  docs:
    parent: "release"
weight: 60020
toc: true
---

### FrontLine 1.13

#### Gatling 3.3, 3.4 and 3.5 generations support

FrontLine 1.13 is compatible with the 3 latest Gatling generations.

{{< alert tip >}}
Only Gatling 3.5 will be actively maintained.

Multiple versions support is for convenience so that customers don't have to upgrade all their tests when upgrading their FrontLine platform.

Customers are recommended to upgrade to Gatling 3.5 as soon as they can.
{{< /alert >}}

#### Key New Features

We've changed the way duration events are aggregated.

Prior to FrontLine 1.13, those stats were aggregated by start timestamp, which could cause a lot of memory and CPU usage without any proven benefit.

Those stats are now aggregated by end timestamp, which results in a way more sound memory and CPU usage and paves the way for more long duration metrics that could be implemented in a future release, such as scenario duration.

We've also introduced hard limits on the number of metrics that a given test run can generate.
In some situations, a test could generate way too many metrics and cause the whole FrontLine instance to crash.

#### Key Bug Fixes

We've fixed some important bugs that could cause injectors and FrontLine server to not be in sync, causing long tests to abnormally crash.

Please check the Release Note for the full list of bug fixes.

### Gatling 3.5.0

{{< alert tip >}}
Gatling 3.5 becomes the production version, meaning that no further improvements and bug fixes will happen on Gatling 3.4.
{{< /alert >}}

#### Scala 2.13

Scala 2.13.0 was released in June 2019 and Scala 3 is expected soon, so it was critical that Gatling stopped lagging behind.

Gatling 3.5.0 is compiled against Scala 2.13, meaning that it's not binary compatible with the previous releases.

Please check the [migration guide](https://gatling.io/docs/current/migration_guides/3.3-to-3.4/) for more information about how to upgrade your tests.
The most noticeable impact is probably refreshing projects imported in your IDE.

Please also check [the full release note](https://github.com/gatling/gatling/milestone/94?closed=1) for more details about the content of this release.

#### Key New Features

* support for Scala case classes in Gatling Expression Language and `jsonStringify`
* ability to use [Pebble templates](https://pebbletemplates.io/) for multipart parts
* ability to provide regex patterns to `useAllLocalAddresses` to only use desired local addresses

#### Key Bug Fixes

* children scenarios not being triggered when parent doesn't generate any load. This can typically happen when using a parent scenario to initialize something with one single virtual user while running a cluster of injector so only one node get the single user.
* feeders configured with `batch` still loading the first batch, making them unsuitable for lazy loading while the file is actually populated at runtime from a parent scenario.

### Operations

{{< alert tip >}}
Images of Gatling FrontLine published to the AWS Marketplace are made using this installer. The directory layout will be the same.
{{< /alert >}}


{{< alert tip >}}
Prior to upgrading, make sure to perform the required backups as described at the beginning of the Installation Guide.
{{< /alert >}}

#### Old Binaries Distribution Platform Upcoming Shutdown

{{< alert tip >}}
Images of Gatling FrontLine published to the AWS Marketplace are made using this installer. The directory layout will be the same.
{{< /alert >}}

IMPORTANT: As announced when we released FrontLine 1.12.0, the `http://repository.gatling.io/` server was supposed to get decommissioned end of November 2020.
We extended the deadline to the end of December for give our customers more time to adapt, but the server will definitely be gone in January 2021.
From now on, please use `https://downloads.gatling.io/releases/YOUR_CUSTOMER_ID/`.
