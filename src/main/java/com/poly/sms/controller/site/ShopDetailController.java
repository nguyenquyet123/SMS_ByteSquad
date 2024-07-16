package com.poly.sms.controller.site;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("sms")
public class ShopDetailController {

    @RequestMapping("shopdetails")
    public String shopDetail() {
        return "site/shop-detail";
    }
}
