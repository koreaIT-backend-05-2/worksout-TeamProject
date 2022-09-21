package com.project.worksout.web.controller.sms;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.worksout.service.sms.SMSAuthService;
import com.project.worksout.web.dto.CMRespDto;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class AuthSMSController {
	
	private SMSAuthService smsAuthService;
	
	@GetMapping("/check/sendSMS")
	public ResponseEntity<?> sendSMS(@RequestParam String sendMsg) {
		boolean status = false;
		
		try {
			status = smsAuthService.authPhoneNumberCheck(sendMsg);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.ok().body(new CMRespDto<>(-1, "메시지 전송 실패", status));
		}
		
		return ResponseEntity.ok().body(new CMRespDto<>(1, "메시지 전송 성공", status));
	}
	
}
