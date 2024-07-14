# "TODO" App performance tests

The repository contains automated performance tests for a simple web app for managing list of TODOs.

This set of tests allows to define a maximal throughput of the application and control performance degradation on every change.

## Prerequisites
- Docker (native for Linux, Docker Desktop for Windows)
- Java 17
- Gradle 8.x

## Application under test
The image of the tested application is located in [app](/app) folder.

Before running tests the image should be loaded to Docker using the following command:

`docker load -i ./app/todo-app.tar`

To start a container locally:

`docker run -d -p 8080:4242 --name todo-app todo-app:latest`

Application will be available at http://localhost:8080

## Non-functional requirements (NFR)
All non-functional requirements are stored in the [configuration file](src/gatling/resources/configuration.yaml) (`nfr` section)
and can be easily modified or added during application evolution.

As for now, they are:
- `maxResponseTime` - upper threshold for response time (milliseconds)
- `perc95ResponseTime` - p95 threshold for response time (milliseconds)
- `failedRequests` - upper threshold for failed requests (percents)

These requirements are automatically asserted when running stability tests to detect performance degradation.

## Test types

### Maximal performance
Maximal performance test (also known as stress test) is designed to find a point of application denial.

Load is gradually increased allowing to find a range of requests where application performance starts to degrade and, finally, falls to zero.

### Stability
Stability test (also known as load test) is designed to test application performance during long period of time with under-maximal load.

## Run tests
Application under test should be running in a separate container and be available (see [Application under test](#application-under-test)).

To run maximal performance test:

`./gradlew gatlingRun --simulation todo.simulations.MaxPerformance`

To run stability test:

`./gradlew gatlingRun --simulation todo.simulations.Stability`

### Test parameters
Performance test parameters can be passed via environment variables or specified directly in the [configuration file](src/gatling/resources/configuration.yaml) (`gatling` section).

List of available variables:
- `intensity` - maximal number of users on the last load stage (equals to requests per second)
- `rampDuration` - time for ramp-up stage (seconds)
- `stageDuration` - time for load stage (seconds)
- `stages` - number of load stages (applicable only for max performance test)

## Test reports
Test execution reports can be found in `/build/reports/gatling/**/index.html`.

When running on CI test reports are uploaded as job artifacts and can be downloaded after job completed.
