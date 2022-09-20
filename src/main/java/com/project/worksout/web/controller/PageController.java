package com.project.worksout.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

//로그인 회원가입 관련 페이지
	@GetMapping("/signin")
	public String loadSignin() {
		return "/signin";
	}
	
	@GetMapping("/signup")
	public String loadSignup() {
		return "/signup";
	}
	
	@GetMapping("/signup/success")
	public String successSignup() {
		return "/signup-success";
	}
	
	
// 상품 관련 페이지	
	@GetMapping("/items")
	public String loadItemDetailPage() {
		return "/items-detail-page";
	}
	
	@GetMapping("/cart")
	public String loadCartPage() {
		return "/cart";
	}

	
}
