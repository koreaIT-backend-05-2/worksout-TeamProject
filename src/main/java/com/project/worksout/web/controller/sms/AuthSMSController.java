package com.project.worksout.web.controller.sms;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.worksout.handler.aop.annotation.Log;
import com.project.worksout.service.sms.SMSAuthService;

import lombok.RequiredArgsConstructor;
import net.nurigo.java_sdk.exceptions.CoolsmsException;

@Controller
@RequiredArgsConstructor
public class AuthSMSController {
	
	private final SMSAuthService smsAuthService;
	
	@Log
	@GetMapping("/check/sendSMS")
	public @ResponseBody String sendSMS(@RequestParam(value = "to") String to) throws CoolsmsException {
		
			return smsAuthService.phoneNumberCheck(to);
	}
	
}
