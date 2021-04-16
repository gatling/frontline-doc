---
title: "Pools"
description: "Pools are the configuration of your injectors"
lead: "Deploy multiple FrontLine injectors"
date: 2021-03-26T09:28:01+01:00
lastmod: 2021-03-26T09:28:01+01:00
draft: false
images: []
menu:
  docs:
    parent: "pools"
weight: 30010
toc: true
---

Pools are the configuration of your injectors.

The Pools view is split by pool type, each in a different tab.
There are currently 6 types of pool:

- [On-premises]({{< ref "docs/pools/on-premises" >}}): a pool which contains hosts configured manually
- [AWS]({{< ref "docs/pools/aws" >}}): used to spawn instances on a configured AWS account
- [GCE]({{< ref "docs/pools/gce" >}}): used to spawn instances on a configured GCE account
- [OpenStack]({{< ref "docs/pools/openstack" >}}): used to spawn instances on a configured OpenStack account
- [Microsoft Azure]({{< ref "docs/pools/azure" >}}): used to spawn instances on a configured Microsoft Azure account
- [Kubernetes]({{< ref "docs/pools/kubernetes" >}}): used to spawn instances through your configured Kubernetes/OpenShift

If you are using the AWS marketplace offer, you will only have access to the On-premises, AWS, and Kubernetes pools.
If you are using the Azure marketplace offer, you will only have access to the On-premises, Azure, and Kubernetes pools.

{{< img src="pools.png" alt="Pools" >}}

{{< alert warning >}}
Every pool except Kubernetes will need to have a [private key]({{< ref "docs/admin/private-keys" >}}) configured, and scoped on Pools. You won't be able to create one of these pools if you have not created a private key.
{{< /alert >}}
