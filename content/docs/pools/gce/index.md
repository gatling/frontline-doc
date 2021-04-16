---
title: "Gce"
description: "GCE pool are a configuration of GCE injectors"
lead: "Deploy FrontLine injectors on GCE"
date: 2021-03-26T09:40:40+01:00
lastmod: 2021-03-26T09:40:40+01:00
draft: false
images: []
menu:
  docs:
    parent: "pools"
weight: 30040
toc: true
---

Like the AWS Pool, a GCE Pool is a reference to the GCE account you want to use to spawn injectors to run the simulation.
To configure the type of instances you want to spawn, you need to fill the form below:

{{< img src="gce.png" alt="GCE Pool" >}}

- **Team**: Set if the pool is global or owned by a team
- **Credentials**: If you're running Frontline on GCE or using `GOOGLE_APPLICATION_CREDENTIALS` to configure access, use 'Application Default'. Otherwise, use JSON credentials.
- **Zone**: the zone where you want to spawn your injectors
- **Private Key**: the previously added [private key]({{< ref "docs/admin/private-keys" >}}) used by your Template
- **Use private IP**: check this if you want your injectors to use their private IP instead of the public one
- **Deployment**: You can choose to spawn GCE instances from an image or an instance template

Specific configuration if you chose Image:

- **Image**: the image you want to use for your instances. You can use our certified Images or the url of your custom Image (the Image should at least have JDK8 installed and a configured key pair without password)
- **Machine type**: this machine type will be used by the injectors. We recommend using n1-highcpu-4 or n1-highcpu-8 machines.
- **Subnetwork**: the subnetwork the instances will use
- **Preemptible**: check this if you want to use preemptible instances (cheaper, but can be reclaimed by GCE)
- **Public SSH key**: content of the public SSH key used to connect to the instances
- **Network tags**: networks tags you may want to apply to the instances
- **Use Static IPs**: check this if you want your injectors to use predefined static IPs

Specific configuration if you chose Instance Template:

- **Template**: the template used for your instances, the template should at least have JDK8 installed, a configured key pair without password and the port 22 & 9999 should be open
- **Username**: the username used by your ssh command to connect to the instances
