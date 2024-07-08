package com.poly.sms.controller.app;

import com.poly.sms.entity.InventoryTransaction;
import com.poly.sms.service.InventoryTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/inventory-transactions")
public class InventoryTransactionController {

    @Autowired
    private InventoryTransactionService inventoryTransactionService;

    @GetMapping
    public List<InventoryTransaction> getAllInventoryTransactions() {
        return inventoryTransactionService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<InventoryTransaction> getInventoryTransactionById(@PathVariable Integer id) {
        Optional<InventoryTransaction> transaction = inventoryTransactionService.findById(id);
        if (transaction.isPresent()) {
            return ResponseEntity.ok(transaction.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public InventoryTransaction createInventoryTransaction(@RequestBody InventoryTransaction inventoryTransaction) {
        return inventoryTransactionService.save(inventoryTransaction);
    }

    @PutMapping("/{id}")
    public ResponseEntity<InventoryTransaction> updateInventoryTransaction(@PathVariable Integer id, @RequestBody InventoryTransaction transactionDetails) {
        Optional<InventoryTransaction> transaction = inventoryTransactionService.findById(id);
        if (transaction.isPresent()) {
            InventoryTransaction updatedTransaction = transaction.get();
            updatedTransaction.setTransactionType(transactionDetails.getTransactionType());
            updatedTransaction.setTransactionDate(transactionDetails.getTransactionDate());
            updatedTransaction.setReceiver(transactionDetails.getReceiver());
            updatedTransaction.setCreator(transactionDetails.getCreator());
            updatedTransaction.setQuantityReceived(transactionDetails.getQuantityReceived());
            updatedTransaction.setQuantityShipped(transactionDetails.getQuantityShipped());
            updatedTransaction.setReceivingLocation(transactionDetails.getReceivingLocation());
            updatedTransaction.setInventoryShrinkage(transactionDetails.getInventoryShrinkage());
            updatedTransaction.setTransactionStatus(transactionDetails.getTransactionStatus());
            updatedTransaction.setNote(transactionDetails.getNote());
            updatedTransaction.setBranch(transactionDetails.getBranch());
            return ResponseEntity.ok(inventoryTransactionService.save(updatedTransaction));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInventoryTransaction(@PathVariable Integer id) {
        Optional<InventoryTransaction> transaction = inventoryTransactionService.findById(id);
        if (transaction.isPresent()) {
            inventoryTransactionService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
