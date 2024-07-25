package com.poly.sms.repository;

import com.poly.sms.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, String> {
    @Query("SELECT a FROM Employee a WHERE a.username = ?1")
    Employee findByUsername(String username);
}
