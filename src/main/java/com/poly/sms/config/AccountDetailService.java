// package com.poly.sms.config;

// import java.util.stream.Collectors;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.core.userdetails.User;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.core.userdetails.UserDetailsService;
// import org.springframework.security.core.userdetails.UsernameNotFoundException;
// import org.springframework.stereotype.Service;

// import com.poly.sms.entity.Employee;
// import com.poly.sms.service.EmployeeService;

// @Service
// public class AccountDetailService implements UserDetailsService {

//     @Autowired
//     EmployeeService employeeService;

//     @Override
//     public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//         try {

//             Employee account = employeeService.findById(username).get();
//             String password = account.getPassword();
//             String[] roles = account.getAuthorities().stream()
//                     .map(au -> au.getRole().getRoleId())
//                     .collect(Collectors.toList()).toArray(new String[0]);

//             return User.builder().username(username)
//                     .password(password)
//                     .roles(roles)
//                     .build();
//         } catch (Exception e) {
//             throw new UsernameNotFoundException(username + " NOT FOUND", e);
//         }

//     }

// }
