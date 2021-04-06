---
title: "FrontLine Configuration"
description: "Default FrontLine configuration"
date: 2021-03-26T17:57:29+01:00
lastmod: 2021-03-26T17:57:29+01:00
draft: false
images: []
menu:
  docs:
    parent: "installation"
weight: 070
toc: true
---

Find below the default frontline.conf file:

```text
frontline {
  #licenseKey = DO_NOT_EDIT_FRONTLINE_WILL_REPLACE_THIS
  http {
    port = 10542
    cookieMaxAge = 604800
    ssl {
      #certificate = "/path/to/domain.crt"
      #privateKey = "/path/to/domain.key"
    }
    proxy {
      #host = ""
      #port = 80
    }
  }
  injector {
    httpPort = 9999
    enableLocalPool = false
    kubernetes {
      disableTrustManager = true
    }
  }
  security {
    #superAdminPassword = DO_NOT_EDIT_FRONTLINE_WILL_REPLACE_THIS
    #secretKey = DO_NOT_EDIT_FRONTLINE_WILL_REPLACE_THIS
  }
  cassandra {
    localDataCenter = datacenter1
    contactPoints = [{
      host = localhost
      host = ${?FRONTLINE_CASSANDRA_HOST}
      port = 9042
      port = ${?FRONTLINE_CASSANDRA_PORT}
    }]
    keyspace = gatling
    replication = "{'class':'SimpleStrategy', 'replication_factor': 1}"
    credentials {
      #username = "hello"
      #password = "world"
    }
    runsCleanup {
      #maxRunsBySimulation = 30
      #maxRunAge = 100
      #timeOfDay = "15:10"
    }
  }
  grafana {
    #url = "http://localhost:3008/dashboard/db/frontline-requests"
  }
  ldap {
    #host = localhost
    #port = 389
    #baseDn = "dc=example,dc=com"
    #distinguishedName = "cn=John Doe,ou=Users,dc=example,dc=com"
    #password = "secret"
    usernameAttribute = uid
    firstNameAttribute = givenName
    surnameAttribute = sn
    mailAttribute = mail
    connectTimeoutMs = 5000
    responseTimeoutMs = 10000
    #personObjectClass = person
    ssl {
      #format = "PEM | JKS" PEM will trigger the pem part of the configuration and JKS the jks part
      pem {
        #serverCertificate = "/path/to/domain.pem"
        #clientCertificate = "/path/to/domain.pem"
        #privateKey = "/path/to/domain.key"
      }
      jks {
        #trustStore = "path/to/truststore.jks"
        #trustStorePassword = "secret"
        #keystore = "path/to/keystore.jks"
        #keystorePassword = "secret"
      }
    }
  }
  oidc {
      # discoveryUrl = "https://provider/.well-known/openid-configuration"
      client {
        # id = "xxxxx-xxxxx-xxxxx-xxxxx-xxxxx"
        # secret = "*******"
      }
      # responseMode = "fragment" | "okta_post_message"
      # scopes = ["openid", "email", "profile"]
      # jwksRefreshFrequency = 1440
      mapping {
        # username: "unique_name"
        # firstname: "given_name"
        # lastname: "family_name"
        # email: "email"
      }
    }
}
```
