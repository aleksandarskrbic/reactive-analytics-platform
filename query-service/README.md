# Query Service

```Query Service``` is only service that is client facing, and it is used to query aggregated data and location details.
This service demosntrates how to:
  1. Use [MicroProfile Rest Client](https://github.com/eclipse/microprofile-rest-client) and query external HTTP services
  2. Use [SmallRye Fault Tolerance](https://smallrye.io/blog/fault-tolerance-4-1/)
  3. Cache data in you microservices using quarkus-cache in comination with [Caffeine](https://github.com/ben-manes/caffeine)
  4. How to combine multiple asynchronous HTTP calls
  5. Exception handling in quarkus
  
Service is running on: ```localhost:9003```

SwaggerUI available at: ```localhost:9003/swagger-ui```
  
## HTTP API exposed by Query Service

<table>
  <tr>
    <td><b>Path</b></td>
    <td><b>Method</b></td>
    <td><b>Summary</b></td>
  </tr>
  <tr>
    <td>/api/query/{id}/full</td>
    <td>GET</td>
    <td>Get full report with location details for location with a given {id}</td>
  </tr>
  <tr>
    <td>/api/query/{id}/{device}</td>
    <td>GET</td>
    <td>Get report for specific {device} with location details for location with a given {id}</td>
  </tr>
</table>
