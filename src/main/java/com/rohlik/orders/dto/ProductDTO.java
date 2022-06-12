package com.rohlik.orders.dto;

import java.math.BigDecimal;
import java.util.UUID;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class ProductDTO
{
    private UUID id;
    @NotNull
    private String name;
    @NotNull
    private Integer quantity;
    @NotNull
    private BigDecimal price;
    private String description;
}
