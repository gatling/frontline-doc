---
title: "Release Notes"
description: "Find the detailed release notes of Gatling Enterprise"
date: 2021-04-06T16:38:41+02:00
lastmod: 2024-02-02T15:20:52+00:00
weight: 6010
---

## 1.19.2 (2024-02-02)

### Gatling Enterprise

#### Fixes

* SH-1009: Azure: disabling public IP addresses doesn't work for custom images

## 1.19.1 (2024-01-23)

### Gatling 3.10.3

### Gatling Enterprise

#### Fixes

* SH-998: About: fix wrong diplayed Gatling version
* SH-1007: Azure: upgrade to SDKv2 as SDKv1 is no longer maintained and frequently crashes on error handling (eg bad policies configuration)
* SH-1008: Azure: fix crash when switching credentials type on existing pool

## 1.19.0 (2023-12-18)

{{< alert warning >}}
Starting from this release, Gatling Enterprise requires at least Java 11.

If you're still running the Gatling Enterprise server or your Load Generators with an older version such as Java 8,
you must first upgrade these installations prior to upgrading to Gatling Enterprise 1.19.0.
{{< /alert >}}

### Gatling 3.10.2

{{< alert warning >}}
Support for Gatling 3.3 and 3.4 is planned for removal in Gatling Enterprise 1.20.0.

We recommend that you upgrade your tests to Gatling 3.10.0, older versions are no longer maintained.
{{< /alert >}}

Gatling Enterprise 1.19 supports Gatling versions:
* 3.3 and 3.4 (planned for removal)
* 3.5 to 3.10 included


See full release note: https://github.com/gatling/gatling/milestone/118?closed=1

### Gatling Enterprise

#### Features

* SH-987: AWS, Azure, GCP, Kubernetes: Add Java 21 Load Generator images

#### Fixes

* SH-992: Teams: check team simulation quota when transferring simulation ownership
* SH-997: OIDC: Prefer client_secret_post to client_secret_basic when the broker supports it

## 1.18.8 (2023-11-25)

### Gatling Enterprise

#### Fixes

* SH-996: Run duration could be missing when trying to fix ongoing runs on boot

## 1.18.7 (2023-09-25)

### Gatling Enterprise

#### Fixes

* SH-988: Azure: Fix classloading crash

## 1.18.6 (2023-08-10)

### Gatling Enterprise

#### Fixes

* SH-984: Load Generators: Remove unexpected cats-effect library that can clash with user defined version

## 1.18.5 (2023-05-11)

### Gatling Enterprise

### Features

* SH-980: Kubernetes: Introduce `frontline.injector.kubernetes.ignoreDefaultKubeConfig` option (default `true`) to be able to honor the default kubeconfig file

#### Fixes

* SH-979: AWS/Azure/GCP/Kubernetes: Fix regression introduced by SH-977 in 1.18.4

## 1.18.4 (2023-05-10)

### Gatling 3.9.4 and 3.9.5

Please check the full release note [for 3.9.4](https://github.com/gatling/gatling/milestone/115?closed=1) and [for 3.9.5](https://github.com/gatling/gatling/milestone/116?closed=1) for more details.

### Gatling Enterprise

#### Features

* SH-201: On premises: Block adding a username in the hostname
* SH-975: AWS: Enforce IDMSv2 on-demand AWS load generators
* SH-977: AWS/Azure/GCP/Kubernetes: Let users define a JAVA_HOME env var for the load generators
* SH-971: Users: Trim username

#### Fixes

* SH-972: Authentication: Store default permission on first OIDC connection

## 1.18.3 (2023-04-03)

{{< alert warning >}}
If you run the Gatling Enterprise server in Docker (including Kubernetes) with the default user: you will need to change the owner of your private key files. This is because the default user for the `gatlingcorp/frontline` container is no longer the root user (it now defaults to the `uid:gid` of `1001:0`).

You have nothing to do if:

- you already explicitly override the user (e.g. by using the `user` key in Docker Compose, or the `runAsUser` directive in Kubernetes)
- you run Gatling Enterprise on OpenShift with the default user settings (OpenShift already overrides the user by default)

Otherwise, you need to change the ownership of:

- your private key files, mounted to `/opt/frontline/keys` in the container
- your configuration files, mounted to `/opt/frontline/conf` in the container
- any other files that Gatling Enterprise needs write access to

```bash
chown -R 1001:0 <list of files or folders>
```

As the main user is no longer `root`, some other mount paths might need to be changed too. For example, AWS credentials previously mounted to `/root/.aws` should now be mounted to `/opt/frontline/.aws`.
{{< /alert >}}

### Gatling 3.9.3

Please check the [full release](https://github.com/gatling/gatling/milestone/115?closed=1) note for more details.

### Gatling Enterprise

#### Features

* SH-961: AWS/Azure/GCP/Docker: Introduce "latest" certified images with the latest to date Java version (20 as of now)
* SH-969: Docker: Introduce certified images for ARM

#### Fixes

* SH-956: Authentication: Invalid cookies shouldn't spam logs 
* SH-957: Users: Team admins should be able to remove users from their team
* SH-958: Maven Repositories: Concurrent downloads crash
* SH-959: Users: It should be possible to save users without any permissions
* SH-965: AWS: Form should not filter out EIP currently assigned
* SH-966: API: `extraEnvironmentVariables` parameter was ignored when launching a run
* SH-967: Global System Properties: Remove `-XX:-UseBiasedLocking` from defaults as this option is no longer supported since Java 19

## 1.18.2 (2023-02-22)

### Gatling 3.9.2

Please check the [full release](https://github.com/gatling/gatling/milestone/114?closed=1) note for more details.

### Gatling Enterprise

#### Fixes

* SH-944: Reports: Fix subgroups in groups menus
* SH-945: Reports: Don't modify case when displaying Simulation and Request names
* SH-948: Core: Restore hanging runs being passed to broken status on Gatling Enterprise reboot

## 1.18.1 (2023-02-15)

### Gatling 3.9.1

Please check the [full release](https://github.com/gatling/gatling/milestone/111?closed=1) note for more details.

### Gatling Enterprise

#### Features

* SH-936: Lift limitation on LTS Java versions on load generators (ie custom images can use Java 18+)

#### Fixes

* SH-846: PDF Exports: Multiple fixes
* SH-937: MQTT: Fix ActorNameException
* SH-938: Reports: Selected percentiles mask is not applied on summaries
* SH-940: Kubernetes: Fix disabling Hostname verification with k8s API server
* SH-942: PDF Exports: Fix missing charts
* SH-943: Kubernetes: Ingresses must be deleted after a run

## 1.18.0 (2022-12-15)

#### Gatling 3.9.0

{{< alert info >}}
Gatling Enterprise 1.18.0 is compatible with Gatling from 3.3 to 3.9 included.
Still, you're recommended to upgrade to 3.9.0, older versions are no longer maintained.
{{< /alert >}}

See full release note: https://github.com/gatling/gatling/milestone/112?closed=1

### Gatling Enterprise

#### Features

* FL-1106: AWS: Add support for c6i and c7g instance types
* FL-1110: AWS: Support Ed25519 SSH keys
* FL-1118: Core: Support running on Java 17
* FL-1120: Cassandra: Support DataStax DSE and AstraDB
* FL-1124: Docker: Image upgraded to Java 17
* FL-1128: Repositories: Add region field for S3 buckets
* CLD-3123: Docker: Upgrade docker containers to java 17.0.5
* CLD-3210: Kubernetes: Add support for tolerations
* CLD-3151: Kubernetes: Add support for annotations
* CLD-3152: Kubernetes: Add support for containers env vars
* CLD-3157: Kubernetes: Drop NodePort mode 
* CLD-3156: Kubernetes: When the controller and the load generators are in the same cluster, directly connect though a Service and never through an Ingress
* FL-1139: Kubernetes: Add support for ingressClassName

#### Fixes

* FL-1105: Reports: Pie chart legend is only drawn on the right side of the chart
* FL-1100: Reports: Group cumulated response time titles overflows out of their chart headers
* FL-1100: Reports: Use ellipsis for truncating Distribution title if needed
* FL-1111: Reports: Selecting group doesn't close the dropdown
* FL-1114: Reports: Fix bucket missing on Distribution chart
* FL-1119: Run Logs: New lines are not displayed

## 1.17.4 (2022-09-15)

### Gatling Enterprise

#### Fixes

* FL-1101: Pipelines: Add a delay between scp upload retries

## 1.17.3 (2022-09-14)

### Gatling 3.8.4

Please check the [full release note](https://github.com/gatling/gatling/milestone/110?closed=1) for more details.

### Gatling Enterprise

#### Fixes

* FL-1063: Pools: Support wildcards for non-proxy hosts in configuration
* FL-1067: Pipeline: Exceptions happening during scenarios loading are not properly trapped and reported early, eg invalid zipped feeder file
* FL-1071: Pipeline: Forcefully disassociate EIPs after run completes
* FL-1072: Pipeline: With Gatling 3.8, grand-children scenarios (using andThen)' stats are not collected
* FL-1079: MQTT: Subscribe timeout crashes Gatling Enterprise with "No key for scenarioPath"
* FL-1082: Run logs: Stop autoscroll when user has scrolled
* FL-1083: Users: Don't allow sending global system admin role when you're not a global system admin
* FL-1085: PDF Exports: Improve legend on "Errors counts" chart
* FL-1088: PDF Exports: * is missing from Connections and DNS menus when multiple entries
* FL-1089: Users: System admin scoped on a team can't update his own team
* FL-1092: Users: A system admin scoped on a team shouldn't be able to delete users
* FL-1096: Pipeline: Incorrect injector start timeout, can cause failures when all the injectors don't take the same time to boot

## 1.17.2 (2022-08-03)

#### Gatling 3.8.3

Please check the [full release note](https://github.com/gatling/gatling/milestone/109?closed=1) for more details.

### Gatling Enterprise

#### Fixes

* FL-1049, FL-1052: Simulation: Fix System Properties values when they contain special characters
* FL-1053: Azure MarketPlace: Fix Azure MarketPlace offers after Azure certificates change
* FL-1055: Azure Pool: Fix managed identity configuration
* FL-1058: AWS Pool: Fix HTTP Proxy usage
* FL-1059: Simulation: safe env var keys and correct value escaping
* FL-1060: Azure Pool: Fix editing when Managed Identity is selected

## 1.17.1 (2022-07-11)

### Gatling 3.8.2

Please check the [full release note](https://github.com/gatling/gatling/milestone/108?closed=1) for more details.

## 1.17.0 (2022-07-06)

### Gatling 3.8.0

Please check the [full release note](https://github.com/gatling/gatling/milestone/106?closed=1) for more details.

### Gatling Enterprise

#### Features

* FL-1015: Team: in the list of users belonging to a team, mention the role they have (team-specific role only, not global one)
* FL-1016: Openshift: Make custom cert not mandatory when configuring a route, fallback to the default JVM cert
* FL-1021: Ops: Add `frontline.http.maxRequestSize` option in `frontline.conf` to allow configuring the max request size
* FL-1022: PDF Export: Preferences are available in the NavBar

#### Fixes

* FL-80: PDF Export: Non US-ASCII chars get mangled
* FL-152: PDF Export: Variable legend unreadable in PDF if too many values / too long values
* FL-924: PDF Export: Summary doesn't work correctly with selected percentiles
* FL-992: PDF Export: New page blocks saving a template
* FL-993: Reports: Unable to change remote metric back to `*`
* FL-1009: GCE: IndexOutOfBoundsException when configure GCE pools to use static IPs while none is available
* FL-1011: Repositories & Pools: Team admins can't select global private keys
* FL-1017: Run comparison: Column sort doesn't work
* FL-1018: Runs: Run logs so autoscroll to the bottom when the run is terminated
* FL-1019: Cassandra: Add some retry in case of request failures to cope with temporary Cassandra freezes
* FL-1020: Reports: Group selection switches back when page is refreshing while the run is ongoing
* FL-1029: Users: Global System Admins couldn't reset the password of users who didn't have a global role
* FL-1036: Reports: Memory unit should be MB, not Mb
* FL-1039: Openshift: Fix `edge` termination when connecting to injectors through a secured route

## 1.16.6 (2022-04-29)

### Gatling Enterprise

#### Fixes

* FL-1012: Kubernetes Pool: Fix custom image validation

## 1.16.5 (2022-04-28)

### Gatling Enterprise

#### Fixes

* FL-922: PDF Export: Fix scn, group, request dropdown appearing on graph without those metrics
* FL-950: Reports: Log scale skips steps and doesn't show graduation
* FL-956: GCE Pool: Fix NPE when injectors don't have AccessConfigs (no public IPs)
* FL-959: GCE Pool: Fix exception when instance group is not created yet when trying to list
* FL-963: Teams: Crop names when they're too long
* FL-965: Public API: Fix parameter 'request' in the 'series' API not working as intended
* FL-971: Pipeline: Fix memory leak, when stats are being re-sent because of a lag
* FL-976: Pipeline: Fix SSH creation folder step failed if folder already exist on retry
* FL-977: SBT Build: Use -batch command instead of --batch in simulation SBT command
* FL-984: Run snapshot: Fix simulation classname overflow
* FL-985: Scroll at top when changing page
* FL-999: Reports: Fix NoSuchElementException when group doesn't exist on a given time window
* FL-996: Handle silently ChannelClosedException on API

#### Features

* FL-948: Save repository and build information in run snapshot
* FL-955: Remove public key field in GCP pool form (derived from private key)
* FL-962: Add run ID in simulations as system prop (`gatling.enterprise.runId`)
* FL-980: Add run trigger in simulations as system props (`gatling.enterprise.trigger.type` being either user or token, and `gatling.enterprise.trigger.name`)
* FL-987: Support passing env vars to injectors
* FL-989: Add support for maven wrapper in simulation build step
* FL-897: Add control on custom AMI ids: must start with "ami-"
* FL-1003: Add a way to disable superAdmin in configuration
* FL-1004: Kubernetes Pool: Allow configuring node selector
* FL-1007: Kubernetes Pool: Allow configuring custom labels
* FL-1008: Rename System Properties into Java system properties

## 1.16.4 (2022-03-03)

### Gatling 3.7.6

Please check the [full release note](https://github.com/gatling/gatling/milestone/104?closed=1) for more details.

### Gatling Enterprise

#### Fixes

* FL-783: Repositories: Downloading an artifact from a Binary Repository actually downloads all the dependencies
* FL-921: Trends: Wrong values for the means of response time percentiles
* FL-937: Simulations: Fix StringIndexOutOfBoundsException when Simulation name ends with a dot
* FL-941: Repositories: Team names are cropped very small in select input
* FL-944: Teams & API Tokens: names in table are cropped too small


## 1.16.3 (2022-02-15)

### Gatling 3.7.5

Please check the [full release note](https://github.com/gatling/gatling/milestone/105?closed=1) for more details.

### Gatling Enterprise

#### Features

* FL-949: Cassandra: modify some queries to set ALLOW FILTERING and hopefully work on ScyllaDB (not officially suppported though)

#### Fixes

* FL-951: GCE Pool: Fix NPE when using the host's service account instead of a JSON file

## 1.16.2 (2022-01-18)

### Gatling 3.7.4

Please check the [full release note](https://github.com/gatling/gatling/milestone/103?closed=1) for more details.

### Gatling Enterprise

#### Features

* FL-913: Reports: Lock summaries column headers when scrolling down
* FL-915: Pipeline: split waiting for injectors to listen over HTTP and to instantiate the Simulation in 2 distinct steps with distinct timeouts

#### Fixes

* FL-918: Pipeline: Stop waiting for missing node stats when node has actually finished earlier than the other nodes

## 1.16.1 (2021-12-20)

### Gatling 3.7.3

Please check the [full release note](https://github.com/gatling/gatling/milestone/102?closed=1) for more details.

### Gatling Enterprise

#### Fixes

* FL-907: Injectors: Cope with MemoryUsage JDK bug, see https://bugs.openjdk.java.net/browse/JDK-8207200
* FL-910: Public API: request and groups values are swapped in /run

## 1.16.0 (2021-11-24)

#### Gatling 3.7.1

{{< alert info >}}
Gatling Enterprise 1.16.0 is compatible with Gatling 3.3, 3.4, 3.5, 3.6 and 3.7.
Still, you're recommended to upgrade to 3.7.1, older versions are no longer maintained.
{{< /alert >}}

{{< alert warning >}}
Beware that the "frontline" maven, gradle and sbt plugins are [now deprecated]({{< ref "../highlight-1-16.md#switch-to-oss-build-plugins" >}}).
{{< /alert >}}

This release is mostly about new features, in particular the new Java DSL.
Please check the [full release note](https://github.com/gatling/gatling/milestone/100?closed=1) for more details.

### Gatling Enterprise

#### Features

* FL-91: Injectors: Support running injectors on ARM + support ARM based instance types on AWS
* FL-861: AWS/GCE/Azure/Kubernetes: Add certified images for Java 17
* FL-837: Deprecate "frontline" maven/gradle/sbt plugins as all the features are moved to the standard OSS plugins, drop Enterprise-specific samples and add pointers to the standard ones

#### Fixes

* FL-826: Login: Fix login button that stayed disabled when auto-filled on Firefox
* FL-839: OpenAPI: Make run status a documented enum
* FL-851: Migrations: increase timeout on schema updates to 1 minute
* FL-869: Reports: Fix established stats in TCP Connections Events per Second chart
* FL-866: Reports: Fix Response Time Percentiles chart y-axis legend
* FL-876: Azure: Rename some form fields to match the updated Azure wording
* FL-874: AWS/GCE/Azure: Improve wording on Private IP preference field
* FL-875: Azure: new pool option to not allocate a public IP address
* FL-863: Injectors: report an error when the user configures an invalid JVM option that causes the JVM to crash on boot

## 1.15.3 (2021-09-02)

#### Fixes

* FL-841: Run: Can't delete a run
* FL-844: Simulation: Can't duplicate a simulation

## 1.15.2 (2021-08-31)

#### Fixes

* FL-825: Dashboard: "Abort run" is now labelled "Stop run"
* FL-836: Boot: flush all responses before rebooting the API after first configuration

## 1.15.1 (2021-08-26)

#### Fixes

* FL-831: Grafana: Improve documentation wrt unsigned plugins
* FL-833: Kubernetes/OpenShift: pod creation crash due to invalid character because of the "Gatling Enterprise" renaming

## 1.15.0 (2021-08-19)

{{< alert info >}}
FrontLine has been renamed to Gatling Enterprise.
{{< /alert >}}

{{< alert info >}}
The official documentation is now hosted [here](https://gatling.io/docs/enterprise/self-hosted/reference/current/user/login/).
{{< /alert >}}

#### Features

* FL-740: The tables have been reshaped
* FL-774: Gradle: new gradlew built-in
* FL-788: All Pools: Harden resources destruction
* FL-806: Reports: page has been reshaped
* FL-814: New public API to retrieve the license details and limits: /license

#### Fixes

* FL-660: Reports: Improved label color generation
* FL-730: API now responds a correct 401 status when the cookie has expired
* FL-743: Simulation: harden FQCN validation
* FL-745: API should handle JSON serialization gracefully without spamming error logs
* FL-754: Kubernetes/Openshift: fix runs crashing when pods don't start fast enough
* FL-756: Fix wrong deprecation warning when using Gatling 3.6
* FL-775: Azure: Renamed Client ID into Application ID, as this is the new correct term
* FL-784: AWS: wrong IP listed in Elastic IP list
* FL-785: Azure: Create instances with one single batch per pool
* FL-790: Elastic IPs: Private IPs are listed in the injectors tab instead of the used Elastic IPs
* FL-795: Public API Swagger: Fix typo in the /runs endpoint
* FL-809: Git Repository: Fix impossibility to switch from http repository with credentials to ssh
* FL-818: GCE: Fix some issue with deprecated Google libs
* FL-820: Azure: Improve error reporting
* FL-823: AWS: No error message when the profile is incorrect
* FL-824: Global Properties: Can't set the JVM Options to nothing

## 1.14.3 (2021-07-06)

### Gatling 3.6.1

Please check the full release note: https://github.com/gatling/gatling/milestone/97?closed=1

### Gatling Enterprise

#### Fixes

* FL-693: Dashboard: Fix chart label with same color 
* FL-695: Dashboard: Fix modal percentile overflow 
* FL-696: API Tokens: Reload API Token table when an API Token is created 
* FL-698: Dashboard: Fix highlight in tooltip 
* FL-700: Simulation: Don't truncate response payloads with non US-ASCII chars
* FL-706: Dashboard: Fix crash on pie and bar chart 
* FL-708: Dashboard: Labelled component crash while updating the labels props 
* FL-710: Dashboard: Trim errors messages so tooltips don't grow too large
* FL-716: Dashboard: Fix DNS charts' colors
* FL-719: Pipeline: Compute `startTimeout`'s default value based on configured `waitHttpTries` 
* FL-753: Kubernetes/OpenShift: Retry on HTTP connect when failure is actually that the service behind the ingress/route is not ready yet
* MISC-89: CI: Make script display a specific error if the Gatling Enterprise url is malformed and ends with a `/`

## 1.14.2 (2021-06-03)

### Gatling Enterprise

#### Fixes

* FL-678: Compare runs button should be disabled when there is only 1 run
* FL-681: Fix Cassandra configuration backward compatibility

## 1.14.1 (2021-05-28)

### Gatling Enterprise

#### Fixes

* FL-668: Crash on boot when using OIDC or LDAP

## 1.14.0 (2021-05-20)

### Gatling 3.6.0

{{< alert info >}}
Gatling Enterprise 1.14.0 is compatible with Gatling 3.3, 3.4, 3.5 and 3.6.
Still, you're recommended to upgrade to 3.6.0, older versions are no longer maintained.
{{< /alert >}}

This release ships lots of bug fixes, in particular on HTTP/2 support and async DNS resolution.
Most noticeable new feature is [Brotli](https://en.wikipedia.org/wiki/Brotli) support.

See full release note: https://github.com/gatling/gatling/milestone/98?closed=1

### Gatling Enterprise

{{< alert info >}}
This release doesn't perform any new database automatic migration if you're upgrading from 1.13.1 or above.
{{< /alert >}}

#### Features

* FL-20: Pipeline: Immediately fail test when a simulation crashes on instantiation instead of retrying HTTP connection
* FL-396: Repository: Gatling zip bundle now ships a script to generate uploadable artifacts (eg in an S3 bucket repository)
* FL-474: Pools: Add nonProxyHosts option for HTTP proxy configuration
* FL-534: AWS: subnets are no multivalued and retried randomly if deploying the pool fails for insufficient capacity
* FL-589: Cassandra: Expose full Cassandra Java Driver configuration with Typesafe config (eg configuring TLS)

#### Fixes

* FL-19: Public API: Fix `abortAll` API permissions
* FL-192: Reports: Tooltip should pass over navbar
* FL-287: AWS: Only display enabled regions
* FL-346: Simulation: Fix decoding failure when passing undefined rampUp and rampDown
* FL-449: Logout: CORS error on second logout
* FL-457: Logging: Clean up scheduler debug logs
* FL-476: Private Keys: Error when trying to delete a private key
* FL-486: Reports: Multiple highlights don't clear when leaving graph
* FL-536: Azure: Filter out non suited instance types
* FL-541: Migrations: M00045_GitAndLocalSimulations doesn't support old BitBucket SSH urls
* FL-552: Simulation: Double quotes are not escaped in System Props
* FL-558: Reports: Abscissa is broken on distribution charts
* FL-564: Pools: Select all shortcut (ctrl+a) on a multiple select input select add null to value
* FL-572: Reports: Last n minutes button doesn't work if you're zoomed in from the start of the run
* FL-573: Reports: Multiple highlights don't clear when leaving graph
* FL-583: Reports: Tooltip vertical position is off when scrolling down
* FL-592: Reports: Changing Group in dropdown doesn't update
* FL-595: Reports: Changing scenario unselect group* and selects the first real group instead
* FL-608: Reports: Navigator header is broken on small screen
* FL-609: Trends: Run comparison shouldn't try to display runs that are currently injecting.
* FL-613: Reports: Can't go to groups tab with group * if group none exists
* FL-616: Reports: JavaScript error when changing the OK/KO/All selection ona chart without data
* FL-619: Pipeline: Retry uploads on scp connection loss
* FL-627: Public API: Fix `abortAll` API
* FL-630: Reports: Disable highlights refresh when test is running
* FL-631: Reports: Request menu gets emptied with only * when changing scenario with only group none
* FL-635: Boot: On First time configuration, Gatling Enterprise shows Cassandra error page instead of "Waiting for Gatling Enterprise to restart"
* FL-645: Reports:  TCP Connection states stats are missing when network is IPv6 while target is IPv4
* FL-647: Private Keys: Invalid error message mentioning "Certificate" instead of "Private Key"
* FL-651: Private Keys: File system private keys path does not update

## 1.13.4 (2021-03-18)

### Gatling Enterprise

{{< alert warning >}}
[JFrog is terminating jCenter and Bintray services.](https://jfrog.com/blog/into-the-sunset-bintray-jcenter-gocenter-and-chartcenter/)
As a consequence, sbt users have to upgrade to dependencies and plugins versions hosted on maven central.
sbt users are **strongly** advised to upgrade to `sbt 1.4.9`, `gatling-sbt 3.2.2` and `sbt-frontline 1.3.2`.

{{< /alert >}}

{{< alert warning >}}
gradle users are **strongly** advised to upgrade to `frontline-gradle-plugin 1.3.4`.
{{< /alert >}}

#### Fixes

* FL-404: UI: link in the Error page to the Service Desk is sometimes wrong
* FL-406: Repositories: Test admin cannot create a S3 repository
* FL-450: Reports: top navigator go hover side navigator
* FL-459: Reports: wrong hidden Group* entry in Groups tab's select field
* FL-462: AWS: save button is enabled while there's no private key
* FL-463: Pools: warn when there is no private key configured
* FL-464: Reports: UI shouldn't stats when select is empty
* FL-471: Reports: going from requests to groups tab with no group shows an empty graph
* FL-480: Users: can't save when editing from scoped user to system admin
* FL-481: frontline-gradle-plugin: random crashes
* FL-492: GCE: updating an existing pool is broken
* FL-500: GCE: trying to connect too fast over SSH can result in "access denied" failure
* FL-501: Azure: force usage of Premium SSD with a minimum size of 256GB when running certified images
* FL-505: Trends: no toastr error on run delete failure
* FL-507: Azure: display correct exception on SDK crash
* FL-508: frontline-gradle-plugin: transitive dependencies are not filtered out
* FL-511: frontline-gradle-plugin: runtime dependencies are not included
* FL-516: Azure: crash on tests with multiple pools because ResourceGroup names are not unique
* FL-523: sbt: Upgrade sbt sample and plugins to cope with jCenter and Bintray incoming termination
* FL-526: Private Keys: superAdmin doesn't have access to private keys page

## 1.13.3 (2021-02-24)

### Gatling Enterprise

#### Fixes

* FL-390: Runs: No data displayed + broken css after deleting runs and current page no longer exists
* FL-393: Runs: Delete option doesn't disappear after deleting a run so no run is selected
* FL-394: Runs: Trends are not refreshed after deleting a run
* FL-395: Runs: Page number go over the Navbar
* FL-397: Runs: Redux store misuse after deleting simulations
* FL-399: Security: Tampered payload can be used to take control of an entity of another team
* FL-427: API Token: Name is not modified on update
* FL-430: Pipeline doesn't delay deployment retries, causing deploying issues in particular on AWS Virginia
* FL-434: Simulations: Sort by team does not work

## 1.13.2 (2021-02-01)

{{< alert warning >}}
As announced for several months, we've finally turned down the `http://repository.gatling.io` maven repository.
Users have to download Gatling Enterprise components from `https://downloads.gatling.io`.
{{< /alert >}}

### Gatling 3.5.1

See full release note: https://github.com/gatling/gatling/milestone/96?closed=1

### Gatling Enterprise

#### Fixes

* FL-348: Dashboard: Hovering on requests and responses counts induces redux state mutation
* FL-349: Public link permissions flawed
* FL-350: CleanUpService scheduler does not execute cleanup
* FL-351: Doc: Installation guide chapter 7 is missing
* FL-357: Cassandra: Don't require keyspace create permission once schema is created (so users can restrict permissions)
* FL-365: CI Plugins: retry requests before considering a build is failed/unstable to cope with temporary failures
* FL-383: maven/gradle/sbt Packager Plugins: forcefully filter out netty-all fatjar
* FL-384: maven/gradle/sbt Packager Plugins: filter out META-INF/versions/ (broken Selenium 3 jars)
* FL-385: Cassandra: Migration 96 crashes when remote name doesn't contain an IP address
* FL-387: Runs History: pagination breaks when going to last page
* FL-391: Installer: should download Gatling Enterprise bundle from downloads.repository.io instead of repository.gatling.io

## 1.13.1 (2020-12-24)

#### Fixes

* FL-344: Summary: Request summary page blinks every 5 seconds
* FL-345: Team Test admins can no longer use global resources (pools, repositories), only resources belonging to their own team
* FL-347: Assertions stopped working

## 1.13.0 (2020-12-16)

### Gatling 3.5.0

See full release note: https://github.com/gatling/gatling/milestone/94?closed=1

### Gatling Enterprise

#### Features

* FL-13: Reports: Limit the number of errors message to avoid flooding
* FL-22: Reports: Limit the number of (scenario, group, request) to avoid flooding
* FL-36: Reports: Duration stats are now aggregated by end timestamp
* FL-86: Reports: Limit the number of remote addresses to avoid flooding

#### Fixes

* FL-12: Runs: Introduce paging on run history to avoid flooding when history is huge
* FL-14: Simulation: delete System prop button should be visible when there is only one defined
* FL-153: Forms: Click on a label should select its associated input
* FL-208: Runs: DNS resolution counts are not deleted when deleting a run
* FL-252: Security: Only global admins should be able to update simulation quotas
* FL-293: AWS: AMI toggle is always certified when editing
* FL-323: Repositories: Can't use a https repo without credentials
* FL-332: Runs: Fix possible desynchronization between injectors and Gatling Enterprise

## 1.12.5 (2020-12-04)

### Gatling Enterprise

#### Fixes

* FL-289: Misc: Run clean up crashes on date formatting
* FL-298: Pipeline: Kill signal_name should not be SIG prefixed
* FL-296: Azure: Update Azure certificates verification on Marketplace init

## 1.12.4 (2020-11-24)

### Gatling 3.4.2

See full release note: https://github.com/gatling/gatling/milestone/95?closed=1

### Gatling Enterprise

#### Features

* FL-231: App: Revisited logging. Existing users are advised to add `<logger name="io.gatling.frontline" level="INFO"/>` in their `logback.xml` file.

#### Fixes

* FL-176: Repositories: Document permissions required to use S3 buckets as repositories
* FL-199: Boot: License prompt shows up again on reboot
* FL-214: Reports: Fix crash when run is ongoing and run is longer than 5 minutes (no problem once run is done)
* FL-233: sbt: remove `io.gatling.frontline` organization from sbt sample config as it removes user provided extra libraries
* FL-242: Pipeline: Fix logs when killing remote process on on-premises injectors
* FL-248: Pipeline: Fix stdout and logback conflict in injector logs retrieved on test crash
* FL-255: Reports: Received resets series missing from TCP connections chart
* FL-260: Reports: Fix stats not displayed when request names contains heading or trailing white spaces
* FL-268: Boot: Don't overwrite super admin password and security key when updating expired license key
* FL-274: Security: It shouldn't be possible a save a simulation after hitting number of simulations limit

## 1.12.3 (2020-10-28)

### Gatling Enterprise

#### Fixes

* FL-164: AWS, S3: form is not properly saved when using environment variables
* FL-175: Private Key: form can't be saved after uploading file without editing other fields
* FL-185: Boot: confusing error message when booting with an empty `frontline.conf` but schema already exists
* FL-186: Cassandra: Migration 77 still crashes on humongous databases
* FL-188: Git: url validation rejects valid AWS CodeCommit urls

## 1.12.2 (2020-10-13)

### Gatling 3.4.1

See full release note: https://github.com/gatling/gatling/milestone/93?closed=1

### Gatling Enterprise

#### Features

* FL-115: AWS: Support AMD based instance types

#### Fixes

* FL-1: Repositories: Fix sort by name
* FL-56: Private Keys: Reset file name field when closing the modal
* FL-112: Cassandra: Improve memory footprint when cleaning up orphan data during Gatling Enterprise 1.12 upgrade
* FL-113: AWS: Document required permissions for using spot instances
* FL-114: AWS: Terminate successful instances when only a part of the spot instance request is successful
* FL-199: AWS: Use retry when tagging spot instances to cope with API being async/racy
* FL-130: AWS: On-demand instances tagging should be performed in RunCreate to allow tag based control
* FL-131: AWS: Spot instance requests should be tagged to allow tag based control
* FL-132: Pipeline: Retries process retry once too much
* FL-133: Pipeline: Retries process shouldn't delay first tentative

## 1.12.1 (2020-09-29)

### Gatling Enterprise

#### Fixes

* DEV-1418: AWS: Saving a new pool with Elastic IPs fails
* DEV-1419: HTTP: Missing request path when using Gatling 3.3
* DEV-1421: HTTP: Injector crash when traffic goes through a proxy

## 1.12.0 (2020-09-24)

### Gatling 3.4.0

See full release note: https://github.com/gatling/gatling/milestone/92?closed=1

### Gatling Enterprise

#### Features

* DEV-359: GCE: Provide certified images
* DEV-553: Core: Gatling Enterprise should log on console when running inside a container or under systemd
* DEV-581: Pools: Provide a button to duplicate a pool
* DEV-585: GCE: Support static IPs
* DEV-621: Charts: Revisit mouse over in sum cards
* DEV-676: AWS: Support spot instances
* DEV-782: Security: Support OpenID Connect authentication
* DEV-806: GCE: Introduce image and instance type support and deprecate instance templates usage
* DEV-844: GCE: Support service accounts
* DEV-948: Core: Prevent updating `frontline.conf` and reset some fields automatically when Cassandra schema already exists
* DEV-870: Misc: Add mouse hovers and titles on SideNav modal
* DEV-872: Security: Revamp Gatling Enterprise roles: viewer, tester, test admin, system admin, superAdmin
* DEV-877: Security: Scope Private keys on repository or pool
* DEV-883: Security: Revoke all cookies on Gatling Enterprise reboot
* DEV-888: Core: Introduce simulation quotas on teams
* DEV-948: Core: Prevent from resetting `frontline.conf` if Cassandra schema already exists
* DEV-955: AWS: Display a message instead of disabling checkbox when there's no Elastic IP
* DEV-1055: GCE: Support preemptible instances
* DEV-1079: Pipeline: Detect incompatible Gatling version before deploying (requires the latest build plugins)
* DEV-1138: Swagger: Public API series content is not documented
* DEV-1173: Simulation: Update default global JVM options for better performance
* DEV-1184: Pools: Update all certified images to JRE 8u265 and 11.0.8
* DEV-1229: Security: Provide a tool for bulk migrating users from LDAP to OIDC
* DEV-1241: PDF Export: remove deprecated load JSON template feature. Please use the regular load from database instead. This feature was deprecated since 1.8.0.
* DEV-1289: OpenStack: Drop Keystone v2 support
* DEV-1296: Gradle: Switch project layout to src/gatling/scala (aligned with new official Gatling OSS plugin)
* DEV-1337: Pipeline: Support deploying projects compiled against Gatling 3.3 as well as Gatling 3.4
* DEV-1346: Pipeline: Increase run duration hard limit to 1 week

#### Fixes

* DEV-208: PDF Export: legend should not be displaying disabled percentiles
* DEV-209: PDF Export: Component title should be more visible
* DEV-296: PDF Export: scenarios dropdown behavior is inconsistent
* DEV-363: Users: Mandatory fields should not be red when loading form
* DEV-365: Pools: Simulation launch button should be disabled when the simulation's single pool is the Local one and it's disabled
* DEV-496: Gradle: frontLine-gradle-plugin does not properly prune dependency tree
* DEV-498: PDF Export: Don't crop summary request name when it's too long
* DEV-506: Users: Password reset button should be disabled while resetting
* DEV-592: Pools: Pages shouldn't load all pools when creating/editing a pool
* DEV-615: Public links: Fix 403 caused by useless Grafana URL request
* DEV-711: Pools & Repositories: Private keys dropdown list should be filtered according to pool or repository's owner team
* DEV-715: Cassandra: Some Network stats were not cleaned up when deleting a run
* DEV-788: Dashboard: Fix error handling not displaying all errors
* DEV-819: Private Keys: Private key still uploaded in case of a unique name conflict
* DEV-889: Kubernetes: Client uses a hardcoded HTTP port instead of the configuration option
* DEV-999: Repositories: Password shouldn't be mandatory when cloning a git repository over https
* DEV-1003: Grafana: Time window doesnt get reset when changing run
* DEV-1060: Cassandra: Trends are not deleted when deleting a run
* DEV-1083: Pipeline: Fix thread safety issue on native processes (git, mvn, etc) completion
* DEV-1085: Pipeline: Fix AWS API blocking calls from caller thread
* DEV-1086: Dashboard: Fix percentile series legends on MacOS 1440px
* DEV-1089: Pools: Fix truncated AWS instance profiles list
* DEV-1089: HTTP Server: Don't log "Connection reset by peer" exceptions
* DEV-1096: HTTP Server: Possible memory leak when response can't be written on the socket
* DEV-1113: Dashboard: Pie chart tooltip appears just under the mouse
* DEV-1120: PDF Export: page can be blank because of a race condition
* DEV-1123: Pipeline: DNS stats are not properly aggregated
* DEV-1127: Dashboard: Invalidate web cache on new release
* DEV-1131: Simulation: Cannot associate simulation with Default team
* DEV-1157: Dashboard: Invalid menu behavior with nested groups
* DEV-1158: Series API: Can't query data for single root group with child
* DEV-1171: Pipeline: Infrequent UnsatisfiedLinkError on sigar loading
* DEV-1192: Users: Crash when username contains spaces
* DEV-1193: Users: Don't persist username in lower case
* DEV-1202: Dashboard: Tooltip on bar charts is inconsistent with the one on pie charts
* DEV-1206: Dashboard: Summary stats miss right bound second stats when changing time window
* DEV-1212: Dashboard: Requests and responses counts should not be stacked
* DEV-1227: Jenkins plugin: Deserialization issue on users series
* DEV-1232: All CI plugins: Summary doesn't display nested groups
* DEV-1233: All CI plugins: Total number of users don't get displayed, only the ones for the first scenario
* DEV-1238: Pipeline: Gatling Enterprise reports JavaNotFound instead of WrongJavaVersion
* DEV-1246: Pools: Invalid message when deleting a pool fails because it's still used
* DEV-1255: Kubernetes: Connection crash when k8s API server is on HTTPS and Gatling Enterprise runs on standard Java 8
* DEV-1304: Dashboard: Runs comparison should make use of the configured time window, just like trends
* DEV-1317: Dashboard: Don't use time window if rampup + rampdown > run duration
* DEV-1321: Security: Repository list API shouldn't expose all repositories data
* DEV-1323: Security: Repository credentials should not be sent back to frontend on edition
* DEV-1350: Gatling: Feeder files are not properly sharded (off by one error)
* DEV-1352: Dashboard: Can't display dashboard when hard limit test duration is reached
* DEV-1361: Dashboard: Groups summary's button to switch to duration / cumulative response time doesn't work
* DEV-1362: HTTP Server: Don't spam server logs with "Trying to write response on a closed channel" errors
* DEV-1363: HTTP Server: Protect against channel actually closed when trying to close idle channel
* DEV-1365: HTTP Server: Close socket on response body allocation failure

## 1.11.1 (2020-05-26)

### Gatling Enterprise

#### Fixes

* DEV-997: WebSocket: unmatched inbound messages are not visible in dropdown menu and summary
* DEV-998: Repositories: don't require Git repository url to end with ".git" (eg: Azure Repos)
* DEV-1013: Azure: take secret change into account when querying the networks and sizes
* DEV-1019: Simulation: enlarge pool name dropdown to 50 chars
* DEV-1022: Pipeline: ssh connect timeout's default value should be 10 seconds, not 5
* DEV-1023: WebSocket: dashboard crashes when displaying check stats
* DEV-1031: Pipeline: crash and can't be stopped when the local repository points to a non-existing directory
* DEV-1056: Upgrade jQuery from 3.4.1 to 3.5.1, fix security vulnerability
* DEV-1061: Repositories & Kubernetes: fix invalid URL validation and allow valid chars such as `-`

## 1.11.0 (2020-04-20)

### Gatling Enterprise

{{< alert warning >}}
Runs and simulations API payloads have been modified: The field previously named `jvmProperties` has been renamed to `jvmOptions`.
*Gatling Enterprise CI plugins* have been impacted, make sure to upgrade them as well.
{{< /alert >}}

#### Features

* DEV-485: Ansible Playbook: add parameters for Cassandra and Gatling Enterprise home directories
* DEV-684: CI plugins: rename `jvmProperties` to `jvmOptions`
* DEV-731: Jenkins Plugin: use "unstable/failed tests" status when assertions are failing
* DEV-756: Azure: provide certified injector images
* DEV-854: Azure: use User Assigned Managed Identity if any
* DEV-862: Conf: document some fields in `frontline.conf` should not be modified manually
* DEV-882: Git: document we require git 2.3+
* DEV-900: Teams: display in the teams table the linked repositories / private keys / api tokens
* DEV-904: AWS: make c5n instance type available
* DEV-912: Bamboo plugin: support deployment process
* DEV-933: Conf: document options for Cassandra storage
* DEV-938: About: display license limits in "About" modal
* DEV-939: MarketPlaces: display a reminder on MarketPlace billing during initial set up
* DEV-943: sbt: recommend disabling sbt's coursier backend for now
* DEV-949: Conf: advertise secretKey criticality
* DEV-950: MarketPlaces: display support address on boot screen

#### Fixes

* DEV-468: Web: protect against 502 errors when Gatling Enterprise is behind a reverse proxy
* DEV-549: Teams: verify team permission on private key by id endpoint
* DEV-678: AWS/Docker/Azure: ship a JRE instead of a JDK on certified injector images
* DEV-801: PDF Export: selected run is not saved when saving report
* DEV-803: Trends: fix broken trends when coming back from export
* DEV-857: Don't allow git repository on http
* DEV-860: PDF Export: runs list is corrupted after loading a saved report
* DEV-861: Trends: in hover box, ko count is always the value of the first run
* DEV-863: PDF Export: first chart is empty after loading a saved report
* DEV-866: Jenkins: plugin doesn't stop when run failed to launch
* DEV-867: Private key: overwrite never triggers
* DEV-869: On-premise hosts: private key not updated if connectivity check is run
* DEV-886: Bamboo: plugin fails to install on modern Bamboo versions
* DEV-887: Bamboo/TeamCity: plugins crash at the end of the test if there was no injection
* DEV-894: PDF export: assertions are missing
* DEV-895: PDF export: request names in summary are truncated when they are too long
* DEV-897: PDF Export: missing assertions color
* DEV-898: Team: prevent team deletion if there is a linked repository
* DEV-902: Conf: fix improper JVM options hurting performance
* DEV-905: PDF Export: display assertion status
* DEV-906: Jenkins plugin: persist summary and assertions after system restart
* DEV-917: Swagger: can't send requests with parameters
* DEV-921: Pools: combo boxes in pool creation form needs to be clicked twice on Google Chrome
* DEV-922: Azure: handle gracefully managed identities errors
* DEV-925: Conf: logback.xml instructions for logging Cassandra requests are outdated
* DEV-926: Stats: fix stats engine thread safety issue
* DEV-927: Charts: make sure percentiles legends fit on a 1440px screen
* DEV-929: Core: reduce Cassandra queries concurrency
* DEV-930: Trends: don't duplicate requests in payload for trends dropdown
* DEV-931: Core: incorrect CQL batch size
* DEV-940: Trends: x axis is inverted
* DEV-941: Conf: unhelpful message when you enter a new license key when relaunching Gatling Enterprise
* DEV-944: sbt: upgrade sbt-frontline 1.1.2 with sbt coursier backend bug workaround

## 1.10.1 (2020-01-31)

### Gatling Enterprise

#### Features

* DEV-794: Update certified AWS AMI and docker images with JRE 11.0.6 and 8u242
* DEV-840: Display default git branch next to the override box

#### Fixes

* DEV-759: Don't redirect to login page when Cassandra is shut down
* DEV-774: Kubernetes NodePort Prefer Internal IP checkbox is broken
* DEV-785: Migration 45 was pretty ineffective with non-obvious git command
* DEV-786: Let users use environment defined SSH keys for cloning git repositories
* DEV-787: Check for associated private keys when deleting a team
* DEV-789: GCE user is always empty
* DEV-796: Don't close AWS pool modal when the private key doesn't match the keypair fingerprint
* DEV-805: Response time percentiles tooltip in trends only contains 0
* DEV-807: Can't switch from P12 to JSON conf in GCE pool
* DEV-810: Kubernetes Local cluster checkbox state is reversed
* DEV-811: AWS MarketPlace offer doesn't work on Hong Kong and Bahrain
* DEV-812: Public API /run messes up chars in scenarios/groups/requests
* DEV-813: Certified AMI are not deployed on Hong Kong and Bahrain
* DEV-814: Hong Kong and Bahrain are missing from AWS regions list
* DEV-825: Modals lose state on props change
* DEV-827: Can't stop run while waiting for HTTP (deployed state)
* DEV-832: In Export, different runs summaries share the same data
* DEV-833: Missing documentation that we support cloning a git tag
* DEV-834: Don't let save an Uploaded private key with selecting a file to upload
* DEV-836: Can't edit graph param in Grafana

## 1.10.0 (2019-12-18)

### Gatling Enterprise

{{< alert warning >}}
This release fixes several security issues (see DEV-726, DEV-747 and DEV-748).
Users who uploaded private keys with the Gatling Enterprise UI are highly advised to upgrade.
{{< /alert >}}

#### Features

* DEV-261: Provide links for downloading Gatling Enterprise extensions (CI plugins and Grafana datasource) from Web UI
* DEV-484: Document how to use Ansible playbook locally
* DEV-489: Distribute Gatling Enterprise extensions (CI plugins and Grafana datasource) on a public server
* DEV-672: Let managers override git repository default branch in Simulation configuration
* DEV-707: Introduce per team admin permission
* DEV-725: Log PATH env var when launching native process fails with "program not found"

#### Fixes

* DEV-719: Git repository username cannot contain '@' and ':' characters
* DEV-722: Reduce memory usage of pipeline actor's mailbox
* DEV-723: Logs shouldn't mention port 22 when using kubectl
* DEV-724: slf4j j.u.l bridge not properly installed
* DEV-726: User with manager permission can see pool metadata in the JSON payload
* DEV-732: Don't disable Cassandra metadata while performing migrations
* DEV-733: Kubernetes pools broken if accessed directly after configuring the dashboard
* DEV-734: Don't let users to delete themselves
* DEV-735: Gatling Enterprise is slow to redirect to login screen when unauthenticated
* DEV-736: LDAP users shouldn't be able to update their profile
* DEV-743: Opening Simulation model, Build tab, shouldn't trigger a request for the list of AWS regions
* DEV-747: Uploaded private keys shouldn't be stored in Cassandra, only on filesystem
* DEV-748: Uploaded private keys are visible in the JSON payload
* DEV-749: Sort Java System properties by name
* DEV-750: Multiple highlights abscissas are off when hovering timeline after zooming in
* DEV-765: Delete obsolete file when updating an uploaded private key

## 1.9.2 (2019-11-20)

### Gatling Enterprise

#### Fixes

* DEV-640: Groups counts are no longer recorded and break group assertions
* DEV-668: Azure networks should be filtered by region
* DEV-673: OpenStack metadata credentials are not encrypted
* DEV-683: Grafana datasource is broken
* DEV-685: Jenkins plugin: link to the reports in the Build sidenav disappeared (old style job only)
* DEV-686: Grafana datasource should enable "no group" drop-down option
* DEV-688: Mig 32 should update data after dropping materialized view
* DEV-689: Cannot delete private key
* DEV-691: Various Ansible playbook fixes
* DEV-692: Map /tmp on an emptyDir volume when spawning injectors on Kubernetes
* DEV-694: Public API: /series endpoint doesn't work when the metric is a percentile
* DEV-695: Grafana: Display every exception messages
* DEV-700: DigitalOcean deployment failure cause droplet to not be deleted
* DEV-701: DigitalOcean deployment failure on public IP only networks
* DEV-703: Can't edit private key
* DEV-708: Fix OpenStack credentials encryption
* DEV-709: It shouldn't be possible to associate to a git repository a private key that belongs to a different team
* DEV-710: Can't change private key team to Global
* DEV-768: Creating a new on-prem host edits last open one instead

## 1.9.1 (2019-11-07)

### Gatling 3.3.1

Gatling 3.3.1 is binary compatible with 3.3.0, so you are not required to upgrade if you're already compiling against 3.3.0.

See full release note: https://github.com/gatling/gatling/milestone/91?closed=1

### Gatling Enterprise

#### Fixes

* DEV-634: Some behavior change on the GCP API server causes a [Google Cloud Java SDK known bug](https://github.com/googleapis/google-api-java-client/issues/1060) to happen way more frequently. We're implementing a workaround.
* DEV-635: Digital Ocean modal: remove misleading mention of username being admin by default
* DEV-636: OpenStack region form field should be mono-valued
* DEV-641: Profile name should be optional when configuring an AWS S3 bucket binary repository
* DEV-642: Fix AWS S3 bucket validation
* DEV-643: Creating an S3 bucket repository requires either profile or env vars
* DEV-647: Fix default team being blocked
* DEV-648: temporary directory should be cleaned up even if injectors termination fails
* DEV-649: Saved Azure instance size is not displayed when editing
* DEV-657: Support open-ssh new format on AWS pools
* DEV-661: Prevent conflicting duplicate private key names
* DEV-663: Fix AWS S3 bucket env variables profile name
* DEV-670: S3 is regionless, remove region field

## 1.9.0 (2019-10-23)

### Gatling 3.3.0

{{< alert warning >}}
Gatling 3.3.0 is source compatible but not fully binary compatible with the 3.2 series.
In particular, `incrementUsersPerSec` and `incrementConcurrentUsers` are not compiled the same way.
As a consequence, we recommend that you upgrade Gatling version in your projects' configuration and
recompile your pre-packaged binaries. Otherwise, tests might fail with `NoSuchMethodError` on injector boot.
{{< /alert >}}

See full release note: https://github.com/gatling/gatling/milestone/90?closed=1

### Gatling Enterprise

{{< alert warning >}}
This release introduce a major change in the way sources and binaries repositories and configured so such configuration is no longer duplicated in all your simulations.
Existing simulations will be automatically migrated when updating Gatling Enterprise instance.
Please remember to make a Cassandra database backup before upgrading.
{{< /alert >}}

#### Features

* DEV-24: Simulation search now takes for team name into account
* DEV-285: AWS pool configuration now filters configuration by VPC
* DEV-350: Revamp errors chart colors
* DEV-476: Extract out of simulation and centralize source and binaries repositories configuration
* DEV-482: Split git command into multiple fields, isolate and encrypt credentials when cloning over https
* DEV-515: MQTT plugin module now has stubs in Gatling OSS and is to be used like other modules
* DEV-520: Provide public certified plug-and-play Docker images for the injectors with JDK8 and JDK11
* DEV-544: Revamp response by status chart colors
* DEV-551: Injector Kubernetes pods are now tagged with recommended labels (https://kubernetes.io/docs/concepts/overview/working-with-objects/common-labels/)
* DEV-558: Search in lists is now case-insensitive
* DEV-561: Support Routes for routing traffic to injectors when deploying on OpenShift pools
* DEV-564: Correlate groups by end date instead of start date to avoid OutOfMemoryErrors
* DEV-584: Introduce option for preferring private IP over public one when deploying on Azure, DigitalOcean and GCE pools
* DEV-588: Replace text field with a dropdown for instance profile when configuring AWS pool
* DEV-594: Don't load file in memory when downloading from S3 bucket
* DEV-597: Js error during on a live reports on Chrome
* DEV-601: Prevent a repository to be deleted if it's being used in a simulation
* DEV-602: Improve injectors' JVM JIT inlining settings for better performance
* DEV-604: Incomplete trends when navigating from the reports to the trends
* DEV-607: Prevent a private key to be deleted if it's being used in a repository
* DEV-612: HTTP request timeout one fetching preferences because of missing content-length
* DEV-617: Lock creating simulation when no repository exists
* DEV-556: Support Ingresses for routing traffic to injectors when deploying on Kubernetes pools
* DEV-560: Introduce `gatling.frontline.groupedDomains` System property to group network stats and prevent OutOfMemoryErrors when testing against large fleet of subdomains
* DEV-624: Initialize new database with a `Default` team

#### Fixes

* DEV-508: Incorrect redirect from link in CI plugins when not authenticated
* DEV-554: Change stacked charts colors when the legend is hovered
* DEV-555: Fix confusing messages about SSH when using Kubernetes pools
* DEV-557: Fix charts and legends colors not matching
* DEV-563: Fix Gatling Enterprise injectors trying to generate OSS HTML reports and crashing
* DEV-569: Don't try to compute `forAll` assertions for silent requests
* DEV-570: Fix performance issue in stats aggregation engine when running very large clusters
* DEV-571: Optimize histograms merging performance
* DEV-583: Don't require public IP for Azure, DigitalOcean and GCE pools
* DEV-590: Fix dashboard freeze when simulation didn't execute any request
* DEV-594: Fix S3 binary download memory usage and timeout
* DEV-596: Make use of all cores when processing stats from large injectors clusters
* DEV-599: Don't record a DNS resolution event when url domain is not a hostname but an IP

## 1.8.2 (2019-09-10)

### Gatling Enterprise

{{< alert warning >}}
Make sure to check your Kubernetes pools setup as memory and cpu requests and limits are now mandatory.
{{< /alert >}}

{{< alert warning >}}
Please upgrade ASAP if you're using binary repositories, see DEV-543
{{< /alert >}}

NOTE: Kubernetes users are advised to upgrade to injectors' Docker image to `gatlingcorp/frontline-injector:8u222`

#### Fixes

* DEV-455: API for checking if Gatling Enterprise is deployed on Kubernetes shouldn't require authentication
* DEV-507: Make sure time window doesn't grow when moving it
* DEV-509: Honor configured OpenStack network
* DEV-517: Fix Swagger file missing from package
* DEV-518: Can't assign pool in on-prem host modal
* DEV-519: Upgrade JDK version in injector Docker image so JVM properly runs in container
* DEV-522: Fix incorrect Kubernetes pool memory settings description
* DEV-531: Increase Kubernetes injector pod creation timeout to cope with initial Docker image download
* DEV-532: Properly delete all Kubernetes resources on injector deployment failure
* DEV-538: Saving a kubernetes pool modal should reset modal state
* DEV-539: Have more specific error message when pod creation times out
* DEV-540: Intermittent connection refused errors to Gatling Enterprise UI when deploying Gatling Enterprise and injectors in the same Kubernetes cluster
* DEV-542: Binary repository credentials can't be removed
* DEV-543: Pools and Repositories listing APIs used in Simulation configuration shouldn't return secrets

#### Features

* DEV-501: Allow preferring Kubernetes internal IP instead of external one, see DEV-534
* DEV-510: Trim Simulation Java System properties to remove unexpected white spaces
* DEV-511: Support OpenStack availability zone
* DEV-512: Make Kubernetes pool resources configuration mandatory
* DEV-528: Force a minimal number of Netty threads when running Gatling Enterprise in a container
* DEV-534: Prefer Kubernetes cluster external IP over internal one, support having Gatling Enterprise and k8s injectors in different networks

## 1.8.1 (2019-08-26)

### Gatling 3.2.1

See release note: https://github.com/gatling/gatling/milestone/89?closed=1

### Gatling Enterprise

{{< alert warning >}}
If you plan on deploying maven projects as fatjars in a maven repository, please upgrade `frontline-maven-plugin` to 1.0.3.
{{< /alert >}}

#### Fixes

* DEV-441: Editing a private key and changing its name warn about overwriting
* DEV-453: Time window in Live reports is not updated
* DEV-454: Need to click twice in Live reports for last n minutes time window to work properly
* DEV-460: Impossible to save OpenStack pool modal, as the image isn't validated
* DEV-461: Dropdown are not populated when editing OpenStack modal
* DEV-462: OpenStack support not working with Keystone v3 api
* DEV-465: Truncated Export PDF Summary when the name is too long
* DEV-466: Support custom protocols with Gatling Enterprise
* DEV-469: Improve error message when hitting license limit
* DEV-470: Document how to publish simulation fatjar into binary repository
* DEV-471: Expire Gatling Enterprise Cookie
* DEV-474: Last run cache and number increment gets polluted after setting comments on a run which is not the last one for this simulation
* DEV-483: Verify selected Kubernetes namespace exists when configuring a Kubernetes pool
* DEV-486: Mean line in trends changes area color
* DEV-487: Allows configuring an external Pod when Gatling Enterprise deployed on Kubernetes
* DEV-488: Wrong color in response time percentiles on trends
* DEV-490: Team sort in the simulations table is not working correctly
* DEV-491: Going back and forth between multiple trends makes the charts appear empty
* DEV-493: frontline-maven-plugin should attach shaded artifact so it can be deployed
* DEV-494: Properly resolve fatjar snapshots
* DEV-495: Wrong labels on binary repositories creation modal
* DEV-499: Close binary repositories modal with escape doesn't reset its state
* DEV-500: Deleting multiples binary repositories when one is used doesn't refresh table
* DEV-502: Fix public link generation error when you choose now as the expiration date
* DEV-505: Tooltip not displayed when some series are disabled

## 1.8.0.1 (2019-07-30)

### Gatling Enterprise

#### Fixes

* DEV-446: Migrate correctly simulations if they use a Git command, correct the simulations which were migrated incorrectly in 1.8.0
* DEV-447: Fix saving a simulation with a custom build command

## 1.8.0 (2019-07-23)

### Gatling 3.2.0

See release note: https://github.com/gatling/gatling/milestone/88?closed=1

### Gatling Enterprise

{{< alert warning >}}
Make sure to upgrade Gatling to 3.2.0, as version is not binary compatible with 3.1.3.
{{< /alert >}}

{{< alert warning >}}
This release ships important security improvements, so users are highly advised to upgrade. In particular, Jenkins users are recommended to switch to Credentials for storing API tokens.
{{< /alert >}}

{{< alert warning >}}
[Some deprecated features](#deprecated-features-removed)  have been removed.
{{< /alert >}}

#### Features

* DEV-27: Support downloading fatjar from JFrog Artifactory
* DEV-64: Support uploading private keys directly from the web interface
* DEV-80: Support deploying Gatling Enterprise on Docker
* DEV-88: Centralize private keys configuration
* DEV-249: Provide a way to save PDF Export templates
* DEV-394: Introduce Jenkins Credentials support for API tokens, deprecate clear text
* DEV-338: Only display AWS instance types suited for load testing
* DEV-362: Simplified Git repository cloning configuration
* DEV-373: Introduce per Jenkins Job API token settings
* DEV-379: Support downloading fatjar from Sonatype Nexus 2
* DEV-380: Support downloading fatjar from Sonatype Nexus 3
* DEV-387: Centralize binary repositories (Artifactory, Nexus 2 and 3) management
* DEV-393: Simplify Kubernetes pool configuration when Gatling Enterprise is deployed in a docker container in the same cluster
* DEV-395: Add ability to launch Gatling Enterprise on foreground
* DEV-403: Set Kubernetes CPU requests/limits as # of CPUs
* DEV-408: Remove SSH server from injectors Docker image, switch to native kubectl
* DEV-410: Introduce retry on initial Cassandra connection to handle slow Cassandra boots

#### Fixes

* DEV-33: Don't lose user preferences on logout
* DEV-86: Protect against brute force attacks when auth is in "Gatling Enterprise" mode: use Argon2 hashing
* DEV-295: Can't unzoom chart on PDF Export when "No Data to Display"
* DEV-384: Store On-demand and Azure pools secrets encrypted
* DEV-386: Protect superAdmin account against brute force attack: delay response on failure
* DEV-391: When using LDAP auth, MyProfile password field should be hidden
* DEV-394: Enable all JDK cipher suites in Jenkins, Bamboo and TeamCity plugins
* DEV-396: Long PDF Export title is cropped
* DEV-397: Web handler errors should be logged with ERROR level, not DEBUG
* DEV-402: Protect against cookie brute force attacks: delay response on failure.
* DEV-404: Protect against API token brute force attacks: use Argon2 hashing.
* DEV-405: Kubernetes pool resources configuration is not persisted
* DEV-407: Don't Crash when building a local project and the Gatling Enterprise user doesn't have permissions on this folder
* DEV-421: PDF Export summary display should display "-" instead of "-1" for undefined metrics
* DEV-423: PDF Export should honor percentiles and date/time user preferences
* DEV-424: Changing request in request summary shouldn't drop selected time window
* DEV-427: PDF Export: incorrect header columns
* DEV-428: Selecting a request from Summary when there's no group corrupts the request dropdown
* DEV-429: Adding a pin shouldn't be possible in anonymous mode
* DEV-430: Gatling Enterprise logo is not visible on the 404 page

#### Deprecated features removed

* DEV-188: Removed Public API metrics deprecated in 1.7.0 (responses Ok, responses Ko, responses by status, groupCounts Ok, groupCounts Ko), please use their alias without spaces
* DEV-188: Removed use of file frontline-api.conf, please use frontline.conf
