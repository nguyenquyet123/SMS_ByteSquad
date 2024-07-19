package com.poly.sms.service;

import com.poly.sms.entity.Product;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {

    List<Product> findAll();

    Optional<Product> findById(Integer id);

    Product save(Product product);

    void deleteById(Integer id);

    List<Product> findTop6ByOrderByDiscountDesc();

    Page<Product> findAllPage(Pageable pageable);

    Page<Product> findProductsByCategoryId(int keywords, Pageable pageable);

    Page<Product> findProductByKeywords(String keywords, Pageable pageable);

    Page<Product> findByProductNameContaining(String productName, Pageable pageable);

    List<Product> findProductsByCategoryId(int keywords);
}
