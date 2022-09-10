package com.project.worksout.web.controller.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.worksout.web.dto.CMRespDto;
import com.project.worksout.web.dto.user.UserSignupReqDto;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

	@PostMapping("/signup")
	public ResponseEntity<?> signup(@RequestBody UserSignupReqDto userSignupReqDto) {
		
		return ResponseEntity.ok().body(new CMRespDto<>(1, "test", userSignupReqDto));
	}
}
