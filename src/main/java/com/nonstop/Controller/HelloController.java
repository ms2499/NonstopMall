package com.nonstop.Controller;

import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/back")
@Hidden
public class HelloController {
//    @GetMapping("/")
//    public String index() {
//        return "index";
//    }

    @GetMapping("/CommodityManager.html")
    public String CommodityManager() {
        return "CommodityManager";
    }

    @GetMapping("/CommodityTagManager.html")
    public String CommodityTagManager() {
        return "CommodityTagManager";
    }

    @GetMapping("/UserinfoManager.html")
    public String UserinfoManager() {
        return "UserinfoManager";
    }

    @GetMapping("/CartManager.html")
    public String CartManager() {
        return "CartManager";
    }

    @GetMapping("/OrderManager.html")
    public String OrderManager() {
        return "OrderManager";
    }

    @GetMapping("/Manager.html")
    public String DbManager() {
        return "Manager";
    }

    @GetMapping("/hello")
    @ResponseBody
    public String hello() {
        return "Hello 測試";
    }
}
