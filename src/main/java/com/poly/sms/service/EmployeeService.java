package com.poly.sms.service;

import com.poly.sms.entity.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {

    List<Employee> findAll();

    Optional<Employee> findById(String username);

    Employee findByUsername(String username);

    Employee save(Employee employee);

    void deleteById(String username);

    Employee findByEmail(String email);
}
