package com.project.worksout.web.controller.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.worksout.service.cart.CartService;
import com.project.worksout.web.dto.CMRespDto;
import com.project.worksout.web.dto.cart.AddCartReqDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
public class CartRestController {

	private final CartService cartService;
	
	@PostMapping("/addCart")
	ResponseEntity<?> addCart(@RequestBody AddCartReqDto addCartReqDto) {
		boolean status = false;
		
		try {
			status = cartService.addCart(addCartReqDto);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.ok().body(new CMRespDto<>(-1, "add failed", status));
		}
		
		return ResponseEntity.ok().body(new CMRespDto<>(1, "add success", status));
	}
	
}
