/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

package com.poly.sms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.poly.sms.entity.Email;

/**
 *
 * @author hoang
 */
public interface EmailRepository extends JpaRepository<Email, Long>{

}
