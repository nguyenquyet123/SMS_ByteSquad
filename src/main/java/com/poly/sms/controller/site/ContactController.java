package com.poly.sms.controller.site;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("sms")
public class ContactController {

    @RequestMapping("contact")
    public String contact() {
        return "site/contact";
    }
}
