---
title: "Kubernetes"
description: "Kubernetes pools are a configuration of Kubernetes injectors"
lead: "Deploy FrontLine injectors on Kubernetes"
date: 2021-03-26T09:41:20+01:00
lastmod: 2021-03-26T09:41:20+01:00
draft: false
images: []
menu:
  docs:
    parent: "pools"
weight: 30070
toc: true
---

A Kubernetes/OpenShift Pool is a reference to your Kubernetes infrastructure.

To configure the type of instances you want to spawn, you need to fill the form below:

{{< img src="kubernetes.png" alt="Kubernetes Pool" >}}

- **Team**: Set if the pool is global or owned by a team
- **Kubernetes Url**: The url of your Kubernetes API with the protocol
- **Service Account Token**: The token of your service account which has edit permissions on the namespace below (see <<needed-permissions, needed permissions>>)
- **Namespace**: The namespace/project name in which injectors will be spawned
- **Connection**:
    - **NodePort**: exposes the Service on each injector Node's IP at a static port
    - **Ingress**: exposes HTTP and HTTPS routes from outside the cluster to injectors within the cluster
    - **TLS secret name**: the optional secret containing a certificate used by the ingress (link:https://kubernetes.github.io/ingress-nginx/user-guide/tls/#tls-secrets[TLS secrets documentation])
    - **Route**: (OpenShift extension) exposes HTTP routes (HTTPS not supported) from outside the cluster to injectors within the cluster.
        - **Secured**: allow you to add the desired certificate on the route (link:https://docs.openshift.com/container-platform/4.5/networking/routes/secured-routes.html[OpenShift secured routes documentation])
        - **Certificate**: Certificate associated with the route
        - **Certificate key**: Certificate key associated to certificate
        - **CA Certificate**: Certificate authority signing the certificate

- **Docker Image**: Docker image that will be used for injectors. You can use our certified Docker images if your Kubernetes cluster has access to Docker Hub, or build your own with https://github.com/gatling/frontline-injector-docker-image
- **Image pull secret**: Recommended approach to run containers based on images in private registries and / or to not be limited by rate limits
- **CPU request**: The minimum number of cores that you need for each one of your injector, express as cpus
- **CPU limit**: The limit of cores that you don't want your injector pod to exceed, express as cpus
- **Memory request**: The minimum memory that you need for each one of your injector
- **Memory limit**: The maximum memory that you need for each one of your injector

Limits and requests for memory are measured in bytes. You can express memory as a plain integer or as a fixed-point integer using one of these suffixes: E, P, T, G, M, K. You can also use the power-of-two equivalents: Ei, Pi, Ti, Gi, Mi, Ki.

NOTE: If your FrontLine instance belongs to a Kubernetes cluster, you don't have to provide a **Kubernetes Url** and a **Service Account Token**.
You can still choose to configure it, for example to create a pool in another cluster, by unticking `Use local cluster`.
Also, you can specify preferring **Internal IP** over the **External IP** for connecting to Kubernetes nodes, by ticking `Prefer internal IP`.

{{< img src="kubernetes-local.png" alt="Kubernetes local" >}}

## Minimal permissions for FrontLine service account

Service account associated to the service-account-token must be binded with permissions to manage services, nodes, routes, ingresses and pods (depending on your needs).
Below, you can find a commented configuration file containing all needed permissions.

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
