package com.gmail.robertosrjr.adapter.out.producer.kafka;

import com.gmail.robertosrjr.adapter.in.controller.rest.PurchaseResource;
import com.gmail.robertosrjr.application.dto.PurchaseDto;
import com.google.gson.Gson;
import io.smallrye.reactive.messaging.kafka.Record;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.UUID;

@ApplicationScoped
public class PurchaseProducer {

    private final Logger logger = Logger.getLogger(PurchaseProducer.class);

    //@Inject
    @Channel("purchase-out")
    Emitter<Record<String, String>> emitter;

    public void sendPurchaseToKafka(PurchaseDto purchase) {

        String key = UUID.randomUUID().toString();
        Gson gson = new Gson();
        logger.infof("sendPurchaseToKafka: ", key, gson.toJson(purchase));
        try {
            purchase.setId(UUID.randomUUID().toString());
            emitter.send(Record.of(key, gson.toJson(purchase)));
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e);
        }
    }
}
