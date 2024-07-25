package com.poly.sms.service;

import java.util.List;
import java.util.Optional;

import com.poly.sms.entity.Customer;

public interface CustomerService {

    List<Customer> findAll();

    Optional<Customer> findById(Integer id);

    Customer save(Customer customer);

    void deleteById(Integer id);

    
    
}
