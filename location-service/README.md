# Location Service

```Location Service``` is service that contains details about locations.

```Location Service``` exposes HTTP API with CRUD operations on location entity.

Whole service is implemented in a reactive manner(from database access to endpoints), using [SmallRye Mutiny](https://smallrye.io/smallrye-mutiny/).

## Location Model

```java
public class Location {

    private Long id;
    private String name;
    private String description;
  }
```

```Location Service``` is used by ```Query Service``` to enrich data from ```Ingestion Service```.
