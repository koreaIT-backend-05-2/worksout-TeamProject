package com.project.worksout.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CategoryPageController {


	@GetMapping("/category/m")
	public String loadMaleCategoryPage() {
		return "category/items-category-page-m";
	}
	@GetMapping("/category/f")
	public String loadFemaleCategoryPage() {
		return "category/items-category-page-f";
	}
	@GetMapping("/category/e")
	public String loadEtcCategoryPage() {
		return "category/items-category-page-e";
	}
	
	
}
