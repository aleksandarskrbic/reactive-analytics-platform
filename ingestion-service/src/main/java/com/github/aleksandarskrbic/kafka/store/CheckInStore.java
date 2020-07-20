package com.github.aleksandarskrbic.kafka.store;

import java.util.Optional;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import com.github.aleksandarskrbic.kafka.model.Aggregate;
import com.github.aleksandarskrbic.kafka.pipeline.AggregationPipeline;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.state.QueryableStoreTypes;
import org.apache.kafka.streams.state.ReadOnlyKeyValueStore;
import io.smallrye.mutiny.Uni;

@ApplicationScoped
public class CheckInStore {

    @Inject
    private KafkaStreams kafkaStreams;

    public Uni<Optional<Aggregate>> findById(final Long id) {
        return store().onItem().apply(store -> Optional.ofNullable(store.get(id)));
    }

    private Uni<ReadOnlyKeyValueStore<Long, Aggregate>> store() {
        while (true) {
            return Uni.createFrom().item(kafkaStreams.store(AggregationPipeline.STORE, QueryableStoreTypes.keyValueStore()));
        }
    }
}
