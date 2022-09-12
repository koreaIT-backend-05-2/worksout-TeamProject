package com.project.worksout.web.controller.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.worksout.service.auth.PrincipalDetails;
import com.project.worksout.service.auth.PrincipalDetailsService;
import com.project.worksout.web.dto.CMRespDto;
import com.project.worksout.web.dto.user.UserSignupReqDto;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

	private final  PrincipalDetailsService principalDetailsService;
	
	@PostMapping("/signup")
	public ResponseEntity<?> signup(@RequestBody UserSignupReqDto userSignupReqDto) {
		boolean status = false;
		
		try {
			status = principalDetailsService.addUser(userSignupReqDto);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().body(new CMRespDto<>(-1, " 회원가입 실패", status));
		}
		
		return ResponseEntity.ok().body(new CMRespDto<>(1, "회원가입 성공", status));
	}
}
