#!/bin/bash

# Run Services
cd ../ingestion-service/ && ./mvnw clean compile quarkus:dev &
cd ../location-service/ && ./mvnw clean compile quarkus:dev &
cd ../query-service/ && ./mvnw clean compile quarkus:dev &
