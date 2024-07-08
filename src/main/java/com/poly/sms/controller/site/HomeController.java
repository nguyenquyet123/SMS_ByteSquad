package com.poly.sms.controller.site;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("sms")
public class HomeController {

    @RequestMapping("home")
    public String home() {
        return "site/index";
    }

}
