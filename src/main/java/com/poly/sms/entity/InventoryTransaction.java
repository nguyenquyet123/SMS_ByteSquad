package com.poly.sms.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Inventory_Transactions")
public class InventoryTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lot_id")
    private Integer lotId;

    @Column(name = "transaction_type", nullable = false, length = 10)
    private String transactionType;

    @Column(name = "transaction_date", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp transactionDate;

    @Column(name = "receiver", columnDefinition = "NVARCHAR(50)")
    private String receiver;

    @Column(name = "creator", columnDefinition = "NVARCHAR(50)")
    private String creator;

    @Column(name = "quantity_received", nullable = false)
    private Integer quantityReceived;

    @Column(name = "quantity_shipped", nullable = false)
    private Integer quantityShipped;

    @Column(name = "receiving_location", columnDefinition = "NVARCHAR(100)")
    private String receivingLocation;

    @Column(name = "inventory_shrinkage", nullable = false)
    private Integer inventoryShrinkage;

    @Column(name = "transaction_status", nullable = false)
    private Integer transactionStatus;

    @Column(name = "note", columnDefinition = "TEXT")
    private String note;

    @ManyToOne
    @JoinColumn(name = "branch_id")
    private Branch branch;
}
