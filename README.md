# Overview
This is IDS (Index Data Search). A REST API app to facilitate discovery and maintaining of Down Jones Index data.

It is built using SpringBoot and Spring Web (RESTful).

# API documentation
Swagger is loaded by default at 1001 port. This will show all available REST API details.

# Future improvements or pending work
- Move to Spring WebFlux (reactive stack).
- Add JUnit integration tests for JPA
- Improve documentation
- Replace Stock entity String attributes by its correct types  

# How to run
    git clone https://github.com/silviocosta/ids.git
    cd ../ids/
    ./gradlew bootRun
