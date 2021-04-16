---
title: "Automated installation with Kubernetes"
description: "Learn how to install FrontLine with Kubernetes"
lead: "Install FrontLine and Cassandra easily with Kubernetes"
date: 2021-03-26T17:37:11+01:00
lastmod: 2021-03-26T17:37:11+01:00
draft: false
images: []
menu:
  docs:
    parent: "installation"
weight: 20050
toc: true
---

If you're using Kubernetes-based injector pools, it is recommended to run FrontLine itself inside Kubernetes too:

* Less configuration is required than when connecting to a cluster from the outside
* It is not necessary to open firewall rules so that FrontLine can contact injectors

## Getting FrontLine's Docker image

FrontLine's image is hosted as a private image on [Docker Hub](https://hub.docker.com/r/gatlingcorp/frontline).

Please contact our support and provide us with your Docker Hub username so we can grant you access.

## Getting FrontLine's injectors Docker image

FrontLine's injector image is publicly accessible on [Docker Hub](https://hub.docker.com/r/gatlingcorp/frontline-injector).

## Setup Cassandra

This manifest setups a single-node Cassandra cluster, along with a service to expose it

```yaml
{{< include-static "kubernetes-cassandra.yml" >}}
```

## Setup FrontLine

### Setup RBAC (optional)

If your cluster has RBAC enabled, this manifest configures the necessary permissions for FrontLine:

```yaml
{{< include-static "kubernetes-frontline.yml" >}}
```

### Setup Docker Hub credentials as a secret (Optional)

If you're not mirroring FrontLine's image in your private registry, you'll need to setup your Docker credentials as a secret to pull FrontLine's image:

```bash
kubectl create secret docker-registry docker-hub-credentials \
  --docker-server=<your-registry-server> \
  --docker-username=<your-name> \
  --docker-password=<your-pword> \
  --docker-email=<your-email>
```

### Setup FrontLine

This manifest sets up FrontLine, pre configured with your license key and admin credentials.
You can then expose FrontLine using LoadBalancer/NodePort services, Ingress, etc...

```yaml
apiVersion: v1
kind: ConfigMap
metadata:
  name: frontline-conf
  namespace: frontline
data:
  frontline.conf: |
    frontline.licenseKey = <YOUR FRONTLINE LICENSE KEY>
    frontline.security.superAdminPassword = <YOUR SUPER ADMIN PASSWORD>
    frontline.security.secretKey = <YOUR ENCRYPTION SECRET KEY>
    frontline.cassandra.contactPoints = [{
      host = cassandra.frontline.svc.cluster.local
      port = 9042
    }]
  logback.xml: |
    <?xml version="1.0" encoding="UTF-8"?>
    <configuration>
      <contextListener class="ch.qos.logback.classic.jul.LevelChangePropagator">
        <resetJUL>true</resetJUL>
      </contextListener>
      <appender name="CONSOLE" class="ch.qos.logback.core.ConsoleAppender">
        <encoder>
          <pattern>%d [%-5level] %logger{15} - %msg%n%rEx</pattern>
        </encoder>
        <immediateFlush>false</immediateFlush>
      </appender>
      <root level="INFO">
        <appender-ref ref="CONSOLE"/>
      </root>
    </configuration>
---
apiVersion: v1
kind: Service
metadata:
  name: frontline
  namespace: frontline
spec:
  ports:
    - name: http
      port: 10542
  selector:
    app: frontline
---
apiVersion: apps/v1
kind: Deployment
metadata:
  name: frontline
  namespace: frontline
spec:
  replicas: 1
  strategy:
    type: Recreate
  selector:
    matchLabels:
      app: frontline
  template:
    metadata:
      labels:
        app: frontline
    spec:
      # Required unless you mirror FrontLine in your private registry
      imagePullSecrets:
        - name: docker-hub-credentials
      serviceAccountName: frontline-sa
      containers:
        - name: frontline
          imagePullPolicy: Never
          image: gatlingcorp/frontline:{{< var revnumber >}}
          resources:
            requests:
              cpu: 2
              memory: 4Gi
          ports:
            - containerPort: 10542
          volumeMounts:
            - mountPath: /opt/frontline/conf
              name: frontline-conf
            - mountPath: /opt/frontline/keys
              name: ssh-keys
      volumes:
        - name: frontline-conf
          configMap:
            name: frontline-conf
        - name: ssh-keys
          # Prefer PersistentVolumeClaims for durability
          hostPath:
            path: <local storage path for SSH keys>
```

{{< alert tip >}}
Depending on your needs, you may need to configure additional volumes on the FrontLine container (SSL certificate if HTTPS is configured, or keystore/truststore for LDAP support)
{{< /alert >}}
