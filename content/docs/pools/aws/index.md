---
title: "Aws"
description: "AWS pool are a configuration of AWS injectors"
lead: "Deploy FrontLine injectors on AWS"
date: 2021-03-26T09:40:35+01:00
lastmod: 2021-03-26T09:40:35+01:00
draft: false
images: []
menu:
  docs:
    parent: "pools"
weight: 30030
toc: true
---

An AWS Pool is a reference to the AWS account you want to use to spawn injectors to run the simulation.
To configure the type of instances you want to spawn, you need to fill the form below:

{{< img src="aws.png" alt="AWS Pool" >}}

- **Team**: Set if the pool is global or owned by a team
- **Profile Name**: Name of the AWS profile described in the AWS credentials file. If you want to use System or Environment properties instead of this file, choose `Use environment or system variables`
- **Region**: the region where to spawn your instances
- **AMI**: the AMI you want to use for your instances. You can use our certified AMIs or the ID of your custom AMI (the AMI should at least have JDK8 installed, a configured key pair without password and the port 22 & 9999 should be open)
- **VPC**: the VPC in which your instance will be created
- **Subnet**: the subnet in which your instance will be created
- **Security Group**: the security groups the instance will use
- **Instance Type**: the type of the instances you want to spawn
- **Key Pair**: the Key pair name used by your AMI
- **User Name**: the username used by your ssh command to connect to the instances. If you use one of our certified AMIs, the username will be ec2-user
- **Private Key**: the previously added [private key]({{< ref "docs/admin/private-keys" >}}) used by your AMI
- **Use Elastic IP**: Allow instances to use predefined Elastic IP
- **Use private IP**: FrontLine will use the injector private IP instead of the public one by default. If unchecked, the private IP remains a fallback if a public IP is missing. This option should be used only when the FrontLine host and the injector are both on AWS on the same network.
- **IAM Instance Profile**: optional step, you can specify an IAM instance profile to grant injectors permissions
- **AWS tags**: optional step, the tags will be visible in your AWS interface, hence you will be able to monitor them

{{< alert warning >}}
If you're using our certified AMIs, make sure that you add a security group allowing Internet access.
This is required for automatic critical security updates checks done by the OS.
{{< /alert >}}
