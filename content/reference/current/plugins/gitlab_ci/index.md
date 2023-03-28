---
title: "Gitlab CI/CD"
description: "Learn how to configure GitLab CI/CD to run your simulations on Gatling Enterprise."
lead: "Run your Gatling Enterprise simulations from GitLab CI/CD."
date: 2023-02-28T09:00:00+00:00
lastmod: 2023-02-28T14:00:00+00:00
weight: 5020
---

## The Gatling Enterprise Docker runner

This runner, packaged as a Docker image and [published on Docker Hub](https://hub.docker.com/r/gatlingcorp/enterprise-runner), enables you to start a Gatling Enterprise simulation directly from your GitLab CI/CD pipelines.

This plugin doesn't create a new Gatling Enterprise simulation, you have to create it using the Gatling Enterprise Dashboard before.

Don't forget to check out [GitLab's official documentation](https://docs.gitlab.com/ee/ci/) to learn how to write CI/CD pipelines on GitLab.

## Docker Hub coordinates

The Docker image is [published on Docker Hub](https://hub.docker.com/r/gatlingcorp/enterprise-runner) with the following coordinates: `gatlingcorp/enterprise-runner:1`.

You can check out the latest releases available [from the GitHub project](https://github.com/gatling/enterprise-action/releases). You generally only need to specify the major version you want to use, currently `1`.

## Pre-requisites

You must first [create an API token]({{< ref "../../admin/api-tokens" >}}), with at least the **Start** permission. It will be used to authenticate with Gatling Enterprise. You can store the API Token in a [Gitlab CI Variable](https://docs.gitlab.com/ee/ci/variables/#define-a-cicd-variable-in-the-ui) (make sure to check "Mask variable") with the name `GATLING_ENTERPRISE_API_TOKEN`. Or if you [use a vault to store secrets](https://docs.gitlab.com/ee/ci/secrets/), store the API Token in your vault and retrieve its value to an environment variable named `GATLING_ENTERPRISE_API_TOKEN` in your Gitlab CI/CD configuration file.

In the following examples, we assume the API Token is available in an environment variable named `GATLING_ENTERPRISE_API_TOKEN`, which our tools will detect automatically.

The runner will also need the **URL for your Gatling Enterprise instance**. In the following examples, we will use `http://my-gatling-instance.my-domain.tld`, but you must replace it with the correct URL for your Gatling Enterprise Self-Hosted instance. Please also note that it **must be accessible** from [the GitLab runners](https://docs.gitlab.com/runner/) you plan to use (either the GitLab.com runners or your own self-managed runners).

We also assume that you have already [configured a simulation]({{< ref "../../user/simulations" >}}) on Gatling Enterprise. You can copy the simulation ID from the simulations list view. In the following examples, we will show the simulation ID as `00000000-0000-0000-0000-000000000000`.

## Quickstart (minimal job configuration)

In this example, we configure a workflow which will only start a simulation as already configured and uploaded on Gatling Enterprise.

Create a file named `.gitlab-ci.yml` in your repository:

```yaml
stages:
  - load-test

run-gatling-enterprise:
  stage: load-test
  image:
    name: gatlingcorp/enterprise-runner:1
    entrypoint: ['']
  script:
    - gatlingEnterpriseStart
  variables:
    # We assume GATLING_ENTERPRISE_API_TOKEN is available,
    # e.g. configured on the GitLab project
    GATLING_ENTERPRISE_URL: 'http://my-gatling-instance.my-domain.tld'
    SIMULATION_ID: '00000000-0000-0000-0000-000000000000'
```

Push this to GitLab. The pipeline will run automatically on new commits; you can also run it manually from your GitLab project's CI/CD menu.

## Configuration reference

Several options can be configured with environment variables. The Docker runner also provides several outputs which can be used in the following stages of your pipeline.

### Inputs

Example:

```yaml
stages:
  - load-test

run-gatling-enterprise:
  stage: load-test
  image:
    name: gatlingcorp/enterprise-runner:1
    entrypoint: ['']
  script:
    - gatlingEnterpriseStart
  variables:
    GATLING_ENTERPRISE_URL: 'http://my-gatling-instance.my-domain.tld'
    GATLING_ENTERPRISE_API_TOKEN: 'my-api-token' # Typically not hard-coded in the script!
    SIMULATION_ID: '00000000-0000-0000-0000-000000000000'
    EXTRA_SYSTEM_PROPERTIES: >
      {
        "sys_prop_1":"value 1",
        "sys_prop_2":42,
        "sys_prop_3":true
      }
    EXTRA_ENVIRONMENT_VARIABLES: >
      {
        "ENV_VAR_1":"value 1",
        "ENV_VAR_2":42,
        "ENV_VAR_3":true
      }
    OVERRIDE_LOAD_GENERATORS: >
      {
        "4a399023-d443-3a58-864f-3919760df78b":{"size":1,"weight":60},
        "c800b6d9-163b-3db7-928f-86c1470a9542":{"size":1,"weight":40}
      }
    FAIL_ACTION_ON_RUN_FAILURE: 'true'
    WAIT_FOR_RUN_END: 'true'
    OUTPUT_DOT_ENV_FILE_PATH: 'path/to/file.env'
```

- `GATLING_ENTERPRISE_URL` {{< badge danger >}}required{{< /badge >}}: The URL for your Gatling Enterprise Self-Hosted instance (if not specified, it will point to Gatling Enterprise Cloud instead).

- `GATLING_ENTERPRISE_API_TOKEN` {{< badge danger >}}required{{< /badge >}}: The API token used by the runner to authenticate with Gatling Enterprise.

- `SIMULATION_ID` {{< badge danger >}}required{{< /badge >}}: The ID of the simulation as configured on Gatling Enterprise.

- `EXTRA_SYSTEM_PROPERTIES` {{< badge info >}}optional{{< /badge >}}: Additional Java system properties, will be merged with the simulation's configured system properties. Must be formatted as a JSON object containing the desired key/value pairs. Values can be strings, numbers or booleans.

- `EXTRA_ENVIRONMENT_VARIABLES` {{< badge info >}}optional{{< /badge >}}: Additional environment variables, will be merged with the simulation's configured environment variables. Must be formatted as a JSON object containing the desired key/value pairs. Values can be strings, numbers or booleans.

- `OVERRIDE_LOAD_GENERATORS` {{< badge info >}}optional{{< /badge >}}: Overrides the simulation's load generators configuration. Must be formatted as a JSON object. Keys are the load generator IDs, which can be retrieved [from the public API]({{< ref "../../user/api" >}}) (using the `/pools` route). Weights are optional.

- `FAIL_ACTION_ON_RUN_FAILURE` {{< badge info >}}optional{{< /badge >}} (defaults to `true`): If `true`, the Action will fail if the simulation run ends in an error (including failed assertions). Note: if set to `false` and the simulation ends in an error, some of the outputs may be missing (e.g. there will be no assertion results if the simulation crashed before the end).

- `WAIT_FOR_RUN_END` {{< badge info >}}optional{{< /badge >}} (defaults to `true`): If `true`, the runner will wait for the end of te simulation run on Gatling Enterprise before terminating. Note: if set to `false`, some of the outputs may be missing (there will be no status nor assertion results).

- `OUTPUT_DOT_ENV_FILE_PATH` {{< badge info >}}optional{{< /badge >}} (defaults to `gatlingEnterprise.env`): path to a dotenv file where output values will be written

### Outputs

Outputs are written to a dotenv file, which can then be exported to make the variables available in later stages. Check out [the GitLab documentation](https://docs.gitlab.com/ee/ci/variables/#pass-an-environment-variable-to-another-job) for more details on exporting dotenv files. Example:

```yaml
stages:
  - load-test
  - post-load-test

run-gatling-enterprise:
  stage: load-test
  image:
    name: gatlingcorp/enterprise-runner:1
    entrypoint: ['']
  script:
    - gatlingEnterpriseStart
  variables:
    GATLING_ENTERPRISE_URL: 'http://my-gatling-instance.my-domain.tld'
    SIMULATION_ID: '00000000-0000-0000-0000-000000000000'
  artifacts:
    reports:
      dotenv: 'gatlingEnterprise.env' # Using the default value

print-output:
  stage: post-load-test
  image: alpine:latest
  script: |
    # Show that we can access the outputs exported by the previous stage
    echo "RUN_ID=$RUN_ID"
    echo "REPORTS_URL=$REPORTS_URL"
    echo "RUNS_URL=$RUNS_URL"
    echo "RUN_STATUS_CODE=$RUN_STATUS_CODE"
    echo "RUN_STATUS_NAME=$RUN_STATUS_NAME"
    echo "RUN_ASSERTIONS=$RUN_ASSERTIONS"
```

- `RUN_ID`: The ID of the run started by this runner.

- `REPORTS_URL`: The URL of the reports page for this run.

- `RUNS_URL`: The URL of the runs history page for this simulation.

- `RUN_STATUS_NAME`: The name of the run's final status (e.g. `Successful`, `AssertionsSuccessful`, `AssertionsFailed`, etc.).

- `RUN_STATUS_CODE`: The code of the run's final status.

- `RUN_ASSERTIONS`: The results of the run's assertions, as a JSON array.

### Logs

Every few seconds, the Docker runner logs to the console output a summary of the run's current status. When the run ends, it logs the status of the run and the results of any assertions. Here's the beginning and end of a very short duration example:

{{< img src="reference_logs_start.png" alt="A run's logs in the GitLab CI/CD console (beginning)" >}}

{{< img src="reference_logs_end.png" alt="A run's logs in the GitLab CI/CD console (end)" >}}

## Sample use cases

### Building from sources

In this example, we assume you have configured your repository on Gatling Enterprise to [build from sources]({{< ref "../../user/repositories/#downloading-from-sources" >}}), from your GitHub repository's `main` branch. Every time the code on the `main` branch gets updated, we run the updated simulation on Gatling Enterprise.

Feel free to use different trigger events or to configure the other inputs and outputs for the runner as documented above, according to your own use case. But keep in mind that Gatling Enterprise will only download and run your simulation scripts from the branch set [in the simulation configuration]({{< ref "../../user/simulations/#option-1-build-from-sources" >}})!

```yaml
workflow:
  rules:
    # Execute the pipeline only on pushes to the main branch
    - if: $CI_COMMIT_BRANCH == "main"

stages:
  - load-test

# Run the simulation on Gatling Enterprise
# If it is configured to "build from sources" from the branch "main",
# it will download and run the updated version of the code
run-gatling-enterprise:
  stage: load-test
  image:
    name: gatlingcorp/enterprise-runner:1
    entrypoint: ['']
  script:
    - gatlingEnterpriseStart
  variables:
    GATLING_ENTERPRISE_URL: 'http://my-gatling-instance.my-domain.tld'
    SIMULATION_ID: '00000000-0000-0000-0000-000000000000'
```

### Using a binary repository

This workflow is defined in the GitLab repository which contains your Gatling simulation script built with one of our build tools plugins. In this example, every time the code on the `main` branch gets updated, we build, package, and publish the current version of the simulation script, before starting the simulation on Gatling Enterprise.

In this example, we assume that:
- You have configured your repository on Gatling Enterprise to [download from a binary repository]({{< ref "../../user/repositories/#downloading-from-a-binary-repository" >}}), using Artifactory or Sonatype Nexus.
- You have [configured your simulation]({{< ref "../../user/simulations/#option-2-download-binary-from-repository" >}}) to use the version marker `latest.integration` for the artifact published on the binary repository.
- Your build is properly configured to publish to the binary repository, using [Maven](https://gatling.io/docs/gatling/reference/current/extensions/maven_plugin/#publish-to-a-binary-repository), [Gradle](https://gatling.io/docs/gatling/reference/current/extensions/gradle_plugin/#publish-to-a-binary-repository), or [SBT](https://gatling.io/docs/gatling/reference/current/extensions/sbt_plugin/#publish-to-a-binary-repository).

{{< include-file >}}
Maven: includes/use-case-binary-repo.maven.md
Maven Wrapper: includes/use-case-binary-repo.maven.md
Gradle: includes/use-case-binary-repo.gradle.md
Gradle Wrapper: includes/use-case-binary-repo.gradlew.md
Sbt: includes/use-case-binary-repo.sbt.md
{{< /include-file >}}

{{< alert tip >}}
For each build tool, there can be different ways to configure credentials for the target repository. We only provide some examples, with links to the relevant documentations they are based on.
{{< /alert >}}

### Run the simulation weekly

This pipeline will only run when started by a pipeline schedule, which you can [configure in your GitLab project](https://docs.gitlab.com/ee/ci/pipelines/schedules.html), for example to run once a week.

```yaml
workflow:
  rules:
    # Execute the pipeline only when scheduled
    - if: $CI_PIPELINE_SOURCE == "schedule"

stages:
  - load-test

run-gatling-enterprise:
  stage: load-test
  image:
    name: gatlingcorp/enterprise-runner:1
    entrypoint: ['']
  script:
    - gatlingEnterpriseStart
  variables:
    GATLING_ENTERPRISE_URL: 'http://my-gatling-instance.my-domain.tld'
    SIMULATION_ID: '00000000-0000-0000-0000-000000000000'
```
