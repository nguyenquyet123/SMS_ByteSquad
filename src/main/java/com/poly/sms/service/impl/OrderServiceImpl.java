package com.poly.sms.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.poly.sms.entity.Order;
import com.poly.sms.entity.OrderDetail;
import com.poly.sms.entity.Product;
import com.poly.sms.repository.OrderDetailRepository;
import com.poly.sms.repository.OrderRepository;
import com.poly.sms.repository.ProductRepository;
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

    @Autowired
    private ProductRepository productRepository;

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

    @Override
    public List<Order> getOrdersNhapHang() {
        return orderRepository.findOrdersByTypeAndStatus("Đơn Nhập",1);
    }

    @Override
    public Order update(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public List<Order> getOrdersChuyenHang() {
        return orderRepository.findOrdersByStatus(2);
    }

    @Override
    public Order updateProdOrder(Order order) {
        Order savedOrder = orderRepository.save(order);
        for (OrderDetail detail : order.getOrderDetails()) {
            Product product = productRepository.findById(detail.getProduct().getProductId()).orElseThrow(() -> new RuntimeException("Product not found"));

            if (!product.getBranch().equals(order.getBranch())) {
                throw new RuntimeException("Product does not belong to the order's branch");
            }

            if (order.getOrderType().equals("Đơn Nhập")) {
                product.setQuantity(product.getQuantity() + detail.getQuantity());
                product.setGiaNhap(detail.getImport_price());
                product.setUnitPrice(detail.getPrice());
            } else if (order.getOrderType().equals("Đơn Xuất")) {
                product.setQuantity(product.getQuantity() - detail.getQuantity());
            }

            productRepository.save(product);
        }
        return savedOrder;
    }

    @Override
    public List<Order> getOrdersHoaDon() {
        return orderRepository.findOrdersByStatus(3);
    }
}
