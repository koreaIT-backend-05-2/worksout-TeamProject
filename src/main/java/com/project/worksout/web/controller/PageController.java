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
	
	@GetMapping("/category")
	public String loadItemCategoryPage() {
		return "/items-category-page";
	}
	
	// 관리자 뷰
	@GetMapping("/admin/user")
	public String loadAdminUserInfoPage() {
		return "/admin-user-info-page";
	}
	@GetMapping("/admin/add-items")
	public String loadAdminItemInsertPage() {
		return "/admin-item-insert-page";
	}
	@GetMapping("/admin/inquiry")
	public String loadAdminAskPage() {
		return "/admin-inquiry-page";
	}
	@GetMapping("/admin/inquiry/detail/{inquiryCode}")
	public String detailInquriy() {
		return "/admin-inquiry-detail-page";
	}
	
	
	
	//문의사항 페이지
	@GetMapping("/inquiry")
	public String loadInquiryPage() {
		return "inquiry";
	}
}
