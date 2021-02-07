package com.xhl.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/protal/index/")
public class IndexController {

    @RequestMapping("ceshi.do")
    @ResponseBody
    public String ceshi(){
        System.out.println("OKK");
        return "<h1>o98k</h1>";
    }
}
