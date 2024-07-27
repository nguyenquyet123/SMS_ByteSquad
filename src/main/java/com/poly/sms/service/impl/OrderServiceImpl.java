package com.poly.sms.service.impl;

<<<<<<< HEAD
=======
import com.fasterxml.jackson.core.type.TypeReference;
>>>>>>> thinhpt
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.poly.sms.entity.Order;
import com.poly.sms.entity.OrderDetail;
import com.poly.sms.repository.OrderDetailRepository;
import com.poly.sms.repository.OrderRepository;
import com.poly.sms.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public Optional<Order> findById(Integer id) {
        return orderRepository.findById(id);
    }

    @Override
    public Order save(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public void deleteById(Integer id) {
        orderRepository.deleteById(id);
    }

    @Override
    public Order create(JsonNode orderData) {
        ObjectMapper mapper = new ObjectMapper();
        Order order = mapper.convertValue(orderData, Order.class);
        orderRepository.save(order);

        TypeReference<List<OrderDetail>> type = new TypeReference<List<OrderDetail>>() {};
        List<OrderDetail> details = mapper.convertValue(orderData.get("orderDetails"), type)
            .stream().peek(d -> d.setOrder(order)).collect(Collectors.toList());
        orderDetailRepository.saveAll(details);

        return order;
    }
}
