package com.poly.sms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.poly.sms.entity.Email;

@Repository
public interface EmailRepository extends JpaRepository<Email, Integer> {
    long countByIsReadFalse(); // Đếm số lượng email chưa đọc
}