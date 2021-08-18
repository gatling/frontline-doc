---
title: "Introduction"
description: "Gatling Enterprise architecture overview and conventions"
date: 2021-03-26T16:01:21+01:00
lastmod: 2021-08-16T17:55:36+02:00
weight: 20010
---

## Audience and Goals

This section is intended for operations people in charge of deploying the Gatling Enterprise components.

It describes Gatling Enterprise's architecture, components, how to install them and what the prerequisites are.

## Architecture Overview

{{< img src="frontline-architecture.png" alt="Gatling Enterprise Architecture - Step 3" >}}

Gatling Enterprise consists of:

- A Cassandra database
- The Gatling Enterprise WebApp:
  * A rich web UI
  * A public REST API
- The Gatling Enterprise Extensions:
  * A Grafana Datasource for querying Gatling Enterprise metrics
  * Continuous Integration plugins for Jenkins, Bamboo and TeamCity or any solution that can trigger a bash script
- Gatling injectors to be used like standard Gatling OSS, with extra features so Gatling Enterprise can collect data from them

## Document Conventions

In this document, you'll find several mentions to some placeholders in capital letters.

- REPLACE_WITH_YOUR_REPOSITORY_URL: the URL of the private repository that you were given alongside with your license key

{{< alert warning >}}
This placeholder only makes sense for on premise customers. AWS Marketplace customers spawn a pre-installed AMI and already have all the dependencies they need.
{{< /alert >}}

- REPLACE_WITH_LATEST_FRONTLINE_VERSION: {{< var revnumber >}} at the time this document was edited
