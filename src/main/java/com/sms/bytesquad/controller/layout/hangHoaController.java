package com.sms.bytesquad.controller.layout;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class hangHoaController {
    
    @RequestMapping("/danhMuc")
    public String hangHoaPage() {
        return "hangHoa/danhMucPage/_page";
    }
}
