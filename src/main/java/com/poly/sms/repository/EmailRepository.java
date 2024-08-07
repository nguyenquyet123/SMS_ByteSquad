package com.poly.sms.repository;

import com.poly.sms.entity.Email;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailRepository extends JpaRepository<Email, Integer> {
    long countByIsReadFalse(); // Đếm số lượng email chưa đọc
}
