package com.poly.sms.controller.site;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("sms")
public class CheckOutController {

    @RequestMapping("checkout")
    public String checkOut() {
        return "site/checkout";
    }
}
