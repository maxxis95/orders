package com.rohlik.orders.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LowQuantityProduct
{
    private ProductDTO product;
    private int missingQuantity;
}
