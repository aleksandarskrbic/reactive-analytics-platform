# Event Generator

```Event Generator``` is service that is used to generate random user data that represent user check-in on some location.
Events are published on ```check-in``` Kafka topic.

For Kafka integration [SmallRye Reactive Messaging](https://smallrye.io/smallrye-reactive-messaging/smallrye-reactive-messaging/2.1/index.html) with Kafka Connector is used.

JSON serialization is used for Kafka records.

## CheckIn Event

```java
public class CheckInEvent {

    private Long locationId;
    private String device;
    
 }
```

### Fields:
  1. ```locationId``` is id of location, and we will use ```location-service``` in order to get location details based on ```locationId```.
  2. ```device``` represents device that user used to chec-in. Device can be ```LAPTOP``` or ```PHONE```.
