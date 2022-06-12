package com.rohlik.orders.controller;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.rohlik.orders.annotation.ERepo;
import com.rohlik.orders.annotation.ParamConstraint;
import com.rohlik.orders.dto.ProductDTO;
import com.rohlik.orders.service.ProductService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/product")
@RestController
@Validated
public class ProductController
{
    private final ProductService productService;

    @PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    void saveProduct(@NotNull @Valid @RequestBody ProductDTO productDTO)
    {
        productService.saveProduct(productDTO);
    }

    @GetMapping(value = "/fetchAll")
    ResponseEntity<List<ProductDTO>> fetchAll()
    {
        return ResponseEntity.ok(productService.findAllProducts());
    }

    @PutMapping("/{id}")
    ResponseEntity<ProductDTO> updateProduct(@Valid @ParamConstraint(repo = ERepo.PRODUCT) @PathVariable(value = "id") @NotNull UUID id,
        @Valid @RequestBody ProductDTO product) throws IllegalArgumentException
    {

        return ResponseEntity.accepted().body(productService.updateProduct(id, product));
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    void deleteProduct(@NotNull @ParamConstraint(repo = ERepo.PRODUCT) @Valid @PathVariable(value = "id") UUID id)
    {
        productService.deleteProduct(id);
    }
}
