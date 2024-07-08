package com.poly.sms.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Roles")
public class Role {

    @Id
    @Column(name = "role_id", length = 20)
    private String roleId;

    @Column(name = "name", nullable = false, unique = true, length = 50, columnDefinition = "NVARCHAR")
    private String name;
}
