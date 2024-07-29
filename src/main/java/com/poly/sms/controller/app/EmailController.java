package com.poly.sms.controller.app;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.poly.sms.entity.Email;
import com.poly.sms.repository.EmailRepository;
import com.poly.sms.service.EmailService;

@RestController
@RequestMapping("/api/email")
public class EmailController {

    @Autowired
    private EmailRepository emailRepository;

    @Autowired
    private  EmailService emailService;

    @GetMapping()
    public List<Email> getEmailDetails() {
        return emailService.findAll();
    }
    
}
