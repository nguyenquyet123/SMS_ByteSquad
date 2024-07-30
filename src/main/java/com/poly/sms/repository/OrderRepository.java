package com.poly.sms.repository;

import com.poly.sms.entity.Order;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    // List<Order> findByOrderTypeAndOrderStatus(String orderType, Integer orderStatus);
    Order findByOrderId(Integer orderId);

    @Query("SELECT o FROM Order o WHERE o.orderType = :orderType AND o.orderStatus = :orderStatus")
    List<Order> findOrdersByTypeAndStatus(@Param("orderType") String orderType, @Param("orderStatus") Integer orderStatus);

    @Query("SELECT o FROM Order o WHERE o.orderStatus = :orderStatus")
    List<Order> findOrdersByStatus(@Param("orderStatus") Integer orderStatus);
}
