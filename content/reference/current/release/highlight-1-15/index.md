---
title: "Gatling Enterprise 1.15 Highlights"
description: "Learn about the main new features of Gatling Enterprise 1.15"
lead: "Gatling Enterprise 1.15 introduces a redesign of the application and a new public API"
date: 2021-08-16T17:55:36+02:00
lastmod: 2021-08-16T17:55:36+02:00
weight: 6020
---

## Gatling Enterprise 1.15

### Renaming to Gatling Enterprise

FrontLine was renamed to Gatling Enterprise.

{{< alert info >}}
In order to not break anything, there is still some mentions of FrontLine, in the downloads links for example.
{{< /alert >}}

### Key New Features

* Redesign of the application with improved Reports and tables
* New Public API /license which returns the license information and limits
* New gradlew built-in for easier Gradle configuration

### Key Bug Fixes

* The used Elastic IPs were not shown in the injectors tab
* Improved the chart label color generation

## Operations

{{< alert tip >}}
Prior to upgrading, make sure to perform the required backups as described [here](https://gatling.io/docs/enterprise/self-hosted/reference/current/installation/manual/#upgrading-from-a-previous-version).
{{< /alert >}}
