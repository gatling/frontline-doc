---
title: "Bamboo Plugin"
description: "Learn how to configure Bamboo plugin and integrate your simulations."
lead: "Integrate your simulations to your Bamboo CI."
date: 2021-03-08T13:49:49+01:00
lastmod: 2021-03-08T13:49:49+01:00
draft: false
images: []
menu:
  docs:
    parent: "plugins"
weight: 030
---

## Purpose of this plugin

This plugin enables you to start a Gatling FrontLine simulation directly from your Bamboo platform. This plugin links a Bamboo job with one Gatling FrontLine simulation.

This plugin doesn't create a new Gatling FrontLine simulation, you have to create it using the FrontLine Dashboard before.

## Installation

To download the plugin, you need to get the jar file located at:

```
https://downloads.gatling.io/releases/frontline-bamboo-plugin/1.13.3/frontline-bamboo-plugin-1.13.3.jar
```

You need to be connected as an administrator of your Bamboo application to install it. Go *Bamboo Administration*, *Manage Apps*, *Upload app*, and choose the jar file.

{{< img src="installation.png" alt="Installation" >}}

## Configuration

The plugin needs some global configuration. Go to __Administration__, then __Global variables__.

Add two new variables:

* The first one is named __frontline.address__, and corresponds to the address of your FrontLine, for example: https://demo-beta.gatling.io
* The second one is named __frontline.apiTokenPassword__, and corresponds to the token needed to authenticate to Gatling FrontLine. To fetch it, refer to the section *Managing API Tokens* in the FrontLine User Guide.

{{< img src="global-variable.png" alt="Global variable" >}}

## Job set-up

### Job configuration

Add a new build task called __Gatling FrontLine__. Choose in the FrontLine Simulation list the simulation you want to use.

{{< img src="configuration-task.png" alt="Task configuration" >}}

### JUnit reporting

You can display the results of the Gatling FrontLine assertions with the JUnit Parser plugin.

Add a new build task called __JUnit Parser__ and fill the __Specify custom results directories__ input with the following line:

`**/gatlingFrontLineJunitResults/*.xml`

{{< alert danger >}}
Be sure to place this task always after the GatlingFrontLine task, or it won't read the results of the new run.
{{< /alert >}}

{{< alert danger >}}
If you don't have any assertions in your Gatling simulation, the JUnit task will fail.
{{< /alert >}}

{{< img src="configuration-junit.png" alt="JUnit configuration" >}}

## Usage

A new Gatling FrontLine simulation will be started every time the job is run. Check the logs to check the simulation progress. If the simulation ran successfully, it will look like the following:

{{< img src="console-output.png" alt="Console output" >}}

If the Gatling FrontLine deployment fails (i.e. because of a shortage of available hosts), the plugin will retry 3 times to redeploy the simulation.

Live metrics will be displayed in the console, and in the build summary.

{{< img src="results.png" alt="Results" >}}
