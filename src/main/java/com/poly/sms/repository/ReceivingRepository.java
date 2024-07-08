package com.poly.sms.repository;

import com.poly.sms.entity.Receiving;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReceivingRepository extends JpaRepository<Receiving, Integer> {
}
