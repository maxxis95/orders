package com.rohlik.orders.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rohlik.orders.convertor.ProductConvertor;
import com.rohlik.orders.dto.ProductDTO;
import com.rohlik.orders.model.ProductEntity;
import com.rohlik.orders.repository.ProductRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService
{
    private final ProductRepository productRepository;
    private final ProductConvertor productConvertor;

    @Override
    public List<ProductDTO> findAllProducts()
    {
        var list = new ArrayList<ProductEntity>();
        productRepository.findAll().forEach(list::add);

        return Optional.of(list).map(productConvertor::productEntitiesToProducts).orElse(
            Collections.emptyList());
    }

    @Override
    public Optional<ProductDTO> findById(@NotNull UUID id)
    {
        return productRepository.findById(id).map(productConvertor::productEntityToProduct);
    }

    @Override
    public ProductEntity saveProduct(ProductDTO productDTO)
    {
        var product = productRepository.saveAndFlush(productConvertor.productToProductEntity(productDTO));

        return product;
    }

    @Override
    @Transactional
    public ProductDTO updateProduct(UUID id, ProductDTO productDTO)
    {
        var productExists =
            productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Product not found by: " + id));

        var product = productConvertor.productToProductEntity(productDTO);
        product.setId(productExists.getId());
        return Optional.of(productRepository.save(product)).map(productConvertor::productEntityToProduct).orElse(null);
    }

    @Override
    @Transactional
    public void deleteProduct(UUID id)
    {
        var product = productRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("ID: " + id + " doesn't exist"));
        productRepository.delete(product);
    }

}
