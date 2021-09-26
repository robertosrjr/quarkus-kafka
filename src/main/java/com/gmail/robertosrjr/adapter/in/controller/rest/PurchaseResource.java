package com.gmail.robertosrjr.adapter.in.controller.rest;

import com.gmail.robertosrjr.adapter.in.consumer.kafka.PurchaseConsumer;
import com.gmail.robertosrjr.adapter.out.producer.kafka.PurchaseProducer;
import com.gmail.robertosrjr.application.dto.PurchaseDto;
import com.google.gson.Gson;
import org.jboss.logging.Logger;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/purchase")
public class PurchaseResource {

    private final Logger logger = Logger.getLogger(PurchaseResource.class);

    @Inject
    private PurchaseProducer producer;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(PurchaseDto purchase) {

        Gson gson = new Gson();
        logger.infof("purchase: ", gson.toJson(purchase));
        this.producer.sendPurchaseToKafka(purchase);
        return Response.accepted(purchase).build();
    }
}
