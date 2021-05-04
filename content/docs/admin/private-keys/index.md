---
title: "Private Keys"
description: "Learn how to administrate private keys"
lead: "Private keys are necessary to configure pools and injectors"
date: 2021-03-25T18:09:45+01:00
lastmod: 2021-03-25T18:09:45+01:00
draft: false
images: []
menu:
  docs:
    parent: "admin"
weight: 40040
toc: true
---

To access the Private Keys administration, click on **Admin** in the navigation bar, and choose **Private Keys**. A private key corresponds to the ssh key used to connect to your Git repository or pool instances.

{{< img src="private-keys.png" alt="Private Keys table" >}}

To create a Private Key, click on the **Create** button.

{{< img src="create-private-key.png" alt="Private Key creation modal" >}}

A private key can be scoped on pool or repository. It means that you can only use this private key while configuring a repository or a pool. The all scope can't be choosen, as it is only there for the legacy private keys without scope.

You have two possibilities to reference private keys:

- Upload them directly by drag-and-drop or click on the input to choose the file on your filesystem
- Locate a private key existing on FrontLine's host. The private key permissions should be 600 or 400, and its owner should be the FrontLine process user

{{< alert tip >}}
If you are using the AWS marketplace offer and wish to reference an existing private key, you must connect with the `ec2-user` user and then `sudo` to the `frontline` user which is the one running the FrontLine process.{{< /alert >}}

You can edit the private key by clicking on the {{< icon pencil-alt >}} icon and delete them using the checkboxes on the table's right part.

