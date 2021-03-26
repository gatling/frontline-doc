---
title: "Manual scripting"
description: "Learn how to configure a script to handle your simulations."
lead: "Integrate your simulations to your CI with a script."
date: 2021-03-08T13:50:17+01:00
lastmod: 2021-03-08T13:50:17+01:00
draft: false
images: []
menu:
  docs:
    parent: "plugins"
weight: 040
---

## Purpose of this script

This script has the same purpose as the Jenkins, Bamboo or Teamcity plugin. It enables you to launch a FrontLine simulation and display live metrics.

This script doesn’t create a new Gatling FrontLine simulation, you have to create it using the FrontLine Dashboard before.

## Requirement

jq is needed to run this script. It is a JSON processor available for download [here](https://stedolan.github.io/jq/download/).

## Use

You need to give 3 parameters to the script:

- frontLine url: address of your FrontLine, for example: https://demo-beta.gatling.io
- api token: FrontLine API token created with permission **All**. To fetch the API Token, refer to the section Managing API Tokens in the FrontLine User Guide.
- simulation id: id of the simulation you want to start. You can get this id on the simulation table, with the {{< icon clipboard >}} icon.

## Script

It can be found [here](https://downloads.gatling.io/releases/frontline-ci-script/{{< var revnumber >}}/frontline-ci-script-{{< var revnumber >}}.zip)
