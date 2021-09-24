package com.gmail.robertosrjr.adapter.in.consumer.kafka;

import io.smallrye.reactive.messaging.kafka.Record;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PurchaseConsumer {

    private final Logger logger = Logger.getLogger(PurchaseConsumer.class);

    @Incoming("purchase-in")
    public void receive(Record<String, String> record) {
        logger.infof("Got a movie: %d - %s", record.key(), record.value());
    }
}
