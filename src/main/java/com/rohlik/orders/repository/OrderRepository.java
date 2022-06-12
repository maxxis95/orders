package com.rohlik.orders.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rohlik.orders.model.OrderEntity;
import com.rohlik.orders.model.ProductEntity;

@Repository
public interface OrderRepository extends CrudRepository<OrderEntity, UUID>
{
    @Query("select o from OrderEntity o where o.orderDate < :expirationDate and o.status = 'CREATED'")
    List<OrderEntity> findAllByCreatedBefore(@Param("expirationDate") LocalDateTime expirationDate);
}
