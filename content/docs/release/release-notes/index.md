---
title: "Release Notes"
description: "Find the detailed release notes of FrontLine"
date: 2021-04-06T16:38:41+02:00
lastmod: 2021-04-06T16:38:41+02:00
draft: false
images: []
menu:
  docs:
    parent: "release"
weight: 60010
toc: true
---

### 1.13.4 (2021-03-18)

#### FrontLine


{{< alert warning >}}
[JFrog is terminating jCenter and Bintray services.](https://jfrog.com/blog/into-the-sunset-bintray-jcenter-gocenter-and-chartcenter/)
As a consequence, sbt users have to upgrade to dependencies and plugins versions hosted on maven central.
sbt users are **strongly** recommended to upgrade to `sbt 1.4.9`, `gatling-sbt 3.2.2` and `sbt-frontline 1.3.2`.

{{< /alert >}}

{{< alert warning >}}
gradle users are **strongly** recommended to upgrade to `frontline-gradle-plugin 1.3.4`.
{{< /alert >}}

##### Fixes

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

### 1.13.3 (2021-02-24)

#### FrontLine

##### Fixes

* FL-390: Runs: No data displayed + broken css after deleting runs and current page no longer exists
* FL-393: Runs: Delete option doesn't disappear after deleting a run so no run is selected
* FL-394: Runs: Trends are not refreshed after deleting a run
* FL-395: Runs: Page number go over the Navbar
* FL-397: Runs: Redux store misusage after deleting simulations
* FL-399: Security: Tampered payload can be used to take control of an entity of another team
* FL-427: API Token: Name is not modified on update
* FL-430: Pipeline doesn't delay deployment retries, causing deploying issues in particular on AWS Virginia
* FL-434: Simulations: Sort by team does not work

### 1.13.2 (2021-02-01)

{{< alert warning >}}
As announced for several months, we've finally turned down the `http://repository.gatling.io` maven repository.
Users have to download FrontLine components from `https://downloads.gatling.io`.
{{< /alert >}}

#### Gatling 3.5.1

See full release note: https://github.com/gatling/gatling/milestone/96?closed=1

#### FrontLine

##### Fixes

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
* FL-391: Installer: should download FrontLine bundle from downloads.repository.io instead of repository.gatling.io

### 1.13.1 (2020-12-24)

##### Fixes

* FL-344: Summary: Request summary page blinks every 5 seconds
* FL-345: Team Test admins can no longer use global resources (pools, repositories), only resources belonging to their own team
* FL-347: Assertions stopped working

### 1.13.0 (2020-12-16)

#### Gatling 3.5.0

See full release note: https://github.com/gatling/gatling/milestone/94?closed=1

#### FrontLine

##### Features

* FL-13: Reports: Limit the number of errors message to avoid flooding
* FL-22: Reports: Limit the number of (scenario, group, request) to avoid flooding
* FL-36: Reports: Duration stats are now aggregated by end timestamp
* FL-86: Reports: Limit the number of remote addresses to avoid flooding

##### Fixes

* FL-12: Runs: Introduce paging on run history to avoid flooding when history is huge
* FL-14: Simulation: delete System prop button should be visible when there is only one defined
* FL-153: Forms: Click on a label should select its associated input
* FL-208: Runs: DNS resolution counts are not deleted when deleting a run
* FL-252: Security: Only global admins should be able to update simulation quotas
* FL-293: AWS: AMI toggle is always certified when editing
* FL-323: Repositories: Can't use a https repo without credentials
* FL-332: Runs: Fix possible desynchronization between injectors and FrontLine

### 1.12.5 (2020-12-04)

#### FrontLine

##### Fixes

* FL-289: Misc: Run clean up crashes on date formatting
* FL-298: Pipeline: Kill signal_name should not be SIG prefixed
* FL-296: Azure: Update Azure certificates verification on Marketplace init

### 1.12.4 (2020-11-24)

#### Gatling 3.4.2

See full release note: https://github.com/gatling/gatling/milestone/95?closed=1

#### FrontLine

##### Features

* FL-231: App: Revisited logging. Existing users are advised to add `<logger name="io.gatling.frontline" level="INFO"/>` in their `logback.xml` file.

##### Fixes

* FL-176: Repositories: Document permissions required to use S3 buckets as repositories
* FL-199: Boot: License prompt shows up again on reboot
* FL-214: Reports: Fix crash when run is ongoing and run is longer than 5 minutes (no problem once run is done)
* FL-233: sbt: remove `io.gatling.frontline` organization from sbt sample config as it removes user provided extra libraries
* FL-242: Pipeline: Fix logs when killing remote process on on-prems injectors
* FL-248: Pipeline: Fix stdout and logback conflict in injector logs retrieved on test crash
* FL-255: Reports: Received resets series missing from TCP connections chart
* FL-260: Reports: Fix stats not displayed when request names contains heading or trailing white spaces
* FL-268: Boot: Don't overwrite super admin password and security key when updating expired license key
* FL-274: Security: It shouldn't be possible a save a simulation after hitting number of simulations limit

### 1.12.3 (2020-10-28)

#### FrontLine

##### Fixes

* FL-164: AWS, S3: form is not properly saved when using environment variables
* FL-175: Private Key: form can't be saved after uploading file without editing other fields
* FL-185: Boot: confusing error message when booting with an empty frontline.conf but schema already exists
* FL-186: Cassandra: Migration 77 still crashes on humongous databases
* FL-188: Git: url validation rejects valid AWS CodeCommit urls

### 1.12.2 (2020-10-13)

#### Gatling 3.4.1

See full release note: https://github.com/gatling/gatling/milestone/93?closed=1

#### FrontLine

##### Features

* FL-115: AWS: Support AMD based instance types

##### Fixes

* FL-1: Repositories: Fix sort by name
* FL-56: Private Keys: Reset file name field when closing the modal
* FL-112: Cassandra: Improve memory footprint when cleaning up orphan data during FrontLine 1.12 upgrade
* FL-113: AWS: Document required permissions for using spot instances
* FL-114: AWS: Terminate successful instances when only a part of the spot instance request is successful
* FL-199: AWS: Use retry when tagging spot instances to cope with API being async/racy
* FL-130: AWS: On-demand instances tagging should be performed in RunCreate to allow tag based control
* FL-131: AWS: Spot instance requests should be tagged to allow tag based control
* FL-132: Pipeline: Retries process retry once too much
* FL-133: Pipeline: Retries process shouldn't delay first tentative

### 1.12.1 (2020-09-29)

#### FrontLine

##### Fixes

* DEV-1418: AWS: Saving a new pool with Elastic IPs fails
* DEV-1419: HTTP: Missing request path when using Gatling 3.3
* DEV-1421: HTTP: Injector crash when traffic goes through a proxy

### 1.12.0 (2020-09-24)

#### Gatling 3.4.0

See full release note: https://github.com/gatling/gatling/milestone/92?closed=1

#### FrontLine

##### Features

* DEV-359: GCE: Provide certified images
* DEV-553: Core: FrontLine should log on console when running inside a container or under systemd
* DEV-581: Pools: Provide a button to duplicate a pool
* DEV-585: GCE: Support static IPs
* DEV-621: Charts: Revisit mouse over in sum cards
* DEV-676: AWS: Support spot instances
* DEV-782: Security: Support OpenID Connect authentication
* DEV-806: GCE: Introduce image and instance type support and deprecate instance templates usage
* DEV-844: GCE: Support service accounts
* DEV-948: Core: Prevent updating frontline.conf and reset some fields automatically when Cassandra schema already exists
* DEV-870: Misc: Add mouse hovers and titles on SideNav modal
* DEV-872: Security: Revamp FrontLine roles: viewer, tester, test admin, system admin, superAdmin
* DEV-877: Security: Scope Private keys on repository or pool
* DEV-883: Security: Revoke all cookies on FrontLine reboot
* DEV-888: Core: Introduce simulation quotas on teams
* DEV-948: Core: Prevent from resetting frontline.conf if Cassandra schema already exists
* DEV-955: AWS: Display a message instead of disabling checkbox when there's no Elastic IP
* DEV-1055: GCE: Support preemptible instances
* DEV-1079: Pipeline: Detect incompatible Gatling version before deploying (requires latest build plugins)
* DEV-1138: Swagger: Public API series content is not documented
* DEV-1173: Simulation: Update default global JVM options for better performance
* DEV-1184: Pools: Update all certified images to JRE 8u265 and 11.0.8
* DEV-1229: Security: Provide a tool for bulk migrating users from LDAP to OIDC
* DEV-1241: PDF Export: remove deprecated load JSON template feature. Please use the regular load from database instead. This feature was deprecated since 1.8.0.
* DEV-1289: OpenStack: Drop Keystone v2 support
* DEV-1296: Gradle: Switch project layout to src/gatling/scala (aligned with new official Gatling OSS plugin)
* DEV-1337: Pipeline: Support deploying projects compiled against Gatling 3.3 as well as Gatling 3.4
* DEV-1346: Pipeline: Increase run duration hard limit to 1 week

##### Fixes

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
* DEV-1193: Users: Don't persists username in lower case
* DEV-1202: Dashboard: Tooltip on bar charts is inconsistent with the one on pie charts
* DEV-1206: Dashboard: Summary stats miss right bound second stats when changing time window
* DEV-1212: Dashboard: Requests and responses counts should not be stacked
* DEV-1227: Jenkins plugin: Deserialization issue on users series
* DEV-1232: All CI plugins: Summary doesn't display nested groups
* DEV-1233: All CI plugins: Total number of users don't get displayed, only the ones for the first scenario
* DEV-1238: Pipeline: FrontLine reports JavaNotFound instead of WrongJavaVersion
* DEV-1246: Pools: Invalid message when deleting a pool fails because it's still used
* DEV-1255: Kubernetes: Connection crash when k8s API server is on HTTPS and FrontLine runs on standard Java 8
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

### 1.11.1 (2020-05-26)

#### FrontLine

##### Fixes

* DEV-997: WebSocket: unmatched inbound messages are not visible in dropdown menu and summary
* DEV-998: Repositories: don't require Git repository url to end with ".git" (eg: Azure Repos)
* DEV-1013: Azure: take secret change into account when querying the networks and sizes
* DEV-1019: Simulation: enlarge pool name dropdown to 50 chars
* DEV-1022: Pipeline: ssh connect timeout's default value should be 10 seconds, not 5
* DEV-1023: WebSocket: dashboard crashes when displaying check stats
* DEV-1031: Pipeline: crash and can't be aborted when the local repository points to a non existing directory
* DEV-1056: Upgrade jQuery from 3.4.1 to 3.5.1, fix security vulnerability
* DEV-1061: Repositories & Kubernetes: fix invalid URL validation and allow valid chars such as `-`

### 1.11.0 (2020-04-20)

#### FrontLine

{{< alert warning >}}
Runs and simulations API payloads have been modified: The field previously named `jvmProperties` has been renamed to `jvmOptions`.
*Frontline CI plugins* have been impacted, make sure to upgrade them as well.
{{< /alert >}}

##### Features

* DEV-485: Ansible Playbook: add parameters for Cassandra and FrontLine home directories
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

##### Fixes

* DEV-468: Web: protect against 502 errors when FrontLine is behind a reverse proxy
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
* DEV-941: Conf: unhelpful message when you enter a new license key when relaunching FrontLine
* DEV-944: sbt: upgrade sbt-frontline 1.1.2 with sbt coursier backend bug workaround

### 1.10.1 (2020-01-31)

#### FrontLine

##### Features

* DEV-794: Update certified AWS AMI and docker images with JRE 11.0.6 and 8u242
* DEV-840: Display default git branch next to the override box

##### Fixes

* DEV-759: Don't redirect to login page when Cassandra is shut down
* DEV-774: Kubernetes NodePort Prefer Internal IP checkbox is broken
* DEV-785: Migration 45 was pretty ineffective with non obvious git command
* DEV-786: Let users use environment defined SSH keys for cloning git repositories
* DEV-787: Check for associated private keys when deleting a team
* DEV-789: GCE user is always empty
* DEV-796: Don't close AWS pool modal when private key doesn't match keypair fingerprint
* DEV-805: Response time percentiles tooltip in trends only contains 0
* DEV-807: Can't switch from P12 to JSON conf in GCE pool
* DEV-810: Kubernetes Local cluster checkbox state is reversed
* DEV-811: AWS MarketPlace offer doesn't work on Hong Kong and Bahrain
* DEV-812: Public API /run messes up chars in scenarios/groups/requests
* DEV-813: Certified AMI are not deployed on Hong Kong and Bahrain
* DEV-814: Hong Kong and Bahrain are missing from AWS regions list
* DEV-825: Modals lose state on props change
* DEV-827: Can't abort run while waiting for HTTP (deployed state)
* DEV-832: In Export, different runs summaries share the same data
* DEV-833: Missing documentation that we support cloning a git tag
* DEV-834: Don't let save an Uploaded private key with selecting a file to upload
* DEV-836: Can't edit graph param in Grafana

### 1.10.0 (2019-12-18)

#### FrontLine

{{< alert warning >}}
This release fixes several security issues (see DEV-726, DEV-747 and DEV-748).
Users who uploaded private keys with the FrontLine UI are highly recommended to upgrade.
{{< /alert >}}

##### Features

* DEV-261: Provide links for downloading FrontLine extensions (CI plugins and Grafana datasource) from Web UI
* DEV-484: Document how to use Ansible playbook locally
* DEV-489: Distribute FrontLine extensions (CI plugins and Grafana datasource) on a public server
* DEV-672: Let managers override git repository default branch in Simulation configuration
* DEV-707: Introduce per team admin permission
* DEV-725: Log PATH env var when launching native process fails with "program not found"

##### Fixes

* DEV-719: Git repository username cannot contain '@' and ':' characters
* DEV-722: Reduce memory usage of pipeline actor's mailbox
* DEV-723: Logs shouldn't mention port 22 when using kubectl
* DEV-724: slf4j j.u.l bridge not properly installed
* DEV-726: User with manager permission can see pool metadata in the JSON payload
* DEV-732: Don't disable Cassandra metadata while performing migrations
* DEV-733: Kubernetes pools broken if accessed directly after configuring the dashboard
* DEV-734: Don't let users to delete themselves
* DEV-735: FrontLine is slow to redirect to login screen when unauthenticated
* DEV-736: LDAP users shouldn't be able to update their profile
* DEV-743: Opening Simulation model, Build tab, shouldn't triggers a request for the list of AWS regions
* DEV-747: Uploaded private keys shouldn't be stored in Cassandra, only on filesystem
* DEV-748: Uploaded private keys are visible in the JSON payload
* DEV-749: Sort System properties by name
* DEV-750: Multiple highlights abscissas are off when hovering timeline after zooming in
* DEV-765: Delete obsolete file when updating an uploaded private key

### 1.9.2 (2019-11-20)

#### FrontLine

##### Fixes

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

### 1.9.1 (2019-11-07)

#### Gatling 3.3.1

Gatling 3.3.1 is binary compatible with 3.3.0 so you are not required to upgrade if you're already compiling against 3.3.0.

See full release note: https://github.com/gatling/gatling/milestone/91?closed=1

#### FrontLine

##### Fixes

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

### 1.9.0 (2019-10-23)

#### Gatling 3.3.0

{{< alert warning >}}
Gatling 3.3.0 is source compatible but not fully binary compatible with the 3.2 series.
In particular, `incrementUsersPerSec` and `incrementConcurrentUsers` are not compiled the same way.
As a consequence, we recommend that you upgrade Gatling version in your projects' configuration and
recompile your pre-packaged binaries. Otherwise, tests might fail with `NoSuchMethodError` on injector boot.
{{< /alert >}}

See full release note: https://github.com/gatling/gatling/milestone/90?closed=1

#### FrontLine

{{< alert warning >}}
This release introduce a major change in the way sources and binaries repositories and configured so such configuration is no longer duplicated in all your simulations.
Existing simulations will be automatically migrated when updating FrontLine instance.
Please remember to make a Cassandra database backup before upgrading.
{{< /alert >}}

##### Features

* DEV-24: Simulation search now takes for team name into account
* DEV-285: AWS pool configuration now filters configuration by VPC
* DEV-350: Revamp errors chart colors
* DEV-476: Extract out of simulation and centralize source and binaries repositories configuration
* DEV-482: Split git command into multiple fields, isolate and encrypt credentials when cloning over https
* DEV-515: MQTT plugin module now has stubs in Gatling OSS and is to be used like other modules
* DEV-520: Provide public certified plug-and-play Docker images for the injectors with JDK8 and JDK11
* DEV-544: Revamp response by status chart colors
* DEV-551: Injector Kubernetes pods are now tagged with recommended labels (https://kubernetes.io/docs/concepts/overview/working-with-objects/common-labels/)
* DEV-558: Search in lists is now case insensitive
* DEV-561: Support Routes for routing traffic to injectors when deploying on OpenShift pools
* DEV-564: Correlate groups by end date instead of start date to avoid OutOfMemoryErrors
* DEV-584: Introduce option for preferring private IP over public one when deploying on Azure, DigitalOcean and GCE pools
* DEV-588: Replace text field with a dropdown for instance profile when configuring AWS pool
* DEV-594: Don't loads file in memory when downloading from S3 bucket
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

##### Fixes

* DEV-508: Incorrect redirect from link in CI plugins when not authenticated
* DEV-554: Change stacked charts colors when the legend is hovered
* DEV-555: Fix confusing messages about SSH when using Kubernetes pools
* DEV-557: Fix charts and legends colors not matching
* DEV-563: Fix FrontLine injectors trying to generate OSS HTML reports and crashing
* DEV-569: Don't try to compute `forAll` assertions for silent requests
* DEV-570: Fix performance issue in stats aggregation engine when running very large clusters
* DEV-571: Optimize histograms merging performance
* DEV-583: Don't require public IP for Azure, DigitalOcean and GCE pools
* DEV-590: Fix dashboard freeze when simulation didn't execute any request
* DEV-594: Fix S3 binary download memory usage and timeout
* DEV-596: Make use of all cores when processing stats from large injectors clusters
* DEV-599: Don't record a DNS resolution event when url domain is not a hostname but an IP

### 1.8.2 (2019-09-10)

#### FrontLine

{{< alert warning >}}
Make sure to check your Kubernetes pools setup as memory and cpu requests and limits are now mandatory.
{{< /alert >}}

{{< alert warning >}}
Please upgrade ASAP if you're using binary repositories, see DEV-543
{{< /alert >}}

NOTE: Kubernetes users are advised to upgrade to injectors' Docker image to `gatlingcorp/frontline-injector:8u222`

##### Fixes

* DEV-455: API for checking if FrontLine is deployed on Kubernetes shouldn't require authentication
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
* DEV-540: Intermittent connection refused errors to FrontLine UI when deploying FrontLine and injectors in the same Kubernetes cluster
* DEV-542: Binary repository credentials can't be removed
* DEV-543: Pools and Repositories listing APIs used in Simulation configuration shouldn't return secrets

##### Features

* DEV-501: Allow preferring Kubernetes internal IP instead of external one, see DEV-534
* DEV-510: Trim Simulation System properties to remove unexpected white spaces
* DEV-511: Support OpenStack availability zone
* DEV-512: Make Kubernetes pool resources configuration mandatory
* DEV-528: Force a minimal number of Netty threads when running FrontLine in a container
* DEV-534: Prefer Kubernetes cluster external IP over internal one, support having FrontLine and k8s injectors in different networks

### 1.8.1 (2019-08-26)

#### FrontLine

{{< alert warning >}}
If you plan on deploying maven projects as fatjars in a maven repository, please upgrade `frontline-maven-plugin` to 1.0.3.
{{< /alert >}}

##### Fixes

* DEV-441: Editing a private key and changing its name warn about overwriting
* DEV-453: Time window in Live reports is not updated
* DEV-454: Need to click twice in Live reports for last n minutes time window to work properly
* DEV-460: Impossible to save OpenStack pool modal, as the image isn't validated
* DEV-461: Dropdown are not populated when editing OpenStack modal
* DEV-462: OpenStack support not working with Keystone v3 api
* DEV-465: Truncated Export PDF Summary when the name is too long
* DEV-466: Support custom protocols with FrontLine
* DEV-469: Improve error message when hitting license limit
* DEV-470: Document how to publish simulation fatjar into binary repository
* DEV-471: Expire FrontLine Cookie
* DEV-474: Last run cache and number increment gets polluted after setting comments on a run which is not the last one for this simulation
* DEV-483: Verify selected Kubernetes namespace exists when configuring a Kubernetes pool
* DEV-486: Mean line in trends changes area color
* DEV-487: Allows configuring an external Pod when FrontLine deployed on Kubernetes
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

#### Gatling 3.2.1

See release note: https://github.com/gatling/gatling/milestone/89?closed=1

### 1.8.0.1 (2019-07-30)

#### FrontLine

##### Fixes

* DEV-446: Migrate correctly simulations if they use a Git command, correct the simulations which were migrated incorrectly in 1.8.0
* DEV-447: Fix saving a simulation with a custom build command

### 1.8.0 (2019-07-23)

#### FrontLine

{{< alert warning >}}
Make sure to upgrade Gatling to 3.2.0, as version is not binary compatible with 3.1.3.
{{< /alert >}}

{{< alert warning >}}
This release ships important security improvements, so users are highly recommended to upgrade. In particular, Jenkins users are recommended to switch to Credentials for storing API tokens.
{{< /alert >}}

{{< alert warning >}}
<<deprecated, Some deprecated features>> have been removed.
{{< /alert >}}

##### Features

* DEV-27: Support downloading fatjar from JFrog Artifactory
* DEV-64: Support uploading private keys directly from the web interface
* DEV-80: Support deploying FrontLine on Docker
* DEV-88: Centralize private keys configuration
* DEV-249: Provide a way to save PDF Export templates
* DEV-394: Introduce Jenkins Credentials support for API tokens, deprecate clear text
* DEV-338: Only display AWS instance types suited for load testing
* DEV-362: Simplified Git repository cloning configuration
* DEV-373: Introduce per Jenkins Job API token settings
* DEV-379: Support downloading fatjar from Sonatype Nexus 2
* DEV-380: Support downloading fatjar from Sonatype Nexus 3
* DEV-387: Centralize binary repositories (Artifactory, Nexus 2 and 3) management
* DEV-393: Simplify Kubernetes pool configuration when FrontLine is deployed in a docker container in the same cluster
* DEV-395: Add ability to launch FrontLine on foreground
* DEV-403: Set Kubernetes CPU requests/limits as # of CPUs
* DEV-408: Remove SSH server from injectors Docker image, switch to native kubectl
* DEV-410: Introduce retry on initial Cassandra connection to handle slow Cassandra boots

##### Fixes

* DEV-33: Don't lose user preferences on logout
* DEV-86: Protect against brute force attacks when auth is in "FrontLine" mode: use Argon2 hashing
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
* DEV-407: Don't Crash when building a local project and FrontLine user doesn't have permissions on this folder
* DEV-421: PDF Export summary display should display "-" instead of "-1" for undefined metrics
* DEV-423: PDF Export should honor percentiles and date/time user preferences
* DEV-424: Changing request in request summary shouldn't drop selected time window
* DEV-427: PDF Export: incorrect header columns
* DEV-428: Selecting a request from Summary when there's no group corrupts the request dropdown
* DEV-429: Adding a pin shouldn't be possible in anonymous mode
* DEV-430: FrontLine logo is not visible on the 404 page

[[deprecated]]
##### Deprecated features removed

* DEV-188: Removed Public API metrics deprecated in 1.7.0 (responses Ok, responses Ko, responses by status, groupCounts Ok, groupCounts Ko), please use their alias without spaces
* DEV-188: Removed use of file frontline-api.conf, please use frontline.conf

#### Gatling 3.2.0

See release note: https://github.com/gatling/gatling/milestone/88?closed=1

### 1.7.2 (2019-06-24)

#### FrontLine

{{< alert warning >}}
Make sure to upgrade Gatling to 3.1.3, as version is not binary compatible with 3.1.2.
{{< /alert >}}

##### Fixes

* DEV-345: Unclear confirmation message when creating a user
* DEV-371: gzip large asset files
* DEV-378: Kubernetes Check API doesn't respond when the Kubernetes URL is set to http instead of https
* DEV-382: Sort UsedBy List Modal alphabetically
* DEV-385: Support only LTS (8 and 11) java versions

#### Gatling 3.1.3

See release note: https://github.com/gatling/gatling/milestone/83?closed=1

### 1.7.1 (2019-05-14)

#### FrontLine

##### Fixes

* DEV-347: Tooltip are misplaced in the public reports
* DEV-351: Sub groups are missing from group menu in Requests and groups tab
* DEV-352: Cursor is off in distribution charts after zoomong in/out
* DEV-353: Drop RunsBySimulation materialized view
* DEV-357: GCE support doesn't honor proxy config
* DEV-361: Request tab charts become empty when coming back and there's no group
* DEV-366: frontline.injector.disableKubernetesTrustManager and set the default value to true
* DEV-367: Drop restart option from frontline launch script
* DEV-369: Log injector crash data with ERROR level
* DEV-370: Ko Indicators tooltip not visible when the Sidenav is open

#### Gatling 3.1.2

See release note: https://github.com/gatling/gatling/milestone/87?closed=1

### 1.7.0 (2019-04-11)

#### FrontLine

##### Features

* DEV-12: Show which simulations use a given on premises pool
* DEV-116: Don't display disabled percentiles in the summaries
* DEV-130: Provide an installer for installing FrontLine on Amazon Linux 1
* DEV-137: Remove timewindow valid duration
* DEV-166: Reboot FrontLine without rebooting Java process
* DEV-171: When editing a simulation, indicate which step is incorrectly configured
* DEV-172: Custom tags on AWS on demand instance
* DEV-173: Make SSH and HTTP service checks timeouts configurable
* DEV-178: Display an error page with a reload button if FrontLine can't connect to Cassandra
* DEV-227: Filter Security Groups depending on Subnet
* DEV-252: Export run stats into PDF
* DEV-253: Support deploying injectors with K8S/OpenShift
* DEV-259: Compare runs global stats
* DEV-286: Display both subnet id and name in AWS Pool
* DEV-306: Force ec2-user when using our AWS certified AMIs
* DEV-339: Disable surefire in default maven build command

####### Fixes

* DEV-21: Fix Public API metrics with space char
* DEV-104: Fix AWS regions sort by name on Chrome
* DEV-125: Fix group dropdown filter
* DEV-136: Meaningful timewindow is not applied on reports
* DEV-142: When querying a run, API should returns the ramp Up/ramp Down of the run, and not of the simulation
* DEV-155: Protect against simulation double start from public API
* DEV-157: Double clicking on a graph doesn't zoom back to everything, but to start & end
* DEV-168: Single quotes are not escaped in migration scripts
* DEV-169: Migration scripts should bind parameters instead of injecting them directly in the CQL query
* DEV-177: Duplicate requests when loading DNS & Injectors tabs
* DEV-179: Incorrect group assertions
* DEV-180: Actual value is missing in assertion messages when it's 0
* DEV-181: Terminating pipeline should happen after persisting run end
* DEV-189: frontline-maven-plugin doesn't delete temporary directory after use
* DEV-193: FrontLine launch scripts blocks Java 11
* DEV-194: FrontLine fails to download S3 object when it's in a folder
* DEV-195: frontline-maven-plugin and sbt-frontline crash when classpath has both file and directory named license
* DEV-228: Don't use original public IP after assigning EIP
* DEV-257: Tag EC2 instances on creation instead of later
* DEV-265: Protect against double simulation launch
* DEV-266: Protect against non alphanum chars when filling license key in web interface
* DEV-277: Same simulation gets triggered when starting multiple simulations at the same time from same local repository
* DEV-280: Editing the team of a permission in the user modal doesn't enable the save button
* DEV-301: kill all on-premises cluster nodes on first node crash
* DEV-302: kill -9 stalled injectors if kill -1 didn't suffice
* DEV-304: Invalid homepage redirect when pressing Enter key to save a run comment
* DEV-308: Broken response time distribution linear vs log scales behavior
* DEV-312: Public Report use private summary api instead of the public one
* DEV-314: Stockholm AWS region is missing
* DEV-324: Large y axis labels in charts were truncated
* DEV-334: Fix automatic run cleanup not working
* DEV-337: Don't try to clean up uploaded resources when they couldn't be uploaded in the first place
* DEV-342: Properly flag OpenStack pools in simulation pools panel
* DEV-344: Fix tooltip position in Percentiles Distribution chart

#### Gatling 3.1.1

See release note: https://github.com/gatling/gatling/milestone/85?closed=1

### 1.6.2 (2019-01-24)

{{< alert warning >}}
CI plugins must be upgraded to 1.6.2 too.
{{< /alert >}}

#### FrontLine

##### Features

* 1598: Introduce automatic run clean up
* 1963: Sorts metrics by name in metrics public API
* DEV-117: Recommend increasing scala-maven-plugin Xss to 100M
* DEV-118: Recommend increasing gradle Xss to 100M
* DEV-119: Recommend increasing sbt Xss to 100M
* DEV-120: Make the doc more explicit about AWS marketplace lack of FrontLine repository
* DEV-121: Provide a shell script for CI solutions without plugins, eg Gitlab CI
* DEV-139: Change the /simulations/start  system props parameter from an object to an array of objects

####### Fixes

* 1962: Public API  should return Double instead of Long for count/s series
* DEV-7: Random 401 error toastr when logging in
* DEV-8: Don't display any 404 errors when unauthenticated
* DEV-9: Don't allow team members to edit run comment and launch simulation
* DEV-16: In the simulation modal, a user shouldn't be able to see teams he's not a member of
* DEV-17: In the simulations table, a user shouldn't be able to select simulations belonging to teams he's not a member of
* DEV-18: Don't hardcode Stats fetching timeout
* DEV-22: Some public API endpoints are not secured
* DEV-49: Optimize simulations page Cassandra usage
* DEV-50: Use a dedicated thread pool for blocking tasks
* DEV-100: AWS Elastic IPs are assigned based on original list and not against available ones
* DEV-102: Don't create a new Timer every time we use Futures#after
* DEV-105: AMI toggle should be disabled if the region is not selected
* DEV-122: Summaries requests in Public API fails if done just after the run is injecting
* DEV-123: Explicit dependencies shared with gatling-recorder and gatling-charts ClassNotFoundException
* DEV-124: Pipeline's run status check timer is never cancelled and cause CQL queries bombs
* DEV-131: requestsPerSec assertion doesn't work in FrontLine
* DEV-132: FrontLine assertions don't honor time window
* DEV-133: Rps is incorrect in request summary when MeaningfulTimeWindow in non empty
* DEV-140: Sigar is not properly loaded on JDK10+
* DEV-143: Js crash in AWS pool modal when a subnet is incorrect
* DEV-144: Crash when writing JUnit XML file while pipeline runs on a slave
* DEV-145: Incorrect Jenkins pipeline syntax in documentation samples
* DEV-147: NPE when Jenkins plugin hits an IOException without a cause
* DEV-149: CI plugins don't cancel timer when run is aborted
* DEV-150: CI plugins send incorrect from in metrics query
* DEV-151: CI plugins should mention concurrent users, not active users
* DEV-152: Rename last dashboard occurrences into reports
* DEV-153: Request body JSON parsing failure causes response to never be sent
* DEV-154: Summary is empty if we select everything while run is injecting
* DEV-156: Can't edit team from specific to global in on-premises pool modal
* DEV-158: OpenStack pool table js crash when non-empty
* DEV-159: LDAP search user validation crashes on some LDAP implementations
* DEV-162: Use new /simulations/start endpoint in Jenkins plugin
* DEV-163: Public reports don't work in the presence of comments
* DEV-164: js error when clicking in Summary and there's no group
* DEV-165: Delete sample tmp file on JVM exit

#### Gatling 3.0.3

See release note: https://github.com/gatling/gatling/milestone/84?closed=1

### 1.6.1 (2018-12-20)

#### FrontLine

##### Features

* 1915: Sort dropdown lists alphabetically
* 1924: More user friendly error on malformed frontline.conf file
* 1933: In create simulation modal, only highlight until current step
* 1950: Document that we don't support password-protected key pairs
* 1957: Clean up JVM parameters and increase default max heap size

####### Fixes

* 1909: Don't let user delete a pool as long as it's being used by a simulation
* 1910: Updating profile (first name, last name, mail address) doesn't update local storage
* 1911: Double totals should be rounded up instead of displaying very large number of decimals
* 1912: Simulations table crash when adding a simulation with a newly created team
* 1913: When creating on-premises host, use config option for ssh connect timeout instead of hard coded value
* 1914: Serve FrontLine documentation from gatling.io website
* 1916: Wrong chart width init when run is ongoing with greater resolution
* 1921: Remove the 401 - Unauthorized toaster when landing on the login page
* 1923: Grafana/Public API: Invalid teamId and id serialization
* 1927: Crash when setting up an invalid license key
* 1934: Impossible to edit existing simulations once license limit is reached
* 1938: In setup mode, setting an invalid license displays it's correct
* 1939: JavaScript error when clicking on browser back/previous when on the ShowListModal
* 1940: Fix ShowListModal icons order and plural
* 1943: Stop checking private IP in AWS Marketplace mode as it can throw an exception
* 1949: JavaScript error after deleting all the simulations
* 1951: NPE when saving an AWS Pool with a public key instead of a private one
* 1952: Don't hardcode displayed frontline.conf path as it might not be /opt/frontline for on-prem customers
* 1953: No error toastr when configuring an invalid license in the web interface
* 1955: Incorrect AWS JSON permissions documentation
* 1958: Bug displaying second on tooltip
* 1959: Display proper error message when entering an expired License key
* 1961: Fix Simulation modal save not working when no pool is defined

#### Gatling 3.0.2

See release note: https://github.com/gatling/gatling/milestone/81?closed=1

### 1.6.0 (2018-11-22)

#### FrontLine

{{< alert warning >}}
Beware of the breaking change introduced in \#1844 in the public REST API:
{{< /alert >}}

* 1844: The params start & end in the /series endpoint are renamed to from & to, defaulting to the start & end of the injection. The from & to params in the /summaries/requests endpoint are now timestamps (they were previously an offset compared to the start of the injection), defaulting to the start & end of the injection.

##### Features

* 1438: Provide downloadable maven, gradle and sbt samples
* 1483: Display related simulations when deleting a pool
* 1599: Pass parameters from Jenkins to simulation
* 1651: Add buttons to copy TeamId, SimulationId and RunId into clipboard
* 1698: Support running FrontLine with Java 11
* 1719: Make users use standard Gatling OSS binaries
* 1720: Make Esc key close Edit Simulation
* 1786: LDAP: validate that we can bind with search user on start up
* 1787: LDAP: validate that we can search with search user on start up
* 1807: Interactive initial setup
* 1812: Update Swagger UI
* 1822: Provide a way to log CQL requests, see logback.xml
* 1845: The param scope in the /series API endpoint is now optional, defaulting to all
* 1862: Support setting a proxy for public clouds HTTP requests (API, injectors)
* 1871: Support deploying fatjars that are already compiled and published in a S3 bucket
* 1877: Document how to deal with JNA crash when FrontLine's temp dir is mounted with noexec
* 1891: Support GCP JSON file based auth
* 1896: Display number of attached pool / user / simulation to a team
* 1895: Provide certified AWS AMIs for OpenJDK8 and OpenJDK11

####### Fixes

* 1596: Long names (request, group) break layout, eg in dropdown menus
* 1622: High CPU usage in browser on trends when a run is ongoing
* 1666: Support large number of scenarios in simulation
* 1868: Limit the number of concurrent Cassandra requests
* 1884: Simulation JVM args should have precedence over global ones
* 1888: Fix PID file deletion race condition when rebooting
* 1890: Fix stacktrace formatting in run logs
* 1904: Fix check on SSH keys permissions
* 1905: Kill injector process as soon as all stats have been collected

#### Gatling 3.0.1.1.FL

{{< alert warning >}}
We now recommend you use latest Gatling OSS binaries (3.0.1 at the time of this release) instead of Gatling internal ones.
Please check the maven/sbt/gradle sections in the User Guide and the downloadable samples for how to update your build configuration.
{{< /alert >}}

Please check Gatling's:
* [migration guide](https://gatling.io/docs/current/migration_guides/2.3-to-3.0/) for hwo to upgrade. Most noticeable change for users coming from 1.4.7 is the case change of some DSL methods, eg `baseURL` becomes `baseUrl`.

### 1.5.0

Internal release for AWS Marketplace

### 1.4.7 (2018-10-09)

#### FrontLine

##### Features

* 1864: Ship aws-java-sdk-sts so assume role works out of the box

##### Fixes

* 1746: Report max concurrent count for given second, not value at measure time
* 1836: Fix Swagger documentation
* 1847: Concurrent counts are logged as 0 when not updated during this second
* 1851: Extra null/None in run snapshot jvmProperties when triggering from API
* 1863: On premises hosts are not properly reserved

#### Gatling 3.0.0-M21.FL

##### Fixes

* [3553](https://github.com/gatling/gatling/issues/3553): JsonPath shouldn't escape solidus
* [3554](https://github.com/gatling/gatling/issues/3554): Simulation folder points to resources one when launching Recorder from bundle

### 1.4.6 (2018-08-28)

#### FrontLine

##### Fixes

* 1789: AWS EIP are not properly reserved and instances are not terminated when association crashes
* 1799: Don't trigger sbt interactive mode when project fails to load
* 1803: Protect against empty second stats
* 1815: FrontLine Jenkins plugin swallows Exceptions
* 1820: Kernel in (4.0, 4.9) don't get detected as supporting Netlink

### 1.4.5 (2018-07-13)

#### FrontLine

##### Features

* 1784: Add an option for preferring private IPs for connection to on-demand AWS instances

##### Fixes

* 1777: Fix Aws Instance Profile Name input not being optional
* 1779: trustStore shouldn't be mandatory in the LDAP configuration
* 1780: objectClass shouldn't be mandatory in the LDAP configuration
* 1785: Allow booting from an empty keyspace
* 1788: Don't try assigning elastic IPs when list is empty

#### Gatling 3.0.0-M20.FL

##### Features

* [3105](https://github.com/gatling/gatling/issues/3105): Let users pass a custom check name
* [3408](https://github.com/gatling/gatling/issues/3408): Make actor system shutdown timeout configurable
* [3443](https://github.com/gatling/gatling/issues/3443): Result folder timestamp with sortable format
* [3476](https://github.com/gatling/gatling/issues/3476): Make checks with lots of possible actual values less verbose
* [3486](https://github.com/gatling/gatling/issues/3486): Make ConsoleDataWriter write interval configurable
* [3493](https://github.com/gatling/gatling/issues/3493): Maven plugin (3.0.0-M4): Support ant patterns in includes/excludes
* [3494](https://github.com/gatling/gatling/issues/3494): Have currentLocation expose url with fragment

##### Fixes

* [3473](https://github.com/gatling/gatling/issues/3473): Support single entry zip archives
* [3482](https://github.com/gatling/gatling/issues/3482): Fix http.perUserCacheMaxCapacity when set to 0
* [3485](https://github.com/gatling/gatling/issues/3485): Fix RampRateOpenInjection that wasn't always injecting the proper number
* [3487](https://github.com/gatling/gatling/issues/3487): Properly log response time when request crashes
* [3495](https://github.com/gatling/gatling/issues/3495): Maven plugin (3.0.0-M5): Properly honor zinc JVM args defaults

### 1.4.4 (2018-06-06)

#### FrontLine

##### Features

* 1703: Option to specify profile name for on-demand AWS EC2 instances

##### Fixes

* 1696: Re-implement LDAP integration w/ bare JNDI instead of ldaptive
* 1700: FrontLine over HTTPS only works with on-the-fly self-signed certificate
* 1701: Make run log less verbose when running large clusters
* 1705: Stats can be incomplete when run is aborted

#### Gatling 3.0.0-M19.FL

##### Features

* [3445](https://github.com/gatling/gatling/issues/3445): JMS listener thread count can be configured

##### Fixes

* [3468](https://github.com/gatling/gatling/issues/3468): Protect against division by 0 when computing assertions with no requests

### 1.4.3 (2018-03-30)

#### FrontLine

##### Features

* 1676: Make sure to log selector rebuilt in io.netty.channel.nio.NioEventLoop
* 1687: Use ISO8601 format for log timestamps

##### Fixes

* 1677: Concurrent LDAP connection usage
* 1678: Phantom EC2 instances when describe API crashes
* 1679: FrontLine launch script doesn't properly honor JAVA_HOME envar
* 1680: LDAP search doesn't take `usernameAttribute` conf into account
* 1681: Make dashboard login errors more verbose
* 1682: Support LDAP referrals
* 1683: Stop validating all users against LDAP server for performance reasons
* 1684: Make username handling case insentitive like in LDAP
* 1686: RemotelyClosedException because of injector not sending response to kill order

#### Gatling 3.0.0-M18.FL

##### Features

* [3436](https://github.com/gatling/gatling/issues/3436): Switch from Boon to Jodd as default JSON parser

##### Fixes

* [3437](https://github.com/gatling/gatling/issues/3437): Warmup request should set Connection header to close
