package com.project.worksout.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainEventPageController {

	@GetMapping("/event/1")
	public String loadSignin() {
		return "main-item-page/main-page-1";
	}
	
	@GetMapping("/event/2")
	public String loadSignup() {
		return "main-item-page/main-page-2";
	}	
}
