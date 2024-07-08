package com.poly.sms.service;

import com.poly.sms.entity.Payment;

import java.util.List;
import java.util.Optional;

public interface PaymentService {

    List<Payment> findAll();

    Optional<Payment> findById(Integer id);

    Payment save(Payment payment);

    void deleteById(Integer id);
}
