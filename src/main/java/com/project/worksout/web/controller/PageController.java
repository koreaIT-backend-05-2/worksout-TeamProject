package com.project.worksout.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

	@GetMapping({"/index", "/"})
	public String loadIndex() {
		return "/index";
	}
	
	@GetMapping("/signin")
	public String loadSignin() {
		return "/signin";
	}
	
	@GetMapping("/signup")
	public String loadSignup() {
		return "/signup";
	}
	
	@GetMapping("/item")
	public String loadItemDetailPage() {
		return "/items-detail-page";
	}

	
}
