---
title: "Teamcity Plugin"
description: "Learn how to configure the Gatling Enterprise TeamCity plugin and run your simulations."
lead: "Run your Gatling Enterprise simulations from your TeamCity CI."
date: 2021-03-08T13:50:20+01:00
lastmod: 2021-03-08T13:50:20+01:00
weight: 50020
---

## Purpose of this plugin

This plugin allows you to start a Gatling Enterprise simulation directly from your TeamCity platform. This plugin links a TeamCity plan with one and only one Gatling FrontLine simulation.

This plugin doesn't create a new Gatling Enterprise simulation, you have to create it manually before.

## Installation

To download the plugin, you need to get the zip file located at:

```
https://downloads.gatling.io/releases/frontline-teamcity-plugin/1.13.3/frontline-teamcity-plugin-1.13.3.zip
```

You need to be connected as an administrator of your TeamCity application to install it. Go **Administration**, **Plugins List**, **Upload plugin zip**, and choose the downloaded zip file.

{{< img src="upload-plugin.png" alt="Upload plugin" >}}

## Configuration

The plugin needs some global configuration. Go **Administration**, then **Gatling Enterprise**.

The **Address** is the address of your Gatling Enterprise, for example: https://demo-beta.gatling.io.

The **API Token** will allow TeamCity to authenticate to Gatling Enterprise, without the need of a username or password. To fetch it, refer to the section *Managing API Tokens* in the Gatling Enterprise User Guide.

{{< img src="administration.png" alt="" >}}

## Plan set-up

Add a new build step called **FrontLine Launcher**. Choose in the Simulation list the simulation you want to monitor. You need to configure the global properties of the plugin, and create at least a simulation on your FrontLine to do this step.

{{< img src="configuration.png" alt="Configuration" >}}

### JUnit reporting

You can display the results of the Gatling Enterprise assertions as a JUnit Test.

Add a new build feature called **XML report processing**. Choose **Ant JUnit** as report type, and enter in the **Monitoring rules** input the following line:

`gatlingFrontLineJunitResults/*.xml`

{{< img src="junit.png" alt="JUnit" >}}

## Usage

A new Gatling Enterprise simulation will be started every time the job is run. Check the Console Log to check the advancement of the simulation. If the simulation ran successfully, it will look like the following:

{{< img src="log.png" alt="Console Log" >}}

If the Gatling Enterprise deployment fails (ie because of a shortage of available hosts), the plugin will retry 3 times to redeploy the simulation.

When the job run is finished, you will be able to see on the **FrontLine Results** tab, the summary of the FrontLine simulation.

{{< img src="display-results.png" alt="Display results" >}}
