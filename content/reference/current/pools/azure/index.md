---
title: "Azure"
description: "Azure pools are a configuration of Azure injectors"
lead: "Deploy Gatling Enterprise injectors on Azure"
date: 2021-03-26T09:40:45+01:00
lastmod: 2021-08-16T17:55:36+02:00
weight: 404
---

A Microsoft Azure Pool is a reference to the Azure account you can use to spawn injectors to run the simulation. Only Linux virtual machines are supported.

{{< alert warning >}}
Make sure your network configuration will let Gatling Enterprise connect to your injectors on port 22 (SSH) and 9999 (HTTP).
{{< /alert >}}

To configure the type of instances you want to spawn, you need to fill the form below:

{{< img src="azure.png" alt="Azure Pool" >}}

- **Team**: Set if the pool is global or owned by a team
- **Subscription ID**: the Azure subscription you want to use, check [this article](https://www.inkoop.io/blog/how-to-get-azure-api-credentials/) to learn how to retrieve it
- **Application ID**: the id of the Azure application you want to authenticate with
- **Directory ID**: the Azure directory you want to use
- **Client Secret**: the key used to authenticate
- **Region**: the region where you want to spawn your instances
- **Size**: the size of the instances
- **Network**: the network configured on your Microsoft Azure account you want to use
- **Subnet**: the subnet you want to use
- **Connect to private IP**: Gatling Enterprise will connect to the injectors' private IP instead of the public one. If unchecked, the private IP remains a fallback if a public IP is missing. This option should be used only when the Gatling Enterprise host and the injector are both in the same Azure network.
- **Image** or **Image URL**: the certified image or the url of the image you want to use for your instances.

{{< alert tip >}}
You can use our certified images or the url of your custom VHD: the image should at least have JDK8 installed, a configured key pair without password and the port 22 & 9999 should be open, see the [Azure documentation](https://docs.microsoft.com/en-us/azure/virtual-machines/virtual-machines-linux-capture-image) if you want to learn how to make your own image.
{{< /alert >}}

- **Public Key**: the public ssh key to connect to your instances
- **Username**: the username used by your ssh command to connect to the instances

{{< alert warning >}}
Azure has some requirements about the username:

- It must be less than 20 characters
- It cannot end with a period (`.`)
- Many usernames are forbidden, such as `admin` and `root`

Check the [Azure documentation](https://docs.microsoft.com/en-us/azure/virtual-machines/windows/faq#what-are-the-username-requirements-when-creating-a-vm-) for all the details.
{{< /alert >}}

- **Private Key**: the previously added [private key]({{< ref "../../admin/private-keys" >}}) associated with the public ssh key

It's also possible to use User Assigned Managed Identities, refer to the installation guide if you want to create a Managed Identity:

{{< img src="azure-msi.png" alt="User Assigned Managed Identities" >}}
