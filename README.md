# restaurant-event-service
Set a restaurant open or closed by the API that this microservice exposes, as well as getting the current state of a restaurant

The back-end service requires Java 11. The code is in the ``src/main/java`` directory.

### How to build and execute the back-end?

The docker compose for the project is present in the path:
docker/docker-compose.yml

- Run the following on the command line: (Mac OS)
```bash
cd PATH/TO/PROJECT
- To run zookeeper and kafka in Docker container:
./docker-compose up
./mvnw clean spring-boot:run

```
- Run the following on the command line: (Windows)
```bash
cd PATH/TO/PROJECT
docker-compose up
mvnw.cmd clean spring-boot:run
```
- __Or__ from Intellij IDEA (during the development process): Run spring application (manually selecting in the run menu or just clicking on the play button next to the main method inside the ``de.idealo.international.reservation.Application`` class)

## Technologies that have been used

- [Spring Framework](https://spring.io/projects/spring-framework): Java based enterprise application framework
- [Spring Boot](https://spring.io/projects/spring-boot): Spring based web application framework
- [Maven](https://maven.apache.org/): Software project management and comprehension tool
- [KAFKA](https://kafka.apache.org/): Open-source distributed event streaming platform

# API's Exposed
The following REST API endpoints provides control over the source of truth in JUST EAT Takeaway regarding the restaurant’s open state

- __GET__

    - __/restaurant-event/{restaurantId}__  
      A read endpoint which get an id and return the current status of the restaurant from KAFKA store.

- __POST__

    - __/restaurant-event/update-status__  
      An endpoint for updating the open/close status of restaurant.
      The input to the API is:
        - __id__: ID of the restaurant.
        - __status__: Restaurant Status (OPEN/CLOSE).


## Explanation and thoughts
The first idea was to use H2 as in memory database. However, since Kafka streams can also be used as a in-memory store, we are utilizing the same feature.
