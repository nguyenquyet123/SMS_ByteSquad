package com.poly.sms.repository;

import com.poly.sms.entity.Product;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    List<Product> findTop6ByOrderByDiscountDesc();

    @Query("SELECT p FROM Product p WHERE p.category.categoryId = ?1")
    Page<Product> findProductsByCategoryId(int keywords, Pageable pageable);

    @Query("SELECT p FROM Product p WHERE p.productName LIKE %?1%")
    Page<Product> findProductByKeywords(String keywords, Pageable pageable);

    @Query("SELECT p FROM Product p WHERE p.productName LIKE %?1%")
    Page<Product> findByProductNameContaining(String productName, Pageable pageable);

    

}
