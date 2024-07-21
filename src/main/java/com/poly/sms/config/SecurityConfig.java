package com.poly.sms.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
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

    private final String[] PUBLIC_ENDPOINTS = { "/site/img/**", "/site/css/**", "/app/images/**", "/app/css/**",
            "/app/vendors/**", "/app/spa/**", "/api/**", "/sms/**", };

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        // .csrf(AbstractHttpConfigurer::disable)
        // .httpBasic(Customizer.withDefaults())

        http.csrf(AbstractHttpConfigurer::disable);

        http.authorizeHttpRequests(
                auth -> auth
                        .requestMatchers(PUBLIC_ENDPOINTS).permitAll()
                        .anyRequest().authenticated());

        // http.formLogin(login -> {
        // login
        // .loginPage("/shop/login")
        // .failureUrl("/shop/fail")
        // .successHandler(new AuthenticationSuccessHandler(session));
        // });

        // http.logout(logout -> {
        // logout
        // .logoutUrl("/shop/logout")
        // .logoutSuccessUrl("/shop/login")
        // .invalidateHttpSession(true)
        // .deleteCookies("JSESSIONID");
        // });

        http.httpBasic(Customizer.withDefaults());

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
