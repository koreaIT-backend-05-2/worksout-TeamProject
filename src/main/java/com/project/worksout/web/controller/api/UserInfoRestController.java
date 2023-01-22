package com.project.worksout.web.controller.api;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.worksout.service.userInfo.UserInfoService;
import com.project.worksout.web.dto.CMRespDto;
import com.project.worksout.web.dto.user.UserInfoListRespDto;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
public class UserInfoRestController {
	
	private final UserInfoService userInfoService;

	@GetMapping("/user/list/{page}")
	public ResponseEntity<?> getUserInfoList(@PathVariable int page, @RequestParam String searchFlag) {
		List<UserInfoListRespDto> listDto = null;
		
		try {
			listDto = userInfoService.getUserInfoList(page, searchFlag);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().body(new CMRespDto<>(-1, "유저 정보 조회실패", listDto));
		}
		
		return ResponseEntity.ok().body(new CMRespDto<>(1, "유저 정보 조회 성공", listDto));
	}
	
}
