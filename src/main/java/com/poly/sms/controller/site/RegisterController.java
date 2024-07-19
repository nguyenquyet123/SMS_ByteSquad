package com.poly.sms.controller.site;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("sms")
public class RegisterController {
    @RequestMapping("register")
    public String register() {
        return "site/register";
    }
    
}
