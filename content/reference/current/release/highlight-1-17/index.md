---
title: "Gatling Enterprise 1.17 Highlights"
description: "Learn about the main new features of Gatling Enterprise 1.17"
lead: "Gatling Enterprise 1.17 introduces Gatling 3.8 support and a redesign of the PDF export feature"
date: 2022-07-05T11:00:00+02:00
lastmod: 2022-07-05T11:00:00+02:00
weight: 6014
---

## Gatling Enterprise 1.17

### Gatling 3.8 Support

Gatling 3.8 is the latest generation of Gatling.

It introduces a new behavior for sequential scenarios with `andThen` and several bug fixes with the Java DSL.

### PDF Exports Redesign

The existing PDF Exports implementation was plagued with design errors that were causing numerous issues. In this release, we've completely reimplemented this feature, hopefully bringing a way more pleasant user experience.
