package com.rohlik.orders.exception;

import java.util.List;

import com.rohlik.orders.dto.LowQuantityProduct;

import lombok.Data;

@Data
public class LowProductStockException extends Exception
{
    private final List<LowQuantityProduct> lowQuantityProducts;
    private final String message = "Low stock quantity";

    public LowProductStockException(List<LowQuantityProduct> lowQuantityProducts)
    {
        super();
        this.lowQuantityProducts = lowQuantityProducts;

    }
}
