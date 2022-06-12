package com.rohlik.orders.model;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
@ToString
@EqualsAndHashCode
public class OrderProductEntity
{
    @Id
    @Type(type = "uuid-char")
    UUID id;

    @ManyToOne()
    @JoinColumn(name = "product_id")
    private ProductEntity product;

    @ManyToOne()
    @JsonBackReference
    @JoinColumn(name = "order_id")
    private OrderEntity order;

    @NotNull
    private Integer quantity;

    @PrePersist
    void prePersist()
    {
        this.id = UUID.randomUUID();
    }

}
