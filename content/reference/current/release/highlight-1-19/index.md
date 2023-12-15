---
title: "Gatling Enterprise 1.19 Highlights"
description: "Learn about the main new features of Gatling Enterprise 1.19"
lead: "Gatling Enterprise 1.19 introduces Gatling 3.10 and Java 21 support and requires running at least on Java 11"
date: 2023-12-15T11:00:00+02:00
lastmod: 2023-15-19T11:00:00+02:00
weight: 6012
---

## Gatling Enterprise 1.19

### Java 11 Baseline

{{< alert warning >}}
Starting from this release, Gatling Enterprise requires at least Java 11.

If you're still running the Gatling Enterprise server or your Load Generators with an older version such as Java 8,
you must first upgrade these installations prior to upgrading to Gatling Enterprise 1.19.0.
{{< /alert >}}

### Gatling 3.3 and 3.4 Support Planned for Removal

{{< alert warning >}}
Support for Gatling 3.3 and 3.4 is planned for removal in Gatling Enterprise 1.20.0.

We recommend that you upgrade your tests to Gatling 3.10.0, older versions are no longer maintained.
{{< /alert >}}

### Gatling 3.10 Support

Gatling 3.10 is the latest generation of Gatling.
Just like every new minor release, it's not binary compatible with previous releases, so code already compiled must be recompiled in order to upgrade.

### Java 21 Support

Gatling Enterprise now supports running on Java 21.
Docker, AWS MarketPlace and Azure MarketPlace images have been upgraded to run with Java 21.
This lets users easily use modern Java APIs in their Gatling tests.
