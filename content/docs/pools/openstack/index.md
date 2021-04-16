---
title: "Openstack"
description: "Openstack pool are a configuration of Openstack injectors"
lead: "Deploy FrontLine injectors on an OpenStack Cluster (ie OVH, City Cloud and Rackspace)"
date: 2021-03-26T09:41:12+01:00
lastmod: 2021-03-26T09:41:12+01:00
draft: false
images: []
menu:
  docs:
    parent: "pools"
weight: 30050
toc: true
---

Like the others pools, an OpenStack Pool is a reference to the OpenStack account you want to use to spawn injectors to run the simulation. We only support Keystone version 3.
To configure the type of instances you want to spawn, you need to fill the form below:

{{< img src="openstack.png" alt="Openstack Pool" >}}

- **Team**: Set if the pool is global or owned by a team
- **Identity Endpoint**: complete url of your OpenStack API (eg: https://auth.cloud.ovh.net/v3)
- **Identity**: your OpenStack identity, it is most of the time: `domain:username`
- **Credentials**: Account password
- **Scope**: you can use scoped authentication (eg: `project:myprojectname`)
- **Region**: the region where to spawn your instances
- **Key Pair**: the Key pair name use by your image
- **Image**: the image used for your instances (the image should at least have jdk8 installed and a configured key pair without password and the port 22 & 9999 should be open)
- **Flavor**: the flavor used for your instances
- **Security Group**: the security group of your account
- **Username**: the username used by your ssh command to connect to the instances
- **Private Key**: the previously added [private key]({{< ref "docs/admin/private-keys" >}}) used by your images
- **Availability Zone**: the optional availability zone
