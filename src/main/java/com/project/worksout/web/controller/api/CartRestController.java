package com.project.worksout.web.controller.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.worksout.service.cart.CartService;
import com.project.worksout.web.dto.CMRespDto;
import com.project.worksout.web.dto.cart.AddCartReqDto;
import com.project.worksout.web.dto.cart.GetCartRespDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
public class CartRestController {

	private final CartService cartService;
	
	@PostMapping("/addCart")
	public ResponseEntity<?> addCart(@RequestBody AddCartReqDto addCartReqDto) {
		boolean status = false;
		
		try {
			status = cartService.addCart(addCartReqDto);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.ok().body(new CMRespDto<>(-1, "add failed", status));
		}
		
		return ResponseEntity.ok().body(new CMRespDto<>(1, "add success", status));
	}
	
	@GetMapping("")
	public ResponseEntity<?> getCart(@RequestParam String username ) {
		List<GetCartRespDto> listDto = new ArrayList<GetCartRespDto>();
		
		try {
			listDto = cartService.getCartList(username);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().body(new CMRespDto<>(-1, "failed request", listDto));
		}
		
		return ResponseEntity.ok().body(new CMRespDto<>(1, "success request", listDto));
	}
}
