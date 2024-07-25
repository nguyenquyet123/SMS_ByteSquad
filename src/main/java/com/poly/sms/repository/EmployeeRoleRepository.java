package com.poly.sms.repository;

import com.poly.sms.entity.Employee;
import com.poly.sms.entity.EmployeeRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface EmployeeRoleRepository extends JpaRepository<EmployeeRole, Integer> {
    List<EmployeeRole> findByEmployee(Employee employee);
}
