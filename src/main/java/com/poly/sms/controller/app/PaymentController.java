package com.poly.sms.controller.app;

import com.poly.sms.entity.Payment;
import com.poly.sms.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @GetMapping
    public List<Payment> getAllPayments() {
        return paymentService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Payment> getPaymentById(@PathVariable Integer id) {
        Optional<Payment> payment = paymentService.findById(id);
        if (payment.isPresent()) {
            return ResponseEntity.ok(payment.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public Payment createPayment(@RequestBody Payment payment) {
        return paymentService.save(payment);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Payment> updatePayment(@PathVariable Integer id, @RequestBody Payment paymentDetails) {
        Optional<Payment> payment = paymentService.findById(id);
        if (payment.isPresent()) {
            Payment updatedPayment = payment.get();
            updatedPayment.setOrder(paymentDetails.getOrder());
            updatedPayment.setPaymentMethod(paymentDetails.getPaymentMethod());
            updatedPayment.setPaymentDate(paymentDetails.getPaymentDate());
            updatedPayment.setAmount(paymentDetails.getAmount());
            updatedPayment.setPaymentStatus(paymentDetails.getPaymentStatus());
            return ResponseEntity.ok(paymentService.save(updatedPayment));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePayment(@PathVariable Integer id) {
        Optional<Payment> payment = paymentService.findById(id);
        if (payment.isPresent()) {
            paymentService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
