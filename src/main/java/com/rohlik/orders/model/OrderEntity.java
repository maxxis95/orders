package com.rohlik.orders.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Type;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Table(name = "roh_order")
@Data
@ToString
@EqualsAndHashCode
public class OrderEntity
{
    @Id
    @Type(type = "uuid-char")
    private UUID id;

    @NotNull
    @OneToMany(mappedBy = "order", cascade = CascadeType.PERSIST)
    @Column(nullable = false)
    private List<OrderProductEntity> products;

    private LocalDateTime orderDate;
    private LocalDateTime lastModified;
    private LocalDateTime payedDate;

    @NotNull
    @Column(nullable = false)
    private BigDecimal orderAmount;
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EOrderStatus status;
    @Email
    @NotNull
    @Column(nullable = false)
    private String email;

    @PrePersist
    public void prePersist()
    {
        if (this.getId() == null)
        {
            this.setId(UUID.randomUUID());
        }
        if (this.getOrderDate() == null)
        {
            this.setOrderDate(LocalDateTime.now());
        }
        this.setStatus(EOrderStatus.CREATED);
    }

    @PreUpdate
    public void preUpdate()
    {
        this.setLastModified(LocalDateTime.now());
    }

}
