/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

package com.poly.sms.service;

import com.poly.sms.entity.Email;

/**
 *
 * @author hoang
 */
public interface EmailService {
  

    void sendEmail(Email email);
    void sendPasswordWithCredentials(String to, String password);
    
}

