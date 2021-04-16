---
title: "FrontLine 1.12 Highlights"
description: "Learn about the main new features of FrontLine 1.12"
lead: "FrontLine 1.12 introduce scoped private keys, OpenId Connect and a permissions rewamp"
date: 2021-04-06T17:55:28+02:00
lastmod: 2021-04-06T17:55:28+02:00
draft: false
images: []
menu:
  docs:
    parent: "release"
weight: 60030
toc: true
---

### FrontLine 1.12

#### Scoped Private Keys

Private Keys now target either `repositories` or `pools`.

You are now required to fill this scope when creating new private keys.

Upgrading automatically updates existing private keys depending on their usage.

{{< alert tip >}}
Upgrade automatic migration won't upgrade private keys that are unused or used for both repositories and pools.
Such unscoped private keys will keep on working with FrontLine 1.12 but will stop being supported in FrontLine 1.13.
We recommend you delete the unused ones and duplicate the ones used for both repositories and pools (unlikely use case).
{{< /alert >}}

#### Revamped Roles with new Test Admin Role

We now have the following roles:

* `viewer` (previously `member`): can view simulations and runs
* `tester` (*new*): all of the above + can edit simulations and trigger runs
* `test admin` (previously `manager`): all of the above + can edit repositories and associated private keys
* `system admin` (previously `admin`): all of the above + can edit pools and associated private keys, users, global properties, API tokens and team settings (simulations quotas)

`superAdmin` is a special system admin. You should only use it to create system admins.

#### Team Simulation Quotas

Managing a global licenced pool of simulations amongst multiple project teams can be cumbersome.

System admins can now define `quotas` on how many simulations a given team can use.

#### New OpenID Connect Integration

FrontLine can now integrate with your favorite OIDC provider such as Azure Active Directory or Okta.

{{< alert tip >}}
If you want to migrate from FrontLine's embedded auth or LDAP to OIDC, you might have to fix the usernames to match the new system.
Beware that in OAuth2, usernames are case-sensitive!
Please check [the tool we provide for helping to migrate those users](https://github.com/gatling/frontline-users-migration-tool).
{{< /alert >}}

#### New Pools Features

##### Generic

Pools can now be duplicated.

##### AWS

* Spot instances support

##### GCE

* Built-in certified images
* Preemptible instances support
* Image and Instance type support (using instance templates is now deprecated)
* Injector static IP support
* Service accounts support

### Gatling 3.4.0

#### What's New

The key new features are:

* official gradle support with the [new `gatling-gradle-plugin`](https://gatling.io/docs/current/extensions/gradle_plugin/)
* new [sequential scenarios](https://github.com/gatling/gatling/issues/3830)
* TLSv1.3 is now enabled by default

This release also includes lots of performance and bug fixes.
Please check the https://github.com/gatling/gatling/milestone/92?closed=1[full release note].

#### Upgrading Gatling version in your Projects

Gatling 3.4 is mostly source compatible with Gatling 3.3 but there are still *a few minor breaking changes*,
please check the [Gatling 3.4 Migration Guide](https://gatling.io/docs/current/migration_guides/3.3-to-3.4/).

{{< alert tip >}}
FrontLine 1.12 supports deploying projects using Gatling 3.3 and 3.4.
{{< /alert >}}

The benefit is that *you don't have to upgrade all your projects at the same time you upgrade FrontLine* when a new Gatling minor version breaks binary compatibility.

You can upgrade FrontLine first, your existing projects will keep on working, and upgrade them to newest Gatling progressively.

{{< alert tip >}}
You are still required to upgrade to Gatling 3.4 at some point.
{{< /alert >}}

* supporting deploying Gatling 3.3 projects will be dropped at some point, possibly when we will release Gatling 3.5.0
* the Gatling 3.3 branch is frozen, meaning no bug fixes will be backported

#### Upgrading maven/gradle/sbt plugin version in your Projects

{{< alert tip >}}
We recommend that you upgrade to the latest versions of the build plugins so that FrontLine can detect which version of Gatling was used to compile:
{{< /alert >}}

* for maven: `frontline-maven-plugin` *1.2.0*.
* for sbt: `sbt-frontline` *1.3.0*.
* for gradle: `frontline-gradle-plugin` *1.2.0*.

{{< alert warning >}}
Breaking change: when upgrading `frontline-gradle-plugin`, you are now required to use a specific layout and store your Gatling code under `src/gatling/scala`.
Please check the sample you can download from FrontLine's web UI.
{{< /alert >}}

#### Upgrading your injectors JVM settings

We've updated the default JVM options to:

```
-server
-Xmx1G
-XX:+UseG1GC
-XX:+ParallelRefProcEnabled
-XX:MaxInlineLevel=20
-XX:MaxTrivialSize=12
-XX:-UseBiasedLocking
```

and the default System properties are now empty.

Those new settings produced way better performance in our internal benchmarks.

If you've overridden the default global properties with your own parameters, you might probably want to revisit them.

### Operations

{{< alert tip >}}
Prior to upgrading, make sure to perform the required backups as described at the beginning of the Installation Guide.
{{< /alert >}}

#### FrontLine Binaries Distribution Platform Change

{{< alert tip >}}
The http://repository.gatling.io/ server will be shut down end of November 2020.
From now on, please use https://downloads.gatling.io/.
{{< /alert >}}

Download urls on the new server use the same pattern as the old one, eg:

https://downloads.gatling.io/releases/YOUR_CUSTOMER_ID/`

#### Minimal Original FrontLine Version

If you are currently using a version of FrontLine older than 1.6.2, you can't directly upgrade to FrontLine 1.12.0.
You have to first upgrade to FrontLine 1.11.1.

Otherwise, if you are running FrontLine 1.6.2 or newer, you can directly upgrade.
