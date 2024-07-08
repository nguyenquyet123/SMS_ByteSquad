package com.poly.sms.service.impl;

import com.poly.sms.entity.Shipment;
import com.poly.sms.repository.ShipmentRepository;
import com.poly.sms.service.ShipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShipmentServiceImpl implements ShipmentService {

    @Autowired
    private ShipmentRepository shipmentRepository;

    @Override
    public List<Shipment> findAll() {
        return shipmentRepository.findAll();
    }

    @Override
    public Optional<Shipment> findById(Integer id) {
        return shipmentRepository.findById(id);
    }

    @Override
    public Shipment save(Shipment shipment) {
        return shipmentRepository.save(shipment);
    }

    @Override
    public void deleteById(Integer id) {
        shipmentRepository.deleteById(id);
    }
}
