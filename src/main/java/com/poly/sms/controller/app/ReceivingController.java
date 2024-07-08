package com.poly.sms.controller.app;

import com.poly.sms.entity.Receiving;
import com.poly.sms.service.ReceivingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/receivings")
public class ReceivingController {

    @Autowired
    private ReceivingService receivingService;

    @GetMapping
    public List<Receiving> getAllReceivings() {
        return receivingService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Receiving> getReceivingById(@PathVariable Integer id) {
        Optional<Receiving> receiving = receivingService.findById(id);
        if (receiving.isPresent()) {
            return ResponseEntity.ok(receiving.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public Receiving createReceiving(@RequestBody Receiving receiving) {
        return receivingService.save(receiving);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Receiving> updateReceiving(@PathVariable Integer id, @RequestBody Receiving receivingDetails) {
        Optional<Receiving> receiving = receivingService.findById(id);
        if (receiving.isPresent()) {
            Receiving updatedReceiving = receiving.get();
            updatedReceiving.setQuantity(receivingDetails.getQuantity());
            updatedReceiving.setSupplier(receivingDetails.getSupplier());
            updatedReceiving.setInventoryTransaction(receivingDetails.getInventoryTransaction());
            updatedReceiving.setProduct(receivingDetails.getProduct());
            return ResponseEntity.ok(receivingService.save(updatedReceiving));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReceiving(@PathVariable Integer id) {
        Optional<Receiving> receiving = receivingService.findById(id);
        if (receiving.isPresent()) {
            receivingService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
