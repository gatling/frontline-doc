---
title: "Manual Installation"
description: "Learn how to install manually FrontLine"
lead: "Install or upgrade manually FrontLine, and learn how to configure it"
date: 2021-03-26T16:08:33+01:00
lastmod: 2021-03-26T16:08:33+01:00
draft: false
images: []
menu:
  docs:
    parent: "installation"
weight: 20020
toc: true
---

## Upgrading From a Previous Version

* Your current FrontLine version must be at least 1.6.2. Otherwise, you must first upgrade to FrontLine 1.11.1.
* Shut FrontLine process down
* Perform a backup of your Cassandra data (eg `/var/lib/cassandra` directory, but might vary depending on how you've installed Cassandra)
* Download and unzip updated FrontLine bundle
* Copy `conf` directory content from previous installation to the new one
* Copy `keys` directory from previous installation (if it exists) to the new one (`cp -r previous_frontline_folder/keys new_frontline_folder`)
* Start new FrontLine process

## Processor Architecture

We currently only support AMD64 for both FrontLine and Gatling injectors.

We don't support ARM at the moment.

## JDK

FrontLine components runs on a JVM and requires a modern Hotspot-based JDK 8 or 11.

We recommend you use JDK builds from [AdoptOpenJDK](https://adoptopenjdk.net/).

Other JVMs such as OpenJ9 are not supported.

{{< alert warning >}}
- As of Cassandra 3, JDK 11 support is flagged as experimental.
- As a consequence, we only support running Cassandra with JDK 8.
- JDK 11 support is limited to FrontLine server and Gatling injectors only.
{{< /alert >}}

## Linux

{{< alert warning >}}
- FrontLine and injectors are intended to be running on Linux 64 bits.
- Injectors are intended to run on Kernel >= 3.10.
- It's possible to use OSX as a development environment.
- Windows and Unix platforms such Solaris or AIX are not supported.
{{< /alert >}}

As FrontLine is about duration measurement and logging events in time, we advice that:

* your system clock is properly synchronized from an NTP server
* you disable power saving Linux features, so clock source doesn't actually shift and stays monotonic

Make sure that the JVM processes run with a user with sound permissions.

## Cassandra

### Important: Backups

{{< alert warning >}}
We recommend you to perform regular backups of your data, all the more prior to upgrading FrontLine to a new version.
{{< /alert >}}

For a simple single node setup (which we recommend if you're not experienced with operating Cassandra clusters),
performing a Cassandra backup simply consists of make a copy (eg, tar.gz) of the directories that are configured in
your `cassandra.yaml` file as the following entries:

* data_file_directories
* commitlog_directory
* saved_caches_directory

### Download

Download and install [Cassandra](http://cassandra.apache.org/download/).

As of FrontLine {{< var revnumber >}}, we require at least Cassandra 3.10.
FrontLine has been tested against Cassandra 3.10 to 3.11.7.
If possible, we advise you go with the latest stable version.

### Deployment

Running a single node (without clustering) is a good start.

For an initial evaluation, you can host FrontLine and Cassandra on the same host.

If hosting FrontLine and Cassandra on the same instance, consider a 8 cores (4 cores with hyper-threading) host with 16Gb of RAM and 20Go of disk space.
Otherwise, considering a 4 cores (2 cores with hyper-threading) host with 8 Gb of RAM for each instance, and 20 Gb of disk space for Cassandra.

FrontLine itself requires little disk space, as only uploaded private keys are stored on FrontLine's host.

### Storage options

There are simple rules to follow when choosing the right type of instance to use for a Cassandra virtual machine:

- SSD is always best
- Avoid network disks

{{< alert warning >}}
For any cloud provider, make sure you use a storage system that support at least 3000 IOPS as we encountered slow downs with 1500 IOPS.
{{< /alert >}}

For AWS, avoid burstable instance types on AWS EC2 (T2), they don't offer good performance for Cassandra deployments:

- C5d are equipped with NVMe SSD, which are great for installation running Cassandra and FrontLine on the same virtual machine
- I3 are better for virtual machines reserved for Cassandra

In Azure, use instances with the `s` prefix:

- FsV2 are great for installations running Cassandra and FrontLine on the same virtual machine
- LsV2 are better for virtual machines reserved for Cassandra

### Configuration

The default configuration is a good start.

Finally, remember the host you configured, as you will need it later to configure the contact points of FrontLine.
Keyspace creation will be handled by FrontLine.

{{< alert tip >}}
- For most use cases, one single Cassandra node suffice!
- Only run a Cassandra cluster if you need it and know how to operate it.
- In this case, make sure to configure replication properly in `frontline.conf`.
{{< /alert >}}

### Network Access

The FrontLine host needs network access to:

- your Cassandra cluster
- your source repository (if you want to build from sources)
- your binary repositories (if you want to download pre-packaged simulations), typically:
  * Maven central repository: https://repo1.maven.org/maven2
  * JCenter repository (sbt and gradle users only): https://jcenter.bintray.com/
  * Gradle plugins portal: https://plugins.gradle.org
  * or instead, the internal mirrors your organization might be enforcing
- the hosts where it will try to deploy Gatling injectors
- your cloud provider API (if deploying on-demand instances on public cloud providers)

{{< alert warning >}}
Don't forget to open the `22` (for SSH) and `9999` (for HTTP) ports on the injectors. If you don't, your runs will appear as `Broken`.
{{< /alert >}}

{{< alert warning >}}
Do NOT use a load balancer on a Cassandra cluster. The Cassandra network driver already does its own load balancing, adding a load balancer on top of it would break cluster communication.
{{< /alert >}}

### Rack and Data Centers

Cassandra new java driver uses data center as a mandatory value to initialize the client. Users who want to transition to a properly scaled cluster might need to adjust.

From the default configuration file, we can read:

```
endpoint_snitch -- Set this to a class that implements IEndpointSnitch.

The snitch has two functions:
- it teaches Cassandra enough about your network topology to route requests efficiently
- it allows Cassandra to spread replicas around your cluster to avoid correlated failures. It does this by grouping machines into "datacenters" and "racks." Cassandra will do its best not to have more than one replica on the same "rack" (which may not actually be a physical location)

CASSANDRA WILL NOT ALLOW YOU TO SWITCH TO AN INCOMPATIBLE SNITCH ONCE DATA IS INSERTED INTO THE CLUSTER.

This would cause data loss. This means that if you start with the default SimpleSnitch, which locates every node on "rack1" in "datacenter1", your only options if you need to add another datacenter are GossipingPropertyFileSnitch (and the older PFS).  From there, if you want to migrate to an incompatible snitch like Ec2Snitch you can do it by adding new nodes under Ec2Snitch (which will locate them in a new "datacenter") and decommissioning the old ones.
```

Cassandra default endpoint snitch is `SimpleSnitch`. Which defaults values of data center and rack to "datacenter1" and "rack1". They are hardcoded and therefore can't be changed.

When using other snitches (E.g., `GossipingPropertyFileSnitch`), Cassandra will use the content of the `cassandra-rackdc.properties` configuration file to build it's network topology or the private IP of the machine (E.g.: Ec2Snitch)

When scaling out, consider these two cases to migrate your (single) instance to a proper cluster configuration:

* Single instance (straighforward)
* Multiples instances of any snitches to any others (probably incompatible) snitches

First method being something like (also applies to cluster name):

```sql
update system.local set data_center = 'dc1' where key = 'local';
update system.local set rack = 'rc1' where key = 'local';
```

Second method implies (as stated in Cassandra documentation) to add instances with proper configuration and removing the old ones as you go in order to avoid data loss.

## FrontLine Server

### Download

FrontLine is packaged as a zip bundle that can be downloaded from our maven repository (only for on-premise customers):

```
REPLACE_WITH_YOUR_REPOSITORY_URL/frontline-bundle/REPLACE_WITH_LATEST_FRONTLINE_VERSION/frontline-bundle-REPLACE_WITH_LATEST_FRONTLINE_VERSION-bundle.zip
```

On launch, FrontLine will create or update the FrontLine schema in the Cassandra database.

### Launch

You can launch FrontLine in the background using the following command:

```bash
[... frontline-bundle ]$ ./bin/frontline
```

The web interface will then be accessible by default on port `10542`. You need to connect in order to fill in your license key.

FrontLine will log its PID and write it to a `pidfile` which names will also be echoed.
You can provides you own path to a custom pidfile this way:

```bash
[... frontline-bundle ]$ ./bin/frontline -p pidfile
```

Using the foreground mode will cancel the handling of a pidfile.

### Configuration

Check the `conf/frontline.conf` file for parameters you might want to edit.

```
licenseKey = REPLACE_WITH_YOUR_LICENSE_KEY <1>
```

<1> Provided license key, you should not edit this configuration directly from this file, FrontLine will ask for it when you launch it or when your current license is expired

```
http {
  port = 10542 <1>
  cookieMaxAge = 604800 <2>
  ssl { <3>
    #certificate = "/path/to/domain.crt" <4>
    #privateKey = "/path/to/domain.key" <5>
    generateSelfSignedCertificate = false <6>
  }
  proxy { <7>
    #host = ""
    #port = 80
  }
}
```

* <1> FrontLine HTTP bind port
* <2> Cookies max-age in seconds (default: 7 days)
* <3> SSL configuration, activated if both `certificate` and `privatekey` are uncommented and points to valid files, or if `generateSelfSignedCertificate` is true.
* <4> Path to the certificate (or full chain) file. Must be an X.509 certificate chain file in PEM format.
* <5> Path to the private key file. Must be a PKCS#1 or PKCS#8 private key file in PEM format.
* <6> For testing purpose, you can make FrontLine produce a self signed certificate
* <7> Optional HTTP proxy, enabled when both host and port are filled. This proxy will be used for every HTTP request to Cloud providers APIs and on-demand injectors.

```
injector {
  httpPort = 9999 <1>
  enableLocalPool = false <2>
  kubernetes {
    disableTrustManager = true <3>
  }
}
```

* <1> Injectors HTTP listening port, so FrontLine can connect and collect the stats
* <2> Enable local injector pool (not for production use)
* <3> When connecting to your kubernetes API, determine if you want a true trust manager to be used to validate your certificate. Disabled by default.

```
security {
  superAdminPassword = gatling <1>
  secretKey = "MUST BE CHANGED!" <2>
}
```

* <1> password for the FrontLine superAdmin account. FrontLine will create a new password when you launch it for the first time.
* <2> key for encrypting cookies. Must be 128, 192 or 256 bit (not bytes) long. FrontLine will create a new secretKey when you launch it for the first time.

```
cassandra { <1>
  localDataCenter = datacenter1 <2>
  contactPoints = [{
    host = localhost
    port = 9042
  }]
  keyspace = gatling
  replication = "{'class':'SimpleStrategy', 'replication_factor': 1}"
  batchGroupingSize = 25
  credentials { <3>
    #username = "hello"
    #password = "world"
  }
  runsCleanup { <4>
    #timeOfDay = "15:10" <5>
    #maxRunsBySimulation = 30 <6>
    #maxRunAge = 100 <7>
  }
}
```

* <1> Can be adapted to your current Cassandra cluster configuration.
* <2> The local data center your contact points belong to. Cassandra's value with SimpleStrategy is "datacenter1".
* <3> The username/password credentials for connecting to Cassandra
* <4> You can configure daily cleanups for your runs in this part.
* <5> The hour of the daily cleanup, mandatory to activate the feature. The format is ISO 8601 (e.g.: 17:45).
* <6> The maximum number of runs by simulation. Can be combined with <7>.
* <7> The max age for the runs, in days. Can be combined with <6>.

```
ldap { <1>
  #host = localhost <2>
  #port = 389 <3>
  #baseDn = "dc=example,dc=com" <4>
  #distinguishedName = "cn=John Doe,ou=Users,dc=example,dc=com" <5>
  #password = "secret" <6>
  #usernameAttribute = uid<7>
  #firstNameAttribute = givenName
  #surnameAttribute = sn
  #mailAttribute = mail
  #connectTimeoutMs = 5000<8>
  #responseTimeoutMs = 10000<9>
  #personObjectClass = person<10>
}
```

* <1> The LDAP configuration, use this part of the config only if you want to enable LDAP based user management
* <2> Uncommenting this line enable LDAP based user management. Correspond to your LDAP server IP address / hostname
* <3> The port used to access your LDAP server.
* <4> The base DN where your users are stored in your LDAP
* <5> The distinguished name of a read-only technical account used to search on your LDAP
* <6> The password of the above technical account
* <7> You can override default attribute names in LDAP
* <8> The connect timeout to your LDAP
* <9> The response timeout when searching your LDAP
* <10> The objectClass of your users if they have one. Used to filter out search results

```
ldap {
  ssl { <1>
    #format = "PEM | JKS" <2>
    pem { <3>
      #serverCertificate = "/path/to/domain.pem" <4>
      #clientCertificate = "/path/to/domain.pem" <5>
      #privateKey = "/path/to/domain.key" <6>
    }
    jks { <7>
      #trustStore = "path/to/truststore.jks" <8>
      #trustStorePassword = "secret" <9>
      #keystore = "path/to/keystore.jks" <10>
      #keystorePassword = "secret" <11>
    }
  }
}
```

* <1> Your TLS configuration for LDAP (you don't need this part if you use plain LDAP)
* <2> Choose what will be the format of your trust store/key store. Can be either PEM or JKS
* <3> The configuration that will be used if you chose "PEM" in the format
* <4> Path to the server certificate if your LDAP certificate is not signed by a JDK trusted CA
* <5> Path to the client certificate if you need mutual authentication
* <6> Path to the client private key if you need mutual authentication. The key format must be PKCS8
* <7> The configuration that will be used if you chose "JKS" in the format
* <8> Path to the trust store containing the server certificate if your LDAP certificate is not signed by a JDK trusted CA. Optional, will use JDK's default if undefined.
* <9> Password for the trust store
* <10> Path to the key store containing client certificate and private key if you need mutual authentication. Optional, will use JDK's default if undefined.
* <11> Password for the key store

```
oidc {
    # discoveryUrl = "https://provider/.well-known/openid-configuration" <1>
    client {
      # id = "xxxxx-xxxxx-xxxxx-xxxxx-xxxxx" <2>
      # secret = "*******" <3>
    }
    # responseMode = "fragment" | "okta_post_message" <4>
    # scopes = ["openid", "email", "profile"] <5>
    # jwksRefreshFrequency = 1440 <6>
    mapping {
      # username: "unique_name" <7>
      # firstname: "given_name" <8>
      # lastname: "family_name" <9>
      # email: "email" <10>
    }
  }
```

Fill the following fields if you want to enable OpenID authentication on FrontLine. All fields need to be uncommented and filled. For more information on our OpenID integration, please check [corresponding section]({{< relref "#openid" >}}).

* <1> URL for discovery of OpenID endpoints
* <2> Application id received when registering FrontLine
* <3> Client secret used to authenticate FrontLine (we don't support secret certificates yet)
* <4> Response mode preferred for the identity provider, please leave only "fragment" or "okta_post_message".
* <5> Scopes required for mapping
* <6> Frequency for JSON Web Key Store refresh
* <7> JmesPath for username attribute in scoped user profile, must be unique.
* <8> JmesPath for firstname attribute in scoped user profile
* <9> JmesPath for lastname attribute in scoped user profile
* <10> JmesPath for email attribute in scoped user profile

There's no default value for those attributes, they must all be defined in the configuration file.

```
grafana {
  #url = "http://localhost:3008/dashboard/db/frontline-requests" <1>
}
```

<1> Url to your Grafana dashboard using the FrontLine datasource (create a link in FrontLine dashboard to the Grafana dashboard)

If you want to modify a value, don't forget to uncomment the line, by deleting the # sign. Any changes to the frontline.conf file needs a FrontLine restart to take effect.

See [HOCON](https://github.com/typesafehub/config/blob/master/HOCON.md) documentation for more information on this format.

#### Permissions

- Execute permission to JDK path
- Execute permission to source control system client
- Execute permission to build tool client
- Read permission to unzipped FrontLine bundle
- Read/write permission to the logs directory
- Read/write/exec permission on tmp directory
If exec permission is not possible because `/tmp` is mounted with `noexec`,
you'll have to configure a different directory without `noexec`. Edit the FrontLine launch script and pass an additional System properties `-Djna.tmpdir=PATH_TO_DIR_WITHOUT_NOEXEC`.
If you don't you'll run into an issue such as `java.lang.UnsatisfiedLinkError: /tmp/jna-3506402/jna4812891826558064540.tmp: /tmp/jna-3506402/jna4812891826558064540.tmp: failed to map segment from shared object: Operation not permitted`.

#### Logging

FrontLine uses the Logback library for logging.
By default, it will log on the filesystem, check logback.xml file.
Feel free to tune the default behavior if needed.

#### LDAP

FrontLine is able to use LDAP to manage its users. The LDAP mode has been tested with OpenLDAP, and Active Directory servers, but it should work with all regular LDAP implementations.

{{< anchor openid >}}
#### OpenID Authentication

FrontLine is able to use OpenID connect to manage its users. The OpenID Connect mode has been tested with One Login and Microsoft identity platform.
Configuration is described above under _oidc_.

By default, all registered users can connect as a global viewer and need an administrator to configure their permissions.

##### Sample OpenID configuration on Azure

- Go to "App registrations"
- Click on "New registration"
- Set the redirect URI to http://your-frontline-url:your-frontline-port/redirected
- Add a client secret in "Certificates & secrets" (we don't support certificates yet)
- Edit your frontline.conf file, configuration is described above, here is a sample configuration:

```
  oidc {
      discoveryUrl = "https://login.microsoftonline.com/organizations/v2.0/.well-known/openid-configuration"
      client {
        id = "******-*******-********-******"
        secret = "****************"
      }
      responseMode = "fragment"
      scopes = ["email", "profile"]
      jwksRefreshFrequency = 1440
      mapping {
        username: "email"
        firstname: "given_name"
        lastname: "family_name"
        email: "email"
      }
  }
```

##### Sample OpenID configuration on Okta

- Navigate to admin => security => api => authorization server
- Make sure the openid, profile and email scopes are present
- Create an OpenId app
- Set the login redirect URI to http://your-frontline-url:your-frontline-port/redirected
- Copy client ID & client secret
- Assign people / groups to the app
- Edit your frontline.conf file, configuration is described above, here is a sample configuration:

```
  oidc {
      discoveryUrl = "https://your-organisation.okta.com/oauth2/default/.well-known/openid-configuration"
      client {
        id = "******-*******-********-******"
        secret = "****************"
      }
      responseMode = "okta_post_message"
      scopes = ["email", "profile"]
      jwksRefreshFrequency = 1440
      mapping {
        username: "email"
        firstname: "given_name"
        lastname: "family_name"
        email: "email"
      }
  }
```

If the connection fails and the page is blank, check in the browser console for more infos.

#### Run Cleanup

FrontLine can be configured to automatically delete runs based on max-age and/or max number of runs by simulation.

#### Source Control System Client (typically git)

If you intend to have FrontLine build tests from sources,
it needs to be able to fetch the test sources from your remote source repository, ie:

- a client for your Source Control System (ex: git, svn, perforce, etc) to be installed on the FrontLine host
- this client to be in the PATH and executable for the user running the FrontLine JVM process

{{< alert tip >}}
If using Git to clone repositories using SSH protocol, Git 2.3 is the minimal version supported.
{{< /alert >}}

#### Build Tool Client (typically maven, gradle or sbt)

If you intend to have FrontLine build tests from sources,
then FrontLine needs to be able to build the fetched resources, ie:

- a client for your build tool (ex: sbt, maven, gradle, etc) to be installed on the FrontLine host
- this client to be in the PATH and executable for the user running the FrontLine JVM process

Make sure that the build tool will be configured so that it will be able to download artifacts, typically if your organization enforces repository mirrors.

{{< alert tip >}}
Maven 3.3.9 is the minimal version supported. This is the version shipped in Debian 9 (Stretch).
{{< /alert >}}
