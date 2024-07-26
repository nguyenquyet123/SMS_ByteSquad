package com.poly.sms.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.poly.sms.entity.Order;

import java.util.List;
import java.util.Optional;

public interface OrderService {

    List<Order> findAll();

    Optional<Order> findById(Integer id);

    Order save(Order order);

    void deleteById(Integer id);

    Order create(JsonNode orderData);
}
