#!/bin/bash

# Run Services
cd ../event-generator/ && ./mvnw clean compile quarkus:dev &
cd ../ingestion-service/ && ./mvnw clean compile quarkus:dev &
cd ../location-service/ && ./mvnw clean compile quarkus:dev &
cd ../query-service/ && ./mvnw clean compile quarkus:dev &
