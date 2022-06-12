package com.rohlik.orders.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.validation.constraints.NotNull;

import org.springframework.transaction.annotation.Transactional;

import com.rohlik.orders.dto.OrderDTO;
import com.rohlik.orders.exception.LowProductStockException;
import com.rohlik.orders.model.EOrderStatus;

public interface OrderService
{
    List<OrderDTO> findAllOrders();

    Optional<OrderDTO> findById(@NotNull UUID id);

    @Transactional
    void saveOrder(@NotNull OrderDTO orderDTO) throws LowProductStockException;

    @Transactional
    OrderDTO updateStatus(@NotNull UUID id, @NotNull EOrderStatus status);

}
