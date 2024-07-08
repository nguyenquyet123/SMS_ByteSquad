package com.poly.sms.service;

import com.poly.sms.entity.Shipment;

import java.util.List;
import java.util.Optional;

public interface ShipmentService {

    List<Shipment> findAll();

    Optional<Shipment> findById(Integer id);

    Shipment save(Shipment shipment);

    void deleteById(Integer id);
}
