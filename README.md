# Reactive Anaytics Platform - Quarkus Hackaton

Example of analytics platform that tracks user check-ins on specific locations and provides real-time dashboards backed by Quarkus, Postgres, Kafka-Streams, and RocksDB
following reactive principles.

The system represents a microservices architecture and some common patterns related to microservices are showed like:
1. When and how to use Kafka, Kafka Streams and Kafka State Stores.
2. How to communicate with other microservices via HTTP in a resilient way.
3. How to compose multiple asynchronous computations and in general how to implement service in a reactive manner from end to end.

Platform consists of multiple microservices:
1. [Event Generator](https://github.com/aleksandarskrbic/reactive-anaytics-platform/tree/master/event-generator) is used to produce random events that represents user data. More details [here](https://github.com/aleksandarskrbic/reactive-anaytics-platform/tree/master/event-generator).
2. [Ingestion Service](https://github.com/aleksandarskrbic/reactive-anaytics-platform/tree/master/ingestion-service) is responsible for ingestion and aggregation of user data. More details [here](https://github.com/aleksandarskrbic/reactive-anaytics-platform/tree/master/ingestion-service).
3. [Location Service](https://github.com/aleksandarskrbic/reactive-anaytics-platform/tree/master/location-service) is service that contains details about locations. More details [here](https://github.com/aleksandarskrbic/reactive-anaytics-platform/tree/master/location-service).
4. [Query Service](https://github.com/aleksandarskrbic/reactive-anaytics-platform/tree/master/query-service) is used to query aggregated data and location details. More details [here](https://github.com/aleksandarskrbic/reactive-anaytics-platform/tree/master/query-service).
5. [Dashboad UI](https://github.com/aleksandarskrbic/reactive-anaytics-platform/tree/master/web-ui) can be used to retrieve data from Query Service. More details [here](https://github.com/aleksandarskrbic/reactive-anaytics-platform/tree/master/web-ui)


## System Architecture

![alt text](https://github.com/aleksandarskrbic/reactive-anaytics-platform/blob/master/system-architecture.png)

## How to run:
1. Git pull and cd into ```./ops``` directory
2. Run this command: ``` docker network create kafka-network```
3. Run UI: ```./run-ui.sh```
4. Run PostgreSQL: ```./run-pg.sh```
5. Run Kafka and Zookeper: ```./run-kafka.sh``` make sure that kafka is started properly, if not just kill process(ctr+c) and run script again
6. Run Event Generator: ```./run-generator.sh```
7. Finally run all services with this command: ```./run-services.sh```
8. Web UI is available at http://localhost:3000 or you can use query-service directly via HTTP
