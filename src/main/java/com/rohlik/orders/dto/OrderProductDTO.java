package com.rohlik.orders.dto;

import java.util.UUID;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class OrderProductDTO
{
    @NotNull
    private UUID productID;
    @NotNull
    private Integer quantity;
}
