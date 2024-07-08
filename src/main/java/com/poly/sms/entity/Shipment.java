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
@Table(name = "Shipments")
public class Shipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shipment_id")
    private Integer shipmentId;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @Column(name = "shipment_date", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp shipmentDate;

    @Column(name = "expected_delivery_date", columnDefinition = "TIMESTAMP")
    private Timestamp expectedDeliveryDate;

    @Column(name = "delivery_status", nullable = false)
    private Integer deliveryStatus;

    @Column(name = "shipping_address", nullable = false, columnDefinition = "TEXT")
    private String shippingAddress;

    @Column(name = "shipping_name", nullable = false, length = 50, columnDefinition = "NVARCHAR")
    private String shippingName;

    @Column(name = "shipping_phone", nullable = false, length = 20, columnDefinition = "NVARCHAR")
    private String shippingPhone;

    @Column(name = "billing_address", nullable = false, columnDefinition = "TEXT")
    private String billingAddress;
}
