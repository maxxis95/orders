package com.rohlik.orders.validator;

import java.util.UUID;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.rohlik.orders.annotation.ERepo;
import com.rohlik.orders.annotation.ParamConstraint;
import com.rohlik.orders.repository.OrderRepository;
import com.rohlik.orders.repository.ProductRepository;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ParamValidator implements ConstraintValidator<ParamConstraint, UUID>
{

    private ERepo repo;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;

    @Override
    public void initialize(ParamConstraint paramConstraint)
    {
        this.repo = paramConstraint.repo();
    }

    @Override
    public boolean isValid(UUID value, ConstraintValidatorContext context)
    {
        if (repo == ERepo.PRODUCT)
        {
            if (productRepository.findById(value).isPresent())
            {
                return true;
            }
            return false;
        }
        else if (repo == ERepo.ORDER)
        {
            if (orderRepository.findById(value).isPresent())
            {
                return true;
            }
            return false;
        }
        return false;
    }

}