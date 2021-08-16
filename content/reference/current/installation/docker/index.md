---
title: "Automated installation with Docker"
description: "Learn how to install Gatling Enterprise with Docker"
lead: "Install Gatling Enterprise and Cassandra easily with Docker or Docker Compose"
date: 2021-03-26T17:31:42+01:00
lastmod: 2021-06-11T13:04:45+01:00
weight: 20040
---

Running Gatling Enterprise with Docker is the recommended solution, as it requires the minimal amount of setup and is the easiest way to upgrade to newer versions.

## Getting Gatling Enterprise's Docker image

Gatling Enterprise's image is hosted as a private image on [Docker Hub](https://hub.docker.com/r/gatlingcorp/frontline).

Please contact our support and provide us with your Docker Hub username so we can grant you access.

{{< alert tip >}}
Ensure that you can pull our image:

```console
docker pull gatlingcorp/frontline:{{< var revnumber >}}
```
{{< /alert >}}

## Setup Cassandra

The following command will start a single-node Cassandra cluster, using the official Cassandra image.
This will also mount a local directory on the host into the container, to persist its data across starts/stops of the Cassandra container.

```console
docker run --detach \
  --name cassandra \
  --volume <local directory to store Cassandra data>:/var/lib/cassandra \
  --publish 9042:9042 \
  cassandra:3
```

## Copy Gatling Enterprise default configuration

To be able to persist Gatling Enterprise (required, as it stores the license information and key used for data encryption), create a directory and copy [Gatling Enterprise's default configuration]({{< ref "../configuration" >}}) to a file named `frontline.conf`.

## Copy logback configuration

The default log behavior is too verbose from a performance point of view. You will have to put this `logback.xml` file in the same directory as `frontline.conf`:

{{< include-code "logback.xml" xml >}}

## Setup Gatling Enterprise

The following command will start Gatling Enterprise, mapping to its default port on the host.
This will also mount two volumes:

* A volume with Gatling Enterprise's configuration, prepared by the previous step.
* A volume to store uploaded private keys (required by features like Git cloning and by some cloud providers)

```console
docker run --detach \
  --name frontline \
  --publish 10542:10542 \
  --volume <configuration directory from the previous step>:/opt/frontline/conf \
  --volume <local directory to store private keys>:/opt/frontline/keys \
  gatlingcorp/frontline:{{< var revnumber >}}
```

{{< alert tip >}}
You should change your `frontline.conf` to let frontline know where to find cassandra. Because of the docker context, they are two different containers and cannot, by default, reach each other as localhost. If really they are on the same host, you can add `--network host` argument to docker run.
{{< /alert >}}
{{< alert tip >}}
Depending on your needs, you may need to configure additional volumes on the Gatling Enterprise container (SSL certificate if HTTPS is configured, or keystore/truststore for LDAP support)
{{< /alert >}}

## Docker compose

For your convenience, here are some docker compose instructions to set up a quick test environment. This is enough to start Gatling Enterprise locally.

**Cassandra container:**

Based on cassandra image `cassandra:3.11`, you'll have to bind a local directory on the volume to store and keep your data. A healthcheck will be started on the container.

**Gatling Enterprise container:**

Wait until cassandra container healthcheck indicates that all is well.
Set the version of Gatling Enterprise you want to deploy, bind a local directory for Gatling Enterprise configuration and keys.

### Prerequisite

Create a folder to store the files, add the given `docker-compose.yml`, then run `docker-compose up -d`.
Once ready, visit Gatling Enterprise at [http://localhost:10542](http://localhost:10542)!

```yaml
version: '2.4'
services:
  cassandra:
    container_name: cassandra
    image: cassandra:3.11
    environment:
      - CASSANDRA_CLUSTER_NAME=FrontLine
    volumes:
      - cassandra:/var/lib/cassandra
      # - <path to your cassandra directory (default empty)>:/var/lib/cassandra
    networks:
      - frontline-network
    healthcheck:
      test: ["CMD-SHELL", "[ $$(nodetool statusgossip) = running ]"]
      interval: 30s
      timeout: 10s
      retries: 10
  frontline:
    container_name: frontline
    image: gatlingcorp/frontline:{{< var revnumber >}}
    ports:
      - 10542:10542
    networks:
      - frontline-network
    environment:
      # FRONTLINE_CASSANDRA_HOST and FRONTLINE_CASSANDRA_PORT are used to update frontline.conf
      - FRONTLINE_CASSANDRA_HOST=cassandra
    volumes:
      - frontline-conf:/opt/frontline/conf
      - frontline-keys:/opt/frontline/keys
      # - <path to your frontline conf directory (default contains frontline.conf)>:/opt/frontline/conf
      # - <path to your frontline keys directory (default empty)>:/opt/frontline/keys

    depends_on:
      cassandra:
        condition: service_healthy
volumes:
  cassandra:
  frontline-conf:
  frontline-keys:
networks:
  frontline-network:
    driver: bridge
```
