package com.poly.sms.service;

import com.poly.sms.entity.Receiving;

import java.util.List;
import java.util.Optional;

public interface ReceivingService {

    List<Receiving> findAll();

    Optional<Receiving> findById(Integer id);

    Receiving save(Receiving receiving);

    void deleteById(Integer id);
}
