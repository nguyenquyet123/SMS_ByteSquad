// package com.poly.sms.config;

// import java.util.HashSet;
// import java.util.Set;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.core.GrantedAuthority;
// import org.springframework.security.core.authority.SimpleGrantedAuthority;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.core.userdetails.UserDetailsService;
// import org.springframework.security.core.userdetails.UsernameNotFoundException;
// import org.springframework.stereotype.Service;

// import com.poly.sms.entity.Employee;
// import com.poly.sms.entity.EmployeeRole;
// import com.poly.sms.repository.EmployeeRepository;

// @Service
// public class AccountDetailService implements UserDetailsService {

//      @Autowired
//     private EmployeeRepository employeeRepository;

//     @Override
//     public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//         Employee employee = employeeRepository.findByUsername(username);
//         if (employee == null) {
//             throw new UsernameNotFoundException("User not found");
//         }

//         Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
//         for (EmployeeRole employeeRole : employee.getAuthorities()) {
//             grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + employeeRole.getRole().getName()));
//         }

//         return new org.springframework.security.core.userdetails.User(employee.getUsername(), employee.getPassword(), grantedAuthorities);
//     }

// }
