---
title: "Jenkins Plugin"
description: "Learn how to configure the Gatling Enterprise Jenkins plugin and run your simulations."
lead: "Run your Gatling Enterprise simulations from your Jenkins CI."
date: 2021-03-08T13:50:14+01:00
lastmod: 2021-08-16T17:55:36+02:00
weight: 5010
---

## Purpose of this plugin

This plugin enables you to start a Gatling Enterprise simulation directly from your Jenkins platform. This plugin links a Jenkins job with one and only one Gatling Enterprise simulation.

This plugin doesn't create a new Gatling Enterprise simulation, you have to create it using the Gatling Enterprise Dashboard before.

## Installation

To download the plugin, you need to get the hpi file located at:

```
https://downloads.gatling.io/releases/frontline-jenkins-plugin/{{< var ciPluginsVersion >}}/frontline-jenkins-plugin-{{< var ciPluginsVersion >}}.hpi
```

You need to be connected as an administrator of your Jenkins application to install it. Go **Manage Jenkins**, **Manage Plugins**, **Advanced**, **Upload Plugin**, and choose the hpi file.

{{< img src="installation.png" alt="Installation" >}}

## Configuration

The plugin needs some global configuration. Go **Manage Jenkins**, **Configure System**, then **Global Gatling Enterprise Plugin Configuration**.

The [API token]({{< ref "../../admin/api-tokens" >}}) will allow Jenkins to authenticate to Gatling Enterprise. The API token needs the *All* role.

You can configure the API Token globally in this page, or per CI project if each project has an API Token scoped on a specific team. We recommend storing the API Token in a secret text credential, but you can also copy the content of the API Token in the second field.

The **Address** is the address of your Gatling Enterprise API, for example: https://cloud.gatling.io.

{{< img src="global-configuration.png" alt="Global Configuration" >}}

## Job set-up

### Set-up for a pipeline job (available since Jenkins 2.0)

#### Basics

You can use the Pipeline Snippet Generator to help you use the Jenkins Plugin. Click on the **Pipeline Syntax** link, then choose the step `gatlingFrontLineLauncherStep`.

{{< img src="pipeline-generator.png" alt="Snippet Generator" >}}

You can specify the id of an API Token stored in a secret text credential if you don't want to use the one configured globally. Choose one of the simulation in the drop-down, then click Generate Groovy. Copy and paste the result in your Pipeline script, eg:
```groovy
// Declarative Pipeline Syntax
pipeline {
    agent any
    stages {
        stage("Gatling Enterprise simulation") {
            steps {
                gatlingFrontLineLauncherStep credentialId: '6737158c-0ff6-4033-91ad-6f3a811aab52', simulationId: '00eacd1c-ef91-4076-ad57-99b4c6675a9e'
            }
        }
    }
}

// Scripted Pipeline Syntax
node {
    stage("Gatling Enterprise simulation") {
        gatlingFrontLineLauncherStep credentialId: '6737158c-0ff6-4033-91ad-6f3a811aab52', simulationId: '00eacd1c-ef91-4076-ad57-99b4c6675a9e'
    }
}
```
{{< img src="pipeline-configuration.png" alt="Pipeline Configuration" >}}

#### Passing parameters

You can also specify a custom Map of system properties which will be used in the Gatling Enterprise run. The syntax is the following:
```groovy
gatlingFrontLineLauncherStep(simulationId: '00eacd1c-ef91-4076-ad57-99b4c6675a9e', systemProps: ["var": "$var1", "sensitive.var2": "this prop won't be displayed in the run snapshot"])
```

#### Displaying assertions as JUnit

You can display the results of the Gatling Enterprise assertions with the JUnit plugin. Add the following line:
```groovy
junit("gatlingFrontLineJunitResults/*.xml")
```

{{< alert danger >}}
If you don't have any assertions in your Gatling simulation, the JUnit step will fail.
{{< /alert >}}

### Set-up for an old style job

Add a new build step called **Gatling Enterprise Plugin**. Choose in the Simulation list the simulation you want to use. You can specify the id of an API Token stored in a credential if you don't want to use the one configured globally.

{{< img src="build-configuration.png" alt="Build configuration" >}}

You can display the results of the Gatling Enterprise assertions with the JUnit plugin.

Add a new build step called **Publish JUnit test result report** and fill the **Test report XMLs** input with the following line:

`gatlingFrontLineJunitResults/*.xml`

{{< img src="junit-configuration.png" alt="JUnit Configuration" >}}

{{< alert danger >}}
If you don't have any assertions in your Gatling simulation, the JUnit step will fail.
{{< /alert >}}

## Usage

A new Gatling Enterprise simulation will be started every time the job is run. Check the Console Output to check the simulation progress. If the simulation ran successfully, it will look like the following:

{{< img src="console-ok.png" alt="Console View" >}}

If the Gatling Enterprise deployment fails (i.e. because of a shortage of available hosts), the plugin will retry 3 times to redeploy the simulation.

Live metrics will be displayed in the console, and in the Status page. The link **View Run in Gatling Enterprise** in the build page menu links to Gatling Enterprise.

{{< img src="run-view.png" alt="Results" >}}
