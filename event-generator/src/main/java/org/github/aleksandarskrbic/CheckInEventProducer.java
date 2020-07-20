package org.github.aleksandarskrbic;

import java.util.concurrent.TimeUnit;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import org.eclipse.microprofile.reactive.messaging.Outgoing;
import io.reactivex.Flowable;
import io.smallrye.reactive.messaging.kafka.KafkaRecord;
import io.smallrye.reactive.messaging.kafka.OutgoingKafkaRecord;

@ApplicationScoped
public class CheckInEventProducer {

    @Inject
    private CheckInEventGenerator generator;

    @Outgoing("check-in-generated")
    public Flowable<OutgoingKafkaRecord<Long, CheckInEvent>> produce() {
        return Flowable.interval(75, TimeUnit.MILLISECONDS)
            .map(tick -> generator.randomEvent())
            .map(event -> KafkaRecord.of(event.getLocationId(), event));
    }
}