package com.sms.bytesquad.controller.manage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class loginController {
    @GetMapping("login")
    public String getLogin() {
        return "account/login";
    }

    @GetMapping("forgot-password")
    public String getForgotPass() {
        return "account/forgot-password";
    }

    @GetMapping("register")
    public String getRegister() {
        return "account/register";
    }
    
}
