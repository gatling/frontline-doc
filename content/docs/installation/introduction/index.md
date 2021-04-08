---
title: "Introduction"
description: "Architecture overview and conventions"
date: 2021-03-26T16:01:21+01:00
lastmod: 2021-03-26T16:01:21+01:00
draft: false
images: []
menu:
  docs:
    parent: "installation"
weight: 20010
toc: true
---

## Audience and Goals

This section is intended for operations people in charge of deploying the FrontLine components.

It describes FrontLine's architecture, components, how to install them and what the prerequisites are.

## Architecture Overview

{{< img src="frontline-architecture.png" alt="FrontLine Architecture - Step 3" >}}

FrontLine consists of:

- A Cassandra database
- The FrontLine WebApp:
  * A rich web UI
  * A public REST API
- The FrontLine Extensions:
  * A Grafana Datasource for querying FrontLine metrics
  * Continuous Integration plugins for Jenkins, Bamboo and TeamCity or any solution that can trigger a bash script
- Gatling injectors to be used like standard Gatling OSS, with extra features so FrontLine can collect data from them

## Document Conventions

In this document, you'll find several mentions to some placeholders in capital letters.

- REPLACE_WITH_YOUR_REPOSITORY_URL: the URL of the private repository that you were given alongside with your license key

{{< alert warning >}}
This placeholder only makes sense for on premise customers. AWS Marketplace customers spawn a pre-installed AMI and already have all the dependencies they need.
{{< /alert >}}

- REPLACE_WITH_LATEST_FRONTLINE_VERSION: {{< var revnumber >}} at the time this document was edited
