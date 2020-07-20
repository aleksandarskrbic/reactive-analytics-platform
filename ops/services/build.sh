#!/bin/bash

cd ../../event-generator/ && ./mvnw clean package -f pom.xml && docker build -f src/main/docker/Dockerfile -t quarkus/event-generator .
cd ../ingestion-service/ && ./mvnw clean package -f pom.xml && docker build -f src/main/docker/Dockerfile -t quarkus/ingestion-service .
cd ../location-service/ && ./mvnw clean package -f pom.xml && docker build -f src/main/docker/Dockerfile -t quarkus/location-service .
cd ../query-service/ && ./mvnw clean package -f pom.xml && docker build -f src/main/docker/Dockerfile -t quarkus/query-service .


