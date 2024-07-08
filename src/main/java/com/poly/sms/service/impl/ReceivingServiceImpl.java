package com.poly.sms.service.impl;

import com.poly.sms.entity.Receiving;
import com.poly.sms.repository.ReceivingRepository;
import com.poly.sms.service.ReceivingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReceivingServiceImpl implements ReceivingService {

    @Autowired
    private ReceivingRepository receivingRepository;

    @Override
    public List<Receiving> findAll() {
        return receivingRepository.findAll();
    }

    @Override
    public Optional<Receiving> findById(Integer id) {
        return receivingRepository.findById(id);
    }

    @Override
    public Receiving save(Receiving receiving) {
        return receivingRepository.save(receiving);
    }

    @Override
    public void deleteById(Integer id) {
        receivingRepository.deleteById(id);
    }
}
