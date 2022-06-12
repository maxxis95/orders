package com.rohlik.orders.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.validation.constraints.NotNull;

import org.springframework.transaction.annotation.Transactional;

import com.rohlik.orders.dto.ProductDTO;
import com.rohlik.orders.model.ProductEntity;

public interface ProductService
{
    List<ProductDTO> findAllProducts();

    Optional<ProductDTO> findById(@NotNull UUID id);

    @Transactional
    ProductEntity saveProduct(@NotNull ProductDTO productDTO);

    @Transactional
    ProductDTO updateProduct(@NotNull UUID id, @NotNull ProductDTO productDTO);

    void deleteProduct(@NotNull UUID id);
}
