package com.rohlik.orders.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.rohlik.orders.dto.OrderDTO;
import com.rohlik.orders.repository.ProductRepository;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
@Data
public class OrderValidator implements Validator
{
    private final ProductRepository productRepository;

    private static final String PRODUCT_ID = "productID";
    private static final String PRODUCTS = "products";
    private static final String PRODUCT_QUANTITY = "order.product.quantity";

    public static final String ERROR_CODE_NOT_FOUND = "NotFound";
    public static final String ERROR_CODE_TOO_LOW = "TooLow";

    @Override
    public boolean supports(Class<?> clazz)
    {
        return OrderDTO.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors)
    {
        var order = (OrderDTO) target;
        for (var i = 0; i < order.getProducts().size(); i++)
        {
            errors.pushNestedPath(PRODUCTS + "[" + i + "]");
            var product = productRepository.findById(order.getProducts().get(i).getProductID());
            if (!product.isPresent())
            {
                errors.rejectValue(PRODUCT_ID, ERROR_CODE_NOT_FOUND,
                    "ID: " + order.getProducts().get(i).getProductID() + " doesn't exist");
            }
            errors.popNestedPath();
        }
    }
}
