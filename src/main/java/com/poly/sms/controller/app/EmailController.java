package com.poly.sms.controller.app;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.poly.sms.entity.Email;
import com.poly.sms.service.EmailService;

@RestController
@RequestMapping("/api/emails")
public class EmailController {

    @Autowired
    private EmailService emailService;

    @GetMapping
    public ResponseEntity<List<Email>> getAllEmails() {
        return ResponseEntity.ok(emailService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Email> getEmailById(@PathVariable Integer id) {
        Email email = emailService.getEmailById(id);
        if (email != null) {
            return ResponseEntity.ok(email);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping
    public ResponseEntity<Email> createEmail(@RequestBody Email email) {
        Email savedEmail = emailService.saveEmail(email);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedEmail);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Email> updateEmail(@PathVariable Integer id, @RequestBody Email email) {
        Optional<Email> existingEmail = Optional.ofNullable(emailService.getEmailById(id));
        if (existingEmail.isPresent()) {
            email.setId(id); // Ensure ID is set for update
            Email updatedEmail = emailService.saveEmail(email);
            return ResponseEntity.ok(updatedEmail);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmail(@PathVariable Integer id) {
        if (emailService.getEmailById(id) != null) {
            emailService.deleteEmail(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/unread")
    public ResponseEntity<Long> getUnreadEmailCount() {
        long count = emailService.getUnreadEmailCount();
        return ResponseEntity.ok(count);
    }
}
