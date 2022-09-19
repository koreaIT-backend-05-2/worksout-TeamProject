package com.project.worksout.web.controller.api;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.worksout.service.auth.AuthService;
import com.project.worksout.service.auth.PrincipalDetails;
import com.project.worksout.service.auth.PrincipalDetailsService;
import com.project.worksout.web.dto.CMRespDto;
import com.project.worksout.web.dto.user.UpdateUserReqDto;
import com.project.worksout.web.dto.user.UserSignupReqDto;
import com.project.worksout.web.dto.user.UsernameCheckReqDto;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

	private final  PrincipalDetailsService principalDetailsService;
	private final AuthService authService;
	
	@PostMapping("/signup")
	public ResponseEntity<?> signup(@Valid @RequestBody UserSignupReqDto userSignupReqDto, BindingResult bindingResult) {
		boolean status = false;
		
		if(bindingResult.hasErrors()) {
			Map<String, String> errorMessage = new HashMap<String, String>();
			
			bindingResult.getFieldErrors().forEach(error -> {
				errorMessage.put(error.getField(), error.getDefaultMessage());
			});
			
			return ResponseEntity.badRequest().body(new CMRespDto<>(-1, " 유효성 검사 실패", errorMessage));
			
		}
		
		try {
			status = principalDetailsService.addUser(userSignupReqDto);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().body(new CMRespDto<>(-1, " 회원가입 실패", status));
		}
		
		return ResponseEntity.ok().body(new CMRespDto<>(1, "회원가입 성공", status));
	}
	
	@GetMapping("/signup/validation/username")
	public ResponseEntity<?> checkUsername(@Valid UsernameCheckReqDto usernameCheckReqDto, BindingResult bindingResult) {
		boolean status = false;
		
		if(bindingResult.hasErrors()) {
			Map<String, String> errorMessage = new HashMap<String, String>();
			
			bindingResult.getFieldErrors().forEach(error -> {
				errorMessage.put(error.getField(), error.getDefaultMessage());
			});
			
			return ResponseEntity.badRequest().body(new CMRespDto<>(-1, " 유효성 검사 실패", errorMessage));
			
		}
	
		try {
			status = authService.checkUsername(usernameCheckReqDto);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().body(new CMRespDto<>(-1, "중복 확인 실패", status));
		}
		
		return ResponseEntity.ok().body(new CMRespDto<>(1, "중복 확인", status));
		
	}
	
	@GetMapping("/principal")
	public ResponseEntity<?> getPrincipal(@AuthenticationPrincipal PrincipalDetails principalDetails) {
		if(principalDetails == null) {
			return  ResponseEntity.badRequest().body(new CMRespDto<>(-1, "principal is null", null));
		}
		
		return ResponseEntity.ok().body(new CMRespDto<>(1, "success load", principalDetails.getUser()));
	
	}
	
	//회원정보 수정 컨트롤러
	@PutMapping("/modify/{userCode}")
	public ResponseEntity<?> modifyUser(@PathVariable int userCode, @RequestBody UpdateUserReqDto updateUserReqDto) {
		boolean status = false;
		
		try {
			status = authService.updateUser(updateUserReqDto);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().body(new CMRespDto<>(-1, "회원정보 수정 실패", status));
		}
		
		
		return ResponseEntity.ok().body(new CMRespDto<>(1, "회원정보 수정 완료", updateUserReqDto));
	}
	
}
