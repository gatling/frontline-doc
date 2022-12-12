---
title: "Gatling Enterprise 1.17 Highlights"
description: "Learn about the main new features of Gatling Enterprise 1.17"
lead: "Gatling Enterprise 1.17 introduces Gatling 3.8 support and a redesign of the PDF export feature"
date: 2022-07-05T11:00:00+02:00
lastmod: 2022-07-05T11:00:00+02:00
weight: 6014
---

## Gatling Enterprise 1.18

### Gatling 3.9 Support

Gatling 3.9 is the latest generation of Gatling. Just like every new minor release, it's not binary compatible with previous releases, so code already compiled must be recompiled in order to upgrade. 

### Java 17 Upgrade

Gatling Enterprise now supports running on Java 17.
Docker, AWS MarketPlace and Azure MarketPlace images have been upgraded to run with Java 17.
This lets users easily compile Gatling code using modern Java APIs.

### Kubernetes Support Revamping

First, we've dropped the legacy NodePort connection mode that was both considered unsecured and against Kubernetes principles.

{{< alert info >}}
If you're still using pools with the "Node Port" mode, you must first move to another one prior to upgrading.
{{< /alert >}}

Then, we've revamped the way the Gatling Enterprise controller connects to Load Generators.
When they sit in the same cluster, we now always create a Service. In this case, the Ingress and Route modes are not available.

Finally, we now support setting tolerations, annotations and container env vars.
