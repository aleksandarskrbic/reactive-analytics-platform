#!/bin/bash

# Run Generator
cd ../event-generator/ && ./mvnw clean compile quarkus:dev &
