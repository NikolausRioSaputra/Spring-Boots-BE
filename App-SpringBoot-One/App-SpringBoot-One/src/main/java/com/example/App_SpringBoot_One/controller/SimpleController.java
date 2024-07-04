package com.example.App_SpringBoot_One.controller;

import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SimpleController {
    @Value("${spring.application.name}")
    String appName;

    @RequestMapping("/")
    public String homepage(Model model){
        model.addAttribute("appName", appName);
        return "home";
    }
}
