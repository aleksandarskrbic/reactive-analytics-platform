# Reactive Anaytics Platform - Quarkus Hackaton

Example of analytics platform that tracks user check-ins on specific locations and provides real-time dashboards backed by Quarkus, Postgres, Kafka-Streams, and RocksDB
following reactive principles.

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
3. Run: ```./run-pg.sh```
4. After that run: ```./run-kafka.sh```
5. Finally run all services with this command: ```./run-services.sh```
