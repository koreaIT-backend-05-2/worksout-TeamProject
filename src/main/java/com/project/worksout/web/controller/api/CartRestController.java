package com.project.worksout.web.controller.api;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.worksout.service.cart.CartService;
import com.project.worksout.web.dto.CMRespDto;
import com.project.worksout.web.dto.cart.AddCartReqDto;
import com.project.worksout.web.dto.cart.GetCartRespDto;
import com.project.worksout.web.dto.cart.UpdateCartReqDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
public class CartRestController {

	private final CartService cartService;
	
	@PostMapping("/addCart")
	public ResponseEntity<?> addCart(@RequestBody AddCartReqDto addCartReqDto, HttpServletResponse response) {
		boolean status = false;
		
		//Cookie myCookie = new Cookie("test",)
		
		try {
			status = cartService.addCart(addCartReqDto);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.ok().body(new CMRespDto<>(-1, "add failed", status));
		}
		
		return ResponseEntity.ok().body(new CMRespDto<>(1, "add success", status));
	}
	
	@GetMapping("/getCart")
	public ResponseEntity<?> getCart(@RequestParam String username) {
		List<GetCartRespDto> listDto = new ArrayList<GetCartRespDto>();
		
		try {
			listDto = cartService.getCartList(username);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().body(new CMRespDto<>(-1, "failed request", listDto));
		}
		
		return ResponseEntity.ok().body(new CMRespDto<>(1, "success request", listDto));
	}
	
	@PutMapping("/setCart/{cartCode}")
	public ResponseEntity<?> setCartAmount(@PathVariable int cartCode, @RequestBody UpdateCartReqDto updateCartReqDto){
		boolean status = false;
		System.out.println("setCartAmount >>>" + cartCode + " /// ReqBody UpdateCartReqDto >>" + updateCartReqDto);
		try {
			updateCartReqDto.setCartCode(cartCode);
			status = cartService.updateCartAmount(updateCartReqDto);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().body(new CMRespDto<>(-1, "update failed", status));
		}
		
//		System.out.println("setCartAmount >>>" + cartCode + " /// ReqBody UpdateCartReqDto >>" + updateCartReqDto);
		return ResponseEntity.ok().body(new CMRespDto<>(1, "update success", null));
	}
}
