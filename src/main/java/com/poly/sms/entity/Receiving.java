package com.poly.sms.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Receiving")
public class Receiving {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "receiving_id")
    private Integer receivingId;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Column(name = "supplier", nullable = false, columnDefinition = "NVARCHAR(50)")
    private String supplier;

    @ManyToOne
    @JoinColumn(name = "lot_id", nullable = false)
    private InventoryTransaction inventoryTransaction;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;
}
