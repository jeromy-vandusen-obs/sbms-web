# Spring Boot Microservices - Web Application

## Jenkins Job Configuration

1. On the main Jenkins dashboard page, click "New Item".
2. Enter "sbms-web" in the "name" field, select "Pipeline", and click "OK".
3. Scroll down to the "Pipeline" section and in "Definition" select "Pipeline script from SCM".
4. In "SCM", select "Git".
5. In "Repository URL", enter "https://github.com/jeromy-vandusen-obs/sbms-web.git".
6. Deselect "Lightweight checkout" and click "Save".

## Profiles

To run locally for development purposes, use the `dev` profile.

To run in a Docker swarm, use the `swarm` profile.

## Environment variables

* EUREKA_INSTANCE_LIST: A comma-separated list of Eureka discovery service URLs. The default value is
`http://localhost:8761/eureka/`.

## Endpoints

* `/dependencyHealth`: provides statuses for all remote services that this application is dependent on, along with an
overall status. The overall status will only be `UP` if all dependent services report a status of `UP`. The statuses
of the dependent services are determined by calling the `/actuator/health` endpoint on those services. Therefore, if
a dependent service is also dependent on another service, then that service should have its own `/dependencyHealth`
endpoint, and this endpoint should call that instead. Currently, there are no such transitive dependencies, so this
service can safely rely solely on the `/actuator/health` endpoint for now.
