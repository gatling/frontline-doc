---
title: "Bamboo Plugin"
description: "Learn how to configure the Gatling Enterprise Bamboo plugin and run your simulations."
lead: "Run your Gatling Enterprise simulations from your Bamboo CI."
date: 2021-03-08T13:49:49+01:00
lastmod: 2023-02-28T14:00:00+00:00
weight: 5050
---

## Purpose of this plugin

This plugin enables you to start a Gatling Enterprise simulation directly from your Bamboo platform. This plugin links a Bamboo job with one Gatling Enterprise simulation.

This plugin doesn't create a new Gatling Enterprise simulation, you have to create it using the Gatling Enterprise Dashboard before.

## Installation

To download the plugin, you need to get the jar file located at:

```
https://downloads.gatling.io/releases/frontline-bamboo-plugin/{{< var ciPluginsVersion >}}/frontline-bamboo-plugin-{{< var ciPluginsVersion >}}.jar
```

You need to be connected as an administrator of your Bamboo application to install it. Go *Bamboo Administration*, *Manage Apps*, *Upload app*, and choose the jar file.

{{< img src="installation.png" alt="Installation" >}}

## Configuration

The plugin needs some global configuration. Go to __Administration__, then __Global variables__.

Add two new variables:

* The first one is named __frontline.address__, and corresponds to the address of your Gatling Enterprise, for example: https://cloud.gatling.io
* The second one is named __frontline.apiTokenPassword__, and corresponds to the [API token]({{< ref "../../admin/api-tokens" >}}) needed to authenticate to Gatling Enterprise. The API token needs the *All* role.

{{< img src="global-variable.png" alt="Global variable" >}}

## Job set-up

### Job configuration

Add a new build task called __Gatling Enterprise__. Choose in the Gatling Enterprise Simulation list the simulation you want to use.

{{< img src="configuration-task.png" alt="Task configuration" >}}

This job regularly prints a summary of the run's current status to the build logs. By default, the summary is printed every 5 seconds the first 12 times (i.e. for the first 60 seconds), and then every 60 seconds. You can configure this behavior (or disable it completely) in the job configuration.

### JUnit reporting

You can display the results of the Gatling Enterprise assertions with the JUnit Parser plugin.

Add a new build task called __JUnit Parser__ and fill the __Specify custom results directories__ input with the following line:

`**/gatlingFrontLineJunitResults/*.xml`

{{< alert danger >}}
Be sure to place this task always after the __Gatling Enterprise__ task, or it won't read the results of the new run.
{{< /alert >}}

{{< alert danger >}}
If you don't have any assertions in your Gatling simulation, the JUnit task will fail.
{{< /alert >}}

{{< img src="configuration-junit.png" alt="JUnit configuration" >}}

## Usage

A new Gatling Enterprise simulation will be started every time the job is run. Check the logs to check the simulation progress. If the simulation ran successfully, it will look like the following:

{{< img src="console-output.png" alt="Console output" >}}

If the Gatling Enterprise deployment fails (i.e. because of a shortage of available hosts), the plugin will retry 3 times to redeploy the simulation.

Live metrics will be displayed in the console, and in the build summary.

{{< img src="results.png" alt="Results" >}}
