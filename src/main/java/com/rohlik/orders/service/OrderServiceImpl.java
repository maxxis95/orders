package com.rohlik.orders.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.rohlik.orders.convertor.OrderConvertor;
import com.rohlik.orders.convertor.ProductConvertor;
import com.rohlik.orders.dto.LowQuantityProduct;
import com.rohlik.orders.dto.OrderDTO;
import com.rohlik.orders.exception.LowProductStockException;
import com.rohlik.orders.model.EOrderStatus;
import com.rohlik.orders.model.OrderEntity;
import com.rohlik.orders.repository.OrderRepository;
import com.rohlik.orders.repository.ProductRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService
{
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final OrderConvertor orderConvertor;
    private final ProductConvertor productConvertor;

    @Override
    public List<OrderDTO> findAllOrders()
    {
        var list = new ArrayList<OrderEntity>();
        orderRepository.findAll().forEach(list::add);

        return Optional.of(list).map(orderConvertor::orderEntitiesToOrderDTOs).orElse(
            Collections.emptyList());
    }

    @Override
    public Optional<OrderDTO> findById(UUID id)
    {
        return Optional.empty();
    }

    @Override
    public void saveOrder(OrderDTO orderDTO) throws LowProductStockException
    {
        checkQuantity(orderDTO);
        var orderEntity = orderConvertor.orderDTOtoOrderEntity(orderDTO);
        orderRepository.save(orderEntity);
        orderEntity.getProducts().forEach(k -> {
            var product = k.getProduct();
            product.setQuantity(product.getQuantity() - k.getQuantity());
            productRepository.save(product);
        });

    }

    private void checkQuantity(OrderDTO orderDTO) throws LowProductStockException
    {
        var lowQuantityProducts = new ArrayList<LowQuantityProduct>();
        for (var orderProductDTO : orderDTO.getProducts())
        {
            var product = productRepository.findById(orderProductDTO.getProductID()).get();
            if (orderProductDTO.getQuantity() > product.getQuantity())
            {
                var lowQuantityProduct = new LowQuantityProduct(productConvertor.productEntityToProduct(product),
                    orderProductDTO.getQuantity() - product.getQuantity());
                lowQuantityProducts.add(lowQuantityProduct);
            }
        }
        if (!lowQuantityProducts.isEmpty())
        {
            throw new LowProductStockException(lowQuantityProducts);
        }
    }

    @Override
    public OrderDTO updateStatus(UUID id, EOrderStatus status)
    {
        var order = orderRepository.findById(id);
        order.get().setStatus(status);
        return Optional.of(orderRepository.save(order.get())).map(orderConvertor::orderEntityToOrderDTO).orElse(null);
    }
}
