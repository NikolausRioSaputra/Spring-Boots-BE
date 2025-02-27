package com.example.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SimpleController {

	@Value("${spring.application.name}")
	String appName;

	@RequestMapping("/1")
	public String homePage(Model model) {
		model.addAttribute("appName", appName);
		return "home";
	}
}
