package com.poly.sms.service;

import com.poly.sms.entity.OrderDetail;

import java.util.List;
import java.util.Optional;

public interface OrderDetailService {

    List<OrderDetail> saveAllOrderDetails(List<OrderDetail> orderDetails);

    List<OrderDetail> getAllOrderDetailByOrderID(Integer id);

    List<OrderDetail> findAll();

    Optional<OrderDetail> findById(Integer id);

    OrderDetail save(OrderDetail orderDetail);

    void deleteById(Integer id);

    List<Object[]> findTopOrderedProducts();
}
