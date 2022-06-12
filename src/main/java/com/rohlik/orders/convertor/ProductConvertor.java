package com.rohlik.orders.convertor;

import java.util.List;

import org.mapstruct.Mapper;

import com.rohlik.orders.dto.ProductDTO;
import com.rohlik.orders.model.ProductEntity;

@Mapper(componentModel = "spring")
public interface ProductConvertor
{
    ProductEntity productToProductEntity(ProductDTO product);

    ProductDTO productEntityToProduct(ProductEntity product);

    List<ProductEntity> productsToProductEntities(List<ProductDTO> products);

    List<ProductDTO> productEntitiesToProducts(List<ProductEntity> products);
}
