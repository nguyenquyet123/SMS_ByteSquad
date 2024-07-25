package com.poly.sms.controller.site;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AuthController {
    @RequestMapping("sms/login")
    public String form() {
        return "site/login";
    }

    @RequestMapping("login/error")
    public String error(Model model) {
        model.addAttribute("message", "Sai thông tin đăng nhập");
        return "forward:/sms/login";
    }

    @RequestMapping("logout/success")
    public String logout(Model model) {
        model.addAttribute("message", "Đăng xuất thành công");
        return "forward:/sms/login";
    }

    @RequestMapping("access/denied")
    public String access(Model model) {
        model.addAttribute("message", "Bạn không có quyền truy xuất");
        return "forward:/sms/login";
    }

}
