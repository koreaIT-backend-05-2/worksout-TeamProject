package com.project.worksout.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminPageController {
	// 관리자 뷰
	@GetMapping("/user")
	public String loadAdminUserInfoPage() {
		return "admin-user-info-page";
	}
	@GetMapping("/itemlist")
	public String loadAdminListPage() {
		return "admin-item-list";
	}
	@GetMapping("/add-items")
	public String loadAdminItemInsertPage() {
		return "admin-item-insert-page";
	}
	@GetMapping("/inquiry")
	public String loadAdminAskPage() {
		return "admin-inquiry-page";
	}
	
}
