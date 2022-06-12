package com.rohlik.orders.scheduler;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.rohlik.orders.model.EOrderStatus;
import com.rohlik.orders.model.ProductEntity;
import com.rohlik.orders.repository.OrderRepository;
import com.rohlik.orders.repository.ProductRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class OrderCheckScheduler
{

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    @Value("${orders.minutes-till-cancel}")
    private Integer minutesTillCancel;

    @Scheduled(fixedRate = 20000, initialDelay = 40000)
    @Transactional
    public void checkOrders()
    {
        var expirationDateTime = LocalDateTime.now().minus(minutesTillCancel, ChronoUnit.MINUTES);
        var expiredOrders = orderRepository.findAllByCreatedBefore(expirationDateTime);
        if (expiredOrders.isEmpty())
        {
            return;
        }
        expiredOrders.forEach(k -> k.setStatus(EOrderStatus.CANCELED));
        orderRepository.saveAll(expiredOrders);

        var orderProducts = expiredOrders.stream().map(k -> k.getProducts()).flatMap(x -> x.stream()).toList();
        var productQuantity = new HashMap<ProductEntity, Integer>();
        orderProducts.forEach(m -> {
            var value = productQuantity.get(m.getProduct());
            if (value != null)
            {
                value += m.getQuantity();
            }
            else
            {
                value = m.getQuantity();
            }
            productQuantity.put(m.getProduct(), value);
        });
        var updateBatchProducts = new ArrayList<ProductEntity>();
        productQuantity.forEach((k, v) -> {
            k.setQuantity(k.getQuantity() + v);
            updateBatchProducts.add(k);
        });
        productRepository.saveAll(updateBatchProducts);
    }

}
