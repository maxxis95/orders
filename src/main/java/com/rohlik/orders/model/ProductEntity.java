package com.rohlik.orders.model;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Table(name = "roh_product")
@Data
@ToString
@EqualsAndHashCode
public class ProductEntity
{
    @Id
    @Type(type="uuid-char")
    private UUID id;

    @NotNull
    @Column(nullable = false)
    private String name;

    @NotNull
    @Column(nullable = false)
    private Integer quantity;

    @NotNull
    @Column(nullable = false)
    private BigDecimal price;
    private String description;

    @PrePersist
    public void prePersist()
    {
        if (this.getId() == null)
        {
            this.setId(UUID.randomUUID());
        }
    }
}
