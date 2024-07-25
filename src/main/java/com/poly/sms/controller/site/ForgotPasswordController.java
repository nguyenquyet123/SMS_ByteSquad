/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.poly.sms.controller.site;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.poly.sms.entity.Employee;
import com.poly.sms.repository.EmployeeRepository;
import com.poly.sms.service.EmailService;


/**
 *
 * @author hoang
 */
@Controller
@RequestMapping("sms")
public class ForgotPasswordController {

    @RequestMapping("forgot")
    public String forgot() {
        return "site/forgotPassword"; // Tìm kiếm tệp tại src/main/resources/templates/site/forgotPassword.html
    }
  @Autowired
    private EmployeeRepository employeeRepository; // Repository để tìm kiếm người dùng

    @Autowired
    private EmailService emailService; // Dịch vụ gửi email

    @PostMapping("forgot")
    public String handlePasswordReset(@RequestParam("email") String email, Model model) {
        // Tìm người dùng theo email
        Employee employee = employeeRepository.findByEmail(email);
        if (employee != null) {
            // Gửi email chứa mật khẩu của người dùng
            emailService.sendPasswordWithCredentials(employee.getEmail(), employee.getPassword());
            model.addAttribute("message", "Đã gửi mật khẩu đến email của bạn.");
        } else {
            model.addAttribute("error", "Email không được tìm thấy.");
        }
        return "site/forgotPassword"; // Trả về trang xác nhận
    }
}

