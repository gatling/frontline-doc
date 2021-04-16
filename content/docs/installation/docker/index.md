---
title: "Automated installation with Docker"
description: "Learn how to install FrontLine with Docker"
lead: "Install FrontLine and Cassandra easily with Docker or Docker Compose"
date: 2021-03-26T17:31:42+01:00
lastmod: 2021-03-26T17:31:42+01:00
draft: false
images: []
menu:
  docs:
    parent: "installation"
weight: 20040
toc: true
---

Running FrontLine with Docker is the recommended solution, as it requires the minimal amount of setup and is the easiest way to upgrade to newer versions.

## Getting FrontLine's Docker image

FrontLine's image is hosted as a private image on [Docker Hub](https://hub.docker.com/r/gatlingcorp/frontline).

Please contact our support and provide us with your Docker Hub username so we can grant you access.

## Setup Cassandra

The following command will start a single-node Cassandra cluster, using the official Cassandra image.
This will also mount a local directory on the host into the container, to persist its data across starts/stops of the Cassandra container.

```bash
docker run -d \
  --name cassandra \
  -v <local directory to store Cassandra data>:/var/lib/cassandra \
  -p 9042:9042 \
  cassandra:3.11.6
```

## Copy FrontLine default configuration

To be able to persist FrontLine (required, as it stores the license information and key used for data encryption), create a directory and copy [FrontLine's default configuration]({{< ref "/docs/installation/configuration" >}}) to a file named `frontline.conf`.

## Setup FrontLine

The following command will start FrontLine, mapping to its default port on the host.
This will also mount two volumes:

* A volume with FrontLine's configuration, prepared by the previous step.
* A volume to store uploaded private keys (required by features like Git cloning and by some cloud providers)

```bash
docker run -d \
  --name frontline \
  -p 10542:10542 \
  -v <configuration directory from the previous step>:/opt/frontline/conf \
  -v <local directory to store private keys>:/opt/frontline/keys \
  gatlingcorp/frontline:{{< var revnumber >}}
```

{{< alert tip >}}
Depending on your needs, you may need to configure additional volumes on the FrontLine container (SSL certificate if HTTPS is configured, or keystore/truststore for LDAP support)
{{< /alert >}}

## Docker compose

For your convenience, here are some docker compose instructions to set up a quick test environment. This is enough to start FrontLine locally.

**Cassandra container:**

Based on cassandra image `cassandra:3.11.6`, you'll have to bind a local directory on the volume to store and keep your data. A healthcheck will be started on the container.

**FrontLine container:**

Wait until cassandra container healthcheck indicates that all is well.
Set the version of FrontLine you want to deploy, bind a local directory for FrontLine configuration and keys.

### Prerequisite

Create a folder to store the files, add the given `docker-compose.yml`, then run `docker-compose up -d`.
Once ready, visit FrontLine at [http://localhost:10542](http://localhost:10542)!

```yaml
{{< include-static "docker-compose.yml" revnumber >}}
```
