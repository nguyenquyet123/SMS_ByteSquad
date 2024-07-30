package com.poly.sms.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.poly.sms.entity.Order;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    Order update(Order order);

    Order updateProdOrder(Order order);

    List<Order> getOrdersHoaDon();

    List<Order> getOrdersNhapHang();

    List<Order> getOrdersChuyenHang();

    List<Order> findAll();

    Optional<Order> findById(Integer id);

    Order create(JsonNode orderData);

    Order save(Order order);

    void deleteById(Integer id);

}
