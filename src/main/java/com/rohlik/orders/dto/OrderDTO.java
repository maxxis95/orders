package com.rohlik.orders.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import com.rohlik.orders.model.EOrderStatus;

import lombok.Data;

@Data
public class OrderDTO
{
    private UUID id;
    @NotNull
    private List<OrderProductDTO> products;

    private LocalDateTime orderDate;
    private LocalDateTime lastModified;
    private LocalDateTime payedDate;

    private BigDecimal orderAmount;
    private EOrderStatus status;
    @NotNull
    @Email
    private String email;
}
