package com.poly.sms.controller.site;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("sms")
public class AppController {
    @RequestMapping("app")
    public String app() {
        return "app/index";
    }
}
