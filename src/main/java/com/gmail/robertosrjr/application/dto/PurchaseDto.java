package com.gmail.robertosrjr.application.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.ws.rs.GET;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
public class PurchaseDto {

    private String id;
    private String product;
    private String amount;
    private String note;

}
