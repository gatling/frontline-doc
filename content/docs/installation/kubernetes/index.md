---
title: "Automated installation with Kubernetes"
description: "Learn how to install FrontLine with Kubernetes"
date: 2021-03-26T17:37:11+01:00
lastmod: 2021-03-26T17:37:11+01:00
draft: false
images: []
menu:
  docs:
    parent: "installation"
weight: 050
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
apiVersion: v1
kind: Service
metadata:
  name: cassandra
  namespace: frontline
spec:
  ports:
    - name: tcp
      port: 9042
  selector:
    app: cassandra
---
apiVersion: apps/v1
kind: Deployment
metadata:
  name: cassandra
  namespace: frontline
spec:
  replicas: 1
  strategy:
    type: Recreate
  selector:
    matchLabels:
      app: cassandra
  template:
    metadata:
      labels:
        app: cassandra
    spec:
      containers:
        - name: cassandra
          image: cassandra:3.11
          imagePullPolicy: IfNotPresent
          resources:
            requests:
              cpu: 2
              memory: 3Gi
          ports:
            - containerPort: 9042
          volumeMounts:
            - mountPath: /var/lib/cassandra
              name: cassandra-data
          securityContext:
            capabilities:
              add:
                - IPC_LOCK
      volumes:
        - name: cassandra-data
          # Prefer PersistentVolumeClaims for durability
          hostPath:
            path: <local storage path for Cassandra data>
```

## Setup FrontLine

### Setup RBAC (optional)

If your cluster has RBAC enabled, this manifest configures the necessary permissions for FrontLine:

```yaml
# Dedicated namespace for FrontLine
apiVersion: v1
kind: Namespace
metadata:
  name: frontline
---
# Service account named frontline
apiVersion: v1
kind: ServiceAccount
metadata:
  name: frontline-sa
  namespace: frontline
---
# Service account token
apiVersion: v1
kind: Secret
metadata:
  name: frontline-sa-token
  namespace: frontline
  annotations:
    kubernetes.io/service-account.name: frontline-sa
type: kubernetes.io/service-account-token
---
# Role containing needed permissions
apiVersion: rbac.authorization.k8s.io/v1
kind: Role
metadata:
  name: frontline-manage-injectors
  namespace: frontline
rules:
    # Used to check the pool configuration
  - apiGroups: [""]
    resources: ["namespaces"]
    verbs: ["get"]
    # Needed for management of injectors instances
  - apiGroups: [""]
    resources: ["services", "pods", "pods/exec"]
    verbs: ["create","delete","get","list","patch","update","watch"]
    # Only for usage of Ingresses
  - apiGroups: ["extensions"]
    resources: ["ingresses"]
    verbs: ["create", "delete", "get", "list", "watch"]
    # Only for usage of OpenShift Routes
  - apiGroups: ["route.openshift.io"]
    resources: ["routes", "routes/custom-host"]
    verbs: ["create", "delete", "get", "list", "watch"]
---
# Bind role to the service account
apiVersion: rbac.authorization.k8s.io/v1
kind: RoleBinding
metadata:
  name: frontline-role-binding
  namespace: frontline
roleRef:
  apiGroup: rbac.authorization.k8s.io
  kind: Role
  name: frontline-manage-injectors
subjects:
  - kind: ServiceAccount
    name: frontline-sa
    namespace: frontline
---
# Only for usage of NodePort
apiVersion: rbac.authorization.k8s.io/v1
kind: ClusterRole
metadata:
  name: frontline-manage-injectors
rules:
  - apiGroups: [""]
    resources: ["nodes"]
    verbs: ["list"]
---
# Only for usage of NodePort
apiVersion: rbac.authorization.k8s.io/v1
kind: ClusterRoleBinding
metadata:
  name: frontline-cluster-role-binding
subjects:
  - kind: ServiceAccount
    name: frontline-sa
    namespace: frontline
    apiGroup: ""
roleRef:
  kind: ClusterRole
  name: frontline-manage-injectors
  apiGroup: ""
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
