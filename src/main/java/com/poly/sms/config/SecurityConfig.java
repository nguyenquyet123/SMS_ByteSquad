package com.poly.sms.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    private AccountDetailService accountDetailService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.csrf(AbstractHttpConfigurer::disable);

        http.authorizeHttpRequests(
                auth -> auth
                        .requestMatchers("/sms/checkout").authenticated()
                        .requestMatchers("/sms/app").hasAnyRole("ADMIN")
                        .requestMatchers("/api/**").authenticated()
                        .anyRequest().permitAll());

        http.exceptionHandling(ex -> {
            ex.accessDeniedPage("/access/denied");
        });

        http.formLogin(login -> {
            login
                    .loginPage("/sms/login")
                    .loginProcessingUrl("/login")
                    .defaultSuccessUrl("/sms/home", false)
                    .failureUrl("/login/error")
                    .usernameParameter("username")
                    .passwordParameter("password");
        });

        http.logout(logout -> {
            logout
                    .logoutUrl("/sms/logout")
                    .logoutSuccessUrl("/logout/success")
                    .invalidateHttpSession(true)
                    .deleteCookies("JSESSIONID");
        });

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return accountDetailService;
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService());
        provider.setPasswordEncoder(passwordEncoder());

        return provider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
