---
title: "Configuration"
description: "Configuration of Gatling Enterprise"
lead: "First time configuration of Gatling Enterprise and content of the default configuration file"
date: 2021-03-26T17:57:29+01:00
lastmod: 2021-03-26T17:57:29+01:00
weight: 20060
---

## Configuring Gatling Enterprise

The first step before using Gatling Enterprise is to configure your license key.

{{< img src="configuration.png" alt="License key configuration" >}}

Once you've filled your license and clicked on the "Next" button you will get the credentials to connect to the superAdmin account. You can change this password in the frontline.conf file.

{{< img src="adminCredentials.png" alt="Admin credentials" >}}

Click on the "Next" button to finish the configuration step and restart Gatling Enterprise.

## Default Configuration File

Find below the default `frontline.conf` file:

{{< include-code "frontline.conf" hocon >}}
