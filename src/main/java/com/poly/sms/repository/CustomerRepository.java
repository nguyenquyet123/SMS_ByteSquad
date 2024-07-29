package com.poly.sms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.poly.sms.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
        Customer findByEmail(String email);
}