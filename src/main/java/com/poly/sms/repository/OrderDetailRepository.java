package com.poly.sms.repository;

import com.poly.sms.entity.OrderDetail;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Integer> {
    @Query("SELECT od.product, SUM(od.quantity) as totalQuantity " +
            "FROM OrderDetail od " +
            "GROUP BY od.product " +
            "ORDER BY totalQuantity DESC")
    List<Object[]> findTopOrderedProducts();

    List<OrderDetail> findByOrderOrderId(Integer orderId);
}
