package com.poly.sms.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.poly.sms.entity.Employee;
import com.poly.sms.service.EmployeeService;

import jakarta.transaction.Transactional;

@Service
@Component
public class AccountDetailService implements UserDetailsService {

    @Autowired
    EmployeeService employeeService;

    @Override
    @Transactional //note :(
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        try {
            Employee employee = employeeService.findByUsername(username);
            if (employee == null) {
                throw new UsernameNotFoundException(username + " NOT FOUND");
            }

            String password = employee.getPassword();
            String[] roles = employee.getEmployeeRoles()
                    .stream()
                    .map(au -> au.getRole().getRoleId())
                    .toArray(String[]::new);

            return User.builder()
                    .username(username)
                    .password(password)
                    .roles(roles)
                    .build();

        } catch (Exception e) {
            throw new UsernameNotFoundException(username + " NOT FOUND", e);
        }

    }

}
