package com.poly.sms.service.impl;

import com.poly.sms.entity.InventoryTransaction;
import com.poly.sms.repository.InventoryTransactionRepository;
import com.poly.sms.service.InventoryTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InventoryTransactionServiceImpl implements InventoryTransactionService {

    @Autowired
    private InventoryTransactionRepository transactionRepository;

    @Override
    public List<InventoryTransaction> findAll() {
        return transactionRepository.findAll();
    }

    @Override
    public Optional<InventoryTransaction> findById(Integer id) {
        return transactionRepository.findById(id);
    }

    @Override
    public InventoryTransaction save(InventoryTransaction transaction) {
        return transactionRepository.save(transaction);
    }

    @Override
    public void deleteById(Integer id) {
        transactionRepository.deleteById(id);
    }
}
