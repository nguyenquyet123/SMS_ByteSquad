package com.poly.sms.service;

import com.poly.sms.entity.InventoryTransaction;

import java.util.List;
import java.util.Optional;

public interface InventoryTransactionService {

    List<InventoryTransaction> findAll();

    Optional<InventoryTransaction> findById(Integer id);

    InventoryTransaction save(InventoryTransaction transaction);

    void deleteById(Integer id);
}
