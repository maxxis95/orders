package com.rohlik.orders.convertor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;

import com.rohlik.orders.dto.OrderDTO;
import com.rohlik.orders.dto.OrderProductDTO;
import com.rohlik.orders.model.OrderEntity;
import com.rohlik.orders.model.OrderProductEntity;
import com.rohlik.orders.repository.ProductRepository;

@Mapper(componentModel = "spring")
public abstract class OrderConvertor
{
    @Autowired
    private ProductRepository productRepository;

    @Mapping(target = "products", ignore = true)
    public abstract OrderEntity orderDTOtoOrderEntity(OrderDTO orderDTO);

    @AfterMapping
    public void setProducts(OrderDTO source, @MappingTarget OrderEntity target)
    {
        var orderProducts = new ArrayList<OrderProductEntity>();
        BigDecimal amount = new BigDecimal(0);

        for (var k : source.getProducts())
        {
            var orderProduct = new OrderProductEntity();
            orderProduct.setOrder(target);
            //TODO maybe set order here if doesn't want to store in db automatically order
            orderProduct.setQuantity(k.getQuantity());
            var product = productRepository.findById(k.getProductID())
                .orElseThrow(() -> new NoSuchElementException("Product id: " + k.getProductID() + " not exist"));
            orderProduct.setProduct(product);
            amount = amount.add(product.getPrice().multiply(new BigDecimal(k.getQuantity())));
            orderProducts.add(orderProduct);
        }
        target.setProducts(orderProducts);
        target.setOrderAmount(amount);
    }

    public abstract List<OrderDTO> orderEntitiesToOrderDTOs(List<OrderEntity> orderEntities);

    public abstract OrderDTO orderEntityToOrderDTO(OrderEntity orderEntity);

    @Mapping(target = "productID", source = "product.id")
    public abstract OrderProductDTO orderProductEntityToOrderProductDTO(OrderProductEntity orderProductEntity);

}
