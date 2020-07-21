# Ingestion Service

```Ingestion Service``` is used to ingest data from ```check-in``` Kafka topic and specific aggregation of ingested data.
Ingestion and aggregation is implemented using [Kafka Streams](https://kafka.apache.org/documentation/streams/) and aggregates are stored in RocksDB.

Service is running on: ```localhost:9001```

SwaggerUI available at: ```localhost:9001/swagger-ui```

## Aggregate Model

```java
public class Aggregate {

    public static final String PHONE = "PHONE";
    public static final String LAPTOP = "LAPTOP";

    private Long locationId;
    private long count;
    private long phoneCount;
    private long laptopCount;
 }
```

Aggregate contains count of check ins by device type and total count. Data can be queried using [Kafka Streams Interactive Queries](https://docs.confluent.io/current/streams/developer-guide/interactive-queries.html)

## HTTP API exposed by Ingestion Service

<table>
  <tr>
    <td><b>Path</b></td>
    <td><b>Method</b></td>
    <td><b>Summary</b></td>
  </tr>
  <tr>
    <td>/api/check-in/{id}/all</td>
    <td>GET</td>
    <td>Get full check-in data for location with a given {id}</td>
  </tr>
  <tr>
    <td>/api/check-in/{id}/{device}</td>
    <td>GET</td>
    <td>Get check-in count for specific {device} for location with a given {id}</td>
  </tr>
</table>
