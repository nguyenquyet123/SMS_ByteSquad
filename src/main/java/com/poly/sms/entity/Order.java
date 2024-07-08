package com.poly.sms.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;
import java.sql.Date;

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
@Table(name = "Orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Integer orderId;

    @Column(name = "order_type", nullable = false, length = 10)
    private String orderType;

    @Column(name = "seller", columnDefinition = "NVARCHAR(50)")
    private String seller;

    @Column(name = "order_date", nullable = false)
    private Date orderDate;

    @Column(name = "required_date")
    private Date requiredDate;

    @Column(name = "total_price", nullable = false, precision = 10, scale = 2)
    private BigDecimal totalPrice;

    @Column(name = "ship_address", nullable = false, length = 100, columnDefinition = "NVARCHAR")
    private String shipAddress;

    @Column(name = "billing_address", nullable = false, length = 100, columnDefinition = "NVARCHAR")
    private String billingAddress;

    @Column(name = "order_status", nullable = false)
    private Integer orderStatus;

    @Column(name = "comments", columnDefinition = "TEXT")
    private String comments;

    @ManyToOne
    @JoinColumn(name = "branch_id", nullable = false)
    private Branch branch;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;
}
