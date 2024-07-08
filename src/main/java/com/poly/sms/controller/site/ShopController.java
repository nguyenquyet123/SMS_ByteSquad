package com.poly.sms.controller.site;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("sms")
public class ShopController {

    @RequestMapping("shop")
    public String shop() {
        return "site/shop";
    }
}
