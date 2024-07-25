package com.poly.sms.entity;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "Employees")
public class Employee {

    @Id
    private String username;

    private String password;

    private String fullname;

    private String email;

    private String phone;

    private Date registrationDate;

    private Date birthDate;

    private String address;

    private BigDecimal salary;

    private String photoPath;

    private Timestamp lastUpdate;

    @JsonIgnore
    @OneToMany(mappedBy = "employee")
    private List<EmployeeRole> employeeRoles;
}
