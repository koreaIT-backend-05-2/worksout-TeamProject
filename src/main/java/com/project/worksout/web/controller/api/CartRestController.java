package com.project.worksout.web.controller.api;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.worksout.service.cart.CartService;
import com.project.worksout.web.dto.CMRespDto;
import com.project.worksout.web.dto.cart.AddCartReqDto;
import com.project.worksout.web.dto.cart.GetCartRespDto;
import com.project.worksout.web.dto.cart.UpdateCartRespDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
public class CartRestController {

	private final CartService cartService;
	
	@PostMapping("/addCart")
	public ResponseEntity<?> addCart(HttpServletRequest req, HttpServletResponse resp, @RequestBody AddCartReqDto addCartReqDto) {
		boolean status = false;
		
		
//		ObjectMapper objectMapper = new ObjectMapper();
//		
//		String value = null;
//		
//		try {
//			value = objectMapper.writeValueAsString(addCartReqDto).toString();
//		} catch (JsonProcessingException e1) {
//			e1.printStackTrace();
//		}
//		
//		try {
//			value = URLEncoder.encode(value, "utf-8");
//		} catch (UnsupportedEncodingException e1) {
//			e1.printStackTrace();
//		}
//		
//		
//		req.getCookies();
//		
//		Cookie cookie = new Cookie("name", value);
//		
//		resp.addCookie(cookie);
//		
//		System.out.println(cookie);
//		System.out.println(cookie.getName() + "=" + cookie.getValue());
		
		
		try {
			status = cartService.addCart(addCartReqDto);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().body(new CMRespDto<>(-1, "add failed", status));
		}
		
//		header(HttpHeaders.SET_COOKIE, cookie.toString()).
		
		return ResponseEntity.ok().body(new CMRespDto<>(1, "add success", status));
	}
	
	@GetMapping("/{userCode}")
	public ResponseEntity<?> getCart(@PathVariable int userCode) {
		List<GetCartRespDto> listDto = new ArrayList<GetCartRespDto>();
		
		try {
			listDto = cartService.getCartList(userCode);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().body(new CMRespDto<>(-1, "failed request", listDto));
		}
		
		return ResponseEntity.ok().body(new CMRespDto<>(1, "success request", listDto));
	}
	
	@PutMapping("/modify/{cartCode}")
	public ResponseEntity<?> updateCart(@PathVariable int cartCode, @RequestBody UpdateCartRespDto updateCartRespDto) {
		boolean status = false;
		
		try {
			status = cartService.updateCart(updateCartRespDto);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().body(new CMRespDto<>(-1, "failed request", status));
		}
		
		return ResponseEntity.ok().body(new CMRespDto<>(1, "success request", status));
	}
	
	@PutMapping("/flag/{cartCode}")
	public ResponseEntity<?> updateFlag(@PathVariable int cartCode) {
		boolean status = false;
		
		try {
			status = cartService.updateCartFlag(cartCode);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().body(new CMRespDto<>(-1, "failed request", status));
		}
		
		return ResponseEntity.ok().body(new CMRespDto<>(1, "success request", status));
	}
	
	@PutMapping("/first/flag/{userCode}")
	public ResponseEntity<?> firstUpdateFlag(@PathVariable int userCode) {
		boolean status = false;
		
		try {
			status = cartService.updateFirstCartFlag(userCode);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().body(new CMRespDto<>(-1, "failed request", status));
		}
		
		return ResponseEntity.ok().body(new CMRespDto<>(1, "success request", status));
	}
	
	@PutMapping("/all/flag/{userCode}")
	public ResponseEntity<?> allUpdateFlag(@PathVariable int userCode) {
		boolean status = false;
		
		try {
			status = cartService.updateAllCartFlag(userCode);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().body(new CMRespDto<>(-1, "failed request", status));
		}
		
		
		return ResponseEntity.ok().body(new CMRespDto<>(1, "success request", status));
	}
	
	@DeleteMapping("/remove/{cartCode}")
	public ResponseEntity<?> removeCart(@PathVariable int cartCode) {
		boolean status = false;
		
		try {
			status = cartService.removeCart(cartCode);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().body(new CMRespDto<>(-1, "failed request", status));
		}
		
		return ResponseEntity.ok().body(new CMRespDto<>(1, "success request", status));
	}
	
	@DeleteMapping("/choice/remove/{userCode}")
	public ResponseEntity<?> choiceRemoveCart(@PathVariable int userCode) {
		boolean status = false;
		
		try {
			status = cartService.removeChoiceCart(userCode);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().body(new CMRespDto<>(-1, "failed request", status));
		}
		
		return ResponseEntity.ok().body(new CMRespDto<>(1, "success request", status));
	}
	
	
}
