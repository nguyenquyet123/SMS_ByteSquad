package com.poly.sms.service.impl;

import com.poly.sms.entity.Product;
import com.poly.sms.repository.ProductRepository;
import com.poly.sms.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> findById(Integer id) {
        return productRepository.findById(id);
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void deleteById(Integer id) {
        productRepository.deleteById(id);
    }

    @Override
    public List<Product> findTop6ByOrderByDiscountDesc() {
        return productRepository.findTop6ByOrderByDiscountDesc();
    }

    @Override
    public Page<Product> findAllPage(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Override
    public Page<Product> findProductsByCategoryId(int keywords, Pageable pageable) {
        return productRepository.findProductsByCategoryId(keywords, pageable);
    }

    @Override
    public Page<Product> findProductByKeywords(String keywords, Pageable pageable) {
        return productRepository.findProductByKeywords(keywords, pageable);
    }

    @Override
    public Page<Product> findByProductNameContaining(String productName, Pageable pageable) {
        return productRepository.findByProductNameContaining(productName, pageable);
    }

    @Override
    public List<Product> findProductsByCategoryId(int keywords) {
        return productRepository.findProductsByCategoryId(keywords);
    }
}
