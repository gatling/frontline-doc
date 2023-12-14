---
title: "Gatling Enterprise 1.19 Highlights"
description: "Learn about the main new features of Gatling Enterprise 1.19"
lead: "Gatling Enterprise 1.19 introduces Gatling 3.10 and Java 21 support and requires running at least on Java 11"
date: 2023-12-15T11:00:00+02:00
lastmod: 2023-15-19T11:00:00+02:00
weight: 6012
---

## Gatling Enterprise 1.19

### Gatling 3.10 Support

Gatling 3.10 is the latest generation of Gatling. Just like every new minor release, it's not binary compatible with previous releases, so code already compiled must be recompiled in order to upgrade.

### Gatling 3.3 and 3.4 Dropped

Gatling 3.3 and 3.4 are no longer supported. Simulations built on top of these old versions must be upgraded.

### Java 11 Baseline

Gatling 3.10 and Gatling Enterprise 1.19 now require at least Java 11 to run. In particular, Java 8 is no longer supported.


### Java 21 Support

Gatling Enterprise now supports running on Java 21.
Docker, AWS MarketPlace and Azure MarketPlace images have been upgraded to run with Java 21.
This lets users easily compile Gatling code using modern Java APIs.
