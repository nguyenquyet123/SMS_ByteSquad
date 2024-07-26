
package com.poly.sms.controller.site;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.poly.sms.entity.Employee;
import com.poly.sms.service.EmailService;
import com.poly.sms.service.EmployeeService;

@Controller
@RequestMapping("sms")
public class ForgotPasswordController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    EmployeeService employeeService; // Repository để tìm kiếm người dùng

    @Autowired
    private EmailService emailService; // Dịch vụ gửi email

    @RequestMapping("forgot")
    public String forgot() {
        return "site/forgotPassword"; // Tìm kiếm tệp tại src/main/resources/templates/site/forgotPassword.html
    }

    @SuppressWarnings("unused")
    @PostMapping("forgot")
    public String handlePasswordReset(@RequestParam("email") String email, Model model) {
        // Tìm người dùng theo email
        Employee employee = employeeService.findByEmail(email);

        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        int length = 6;
        String pass = generateRandomString(characters, length);

        employee.setPassword(passwordEncoder.encode(pass));

        employeeService.save(employee);

        if (employee != null) {
            // Gửi email chứa mật khẩu của người dùng
            emailService.sendPasswordWithCredentials(employee.getEmail(), pass);
            model.addAttribute("message", "Đã gửi mật khẩu đến email của bạn.");
        } else {
            model.addAttribute("error", "Email không được tìm thấy.");
        }

        return "site/forgotPassword"; // Trả về trang xác nhận
    }



    public static String generateRandomString(String characters, int length) {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            sb.append(characters.charAt(index));
        }

        return sb.toString();
    }
      @GetMapping("/reset-password")
    public String showResetPasswordForm(Model model) {
        model.addAttribute("employee", new Employee());
        return "site/changePassword";
    }

    @PostMapping("/reset-password")
    public String resetPassword(@RequestParam String username, 
    @RequestParam String currentPassword, 
    @RequestParam String newPassword, 
    Model model) {
        Employee employee = employeeService.findByUsername(username);
        if (employee == null) {
            model.addAttribute("message", "Tên người dùng không tồn tại.");
            return "site/changePassword";
        }

        if (!passwordEncoder.matches(currentPassword, employee.getPassword())) {
            model.addAttribute("message", "Mật khẩu hiện tại không đúng.");
            return "site/changePassword";
        }

        employee.setPassword(passwordEncoder.encode(newPassword)); // Trong thực tế, bạn nên mã hóa mật khẩu trước khi lưu
        employeeService.save(employee);

        model.addAttribute("message", "Mật khẩu đã được thay đổi thành công.");
        return "site/changePassword";
    }



}
