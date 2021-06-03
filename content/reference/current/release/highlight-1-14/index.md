---
title: "Gatling Enterprise 1.14 Highlights"
description: "Learn about the main new features of Gatling Enterprise 1.14"
lead: "Gatling Enterprise 1.14 introduce support for multiple versions of Gatling and performance fixes"
date: 2021-05-25T17:55:36+02:00
lastmod: 2021-05-25T17:55:36+02:00
weight: 60019
---

### Gatling Enterprise 1.14

#### Gatling 3.6, 3.5, 3.4 and 3.3 generations support

Gatling Enterprise 1.14 is compatible with the 4 latest Gatling generations.

{{< alert tip >}}
Only Gatling 3.6 will be actively maintained.
Multiple versions support is for convenience so that customers don't have to upgrade all their tests when upgrading their Gatling Enterprise platform.
Customers are recommended to upgrade to Gatling 3.6 as soon as they can.
{{< /alert >}}

#### Key New Features

* faster crash when a simulation can't be instantiated because of a user error (no more HTTP retries in this case)
* AWS subnets are no multivalued and retried randomly if deploying the pool fails because of insufficient capacity
* full Cassandra Java Driver configuration with Typesafe config (eg configuring TSL)
* the Gatling zip bundle now provides a script to generate uploadable artifacts (eg in an S3 bucket repository)

#### Key Bug Fixes

* several bug fixes related to group menu in Requests and Groups tabs
* updating a filesystem private key didn't properly update the path
* TCP Connection states stats were missing when network was IPv6 while target was IPv4

### Gatling 3.6.0

{{< alert tip >}}
Gatling 3.6 becomes the production version, meaning that no further improvements and bug fixes will happen on Gatling 3.5.
{{< /alert >}}

#### Key New Features

* transparent support for Brotli compression

#### Key Bug Fixes

* several important bug fixes on HTTP/2 support. Upgrading is highly recommended if you use HTTP/2.
* several important bug fixes on async DNS resolution, on particular when used in combination with `shareConnections`

### Operations

{{< alert tip >}}
Prior to upgrading, make sure to perform the required backups as described at the beginning of the Installation Guide.
{{< /alert >}}
