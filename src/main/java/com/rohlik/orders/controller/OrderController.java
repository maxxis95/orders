package com.rohlik.orders.controller;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.rohlik.orders.annotation.ERepo;
import com.rohlik.orders.annotation.ParamConstraint;
import com.rohlik.orders.dto.LowQuantityProduct;
import com.rohlik.orders.dto.OrderDTO;
import com.rohlik.orders.exception.LowProductStockException;
import com.rohlik.orders.model.EOrderStatus;
import com.rohlik.orders.service.OrderService;
import com.rohlik.orders.validator.OrderValidator;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/order")
@Validated
public class OrderController extends RohlikRootController
{
    private final OrderService orderService;
    private final OrderValidator orderValidator;

    @InitBinder
    protected void initBinder(WebDataBinder binder)
    {
        binder.addValidators(orderValidator);
    }

    @PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    void saveOrder(@NotNull @Valid @RequestBody OrderDTO orderDTO) throws LowProductStockException
    {
        orderService.saveOrder(orderDTO);
    }

    @GetMapping(value = "/fetchAll")
    ResponseEntity<List<OrderDTO>> fetchAll()
    {
        return ResponseEntity.ok(orderService.findAllOrders());
    }

    @PutMapping("/pay/{id}")
    ResponseEntity<OrderDTO> payOrder(
        @Valid @ParamConstraint(repo = ERepo.ORDER) @PathVariable(value = "id") @NotNull UUID id)
        throws IllegalArgumentException
    {

        return ResponseEntity.accepted().body(orderService.updateStatus(id, EOrderStatus.PAYED));
    }

    @PutMapping("/cancel/{id}")
    ResponseEntity<OrderDTO> cancelOrder(
        @Valid @ParamConstraint(repo = ERepo.ORDER) @PathVariable(value = "id") @NotNull UUID id)
        throws IllegalArgumentException
    {
        return ResponseEntity.accepted().body(orderService.updateStatus(id, EOrderStatus.CANCELED));
    }

    @ExceptionHandler({LowProductStockException.class})
    public ResponseEntity<List<LowQuantityProduct>> handleLowProductStockException(LowProductStockException ex)
    {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .contentType(MediaType.APPLICATION_JSON)
            .body(ex.getLowQuantityProducts());
    }
}

