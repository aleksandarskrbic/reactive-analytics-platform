package com.github.aleksandarskrbic.kafka.pipeline;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import com.github.aleksandarskrbic.kafka.model.Aggregate;
import com.github.aleksandarskrbic.kafka.model.CheckInEvent;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.Materialized;
import org.apache.kafka.streams.kstream.Produced;
import org.apache.kafka.streams.state.KeyValueBytesStoreSupplier;
import org.apache.kafka.streams.state.Stores;
import io.quarkus.kafka.client.serialization.JsonbSerde;

@ApplicationScoped
public class AggregationPipeline {

    public static final String STORE = "CHECK_IN";
    public static final String INPUT_TOPIC = "check-in";
    public static final String OUTPUT_TOPIC = "check-in-aggregation";

    @Produces
    public Topology topology() {
        final StreamsBuilder builder = new StreamsBuilder();
        final KeyValueBytesStoreSupplier storeSupplier = Stores.persistentKeyValueStore(STORE);

        final JsonbSerde<CheckInEvent> checkInEventSerde = new JsonbSerde<>(CheckInEvent.class);
        final JsonbSerde<Aggregate> aggregateSerde = new JsonbSerde<>(Aggregate.class);

        builder.stream(INPUT_TOPIC, Consumed.with(Serdes.Long(), checkInEventSerde))
            .groupByKey()
            .aggregate(
                Aggregate::new,
                (key, event, aggregate) -> aggregate.update(event),
                Materialized.<Long, Aggregate>as(storeSupplier)
                       .withKeySerde(Serdes.Long())
                       .withValueSerde(aggregateSerde)
            )
            .toStream()
            .to(OUTPUT_TOPIC, Produced.with(Serdes.Long(), aggregateSerde));

        return builder.build();
    }
}
