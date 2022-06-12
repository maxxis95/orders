package com.rohlik.orders.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rohlik.orders.model.ProductEntity;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, UUID>
{
    Optional<ProductEntity> findById(UUID id);
}
