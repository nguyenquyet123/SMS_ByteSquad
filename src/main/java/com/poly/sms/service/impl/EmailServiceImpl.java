/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.poly.sms.service.impl;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.poly.sms.entity.Email;
import com.poly.sms.service.EmailService;

/**
 *
 * @author hoang
 */
@Service
public class EmailServiceImpl implements EmailService{



     @Autowired
    private JavaMailSender mailSender;

    @Override
    public void sendEmail(Email email) {
        Properties props = new Properties();

		props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        props.put("mail.smtp.debug", "true");
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("hoangthanhtuyen.ksmy@gmail.com");
        message.setSubject("New message from " + email.getName());
        message.setText(email.getMessage() + "\n\nFrom: " + email.getEmailD());

        try {
            mailSender.send(message);
        } catch (Exception e) {
            // Log lỗi hoặc xử lý lỗi nếu cần
            e.printStackTrace();
            throw new RuntimeException("Error sending email: " + e.getMessage());
        }
    }
    }
