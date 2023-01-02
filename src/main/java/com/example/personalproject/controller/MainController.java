package com.example.personalproject.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
public class MainController {

	@RequestMapping("/")
	public String index(Model model) {
		return "index";
	}

	@RequestMapping("/error/denied")
	public String errorDenied() {

		return "error/denied";
	}


}
