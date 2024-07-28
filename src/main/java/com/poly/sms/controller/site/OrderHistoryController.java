package com.poly.sms.controller.site;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("sms")
public class OrderHistoryController {
    @RequestMapping("orderhistory")
    public String orderhistory() {
        return "site/orderhistory";
    }

}
