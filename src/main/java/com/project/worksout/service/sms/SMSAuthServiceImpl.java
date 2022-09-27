package com.project.worksout.service.sms;

import java.util.HashMap;
import java.util.Random;

import org.springframework.stereotype.Service;

import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;

@Service
public class SMSAuthServiceImpl implements SMSAuthService{

	
	@Override
	public String phoneNumberCheck(String to) throws CoolsmsException {
		
		String api_key = "NCSHOLAAFPJOZGR6";
		String api_secret = "9UG5OWF6N6RLLBPV3QFKK31B09R2F2NH";
		Message message= new Message(api_key, api_secret);
		
		Random random = new Random();
		
		String numStr = "";
		
		for(int i = 0; i < 4; i++) {
			String randomStr = Integer.toString(random.nextInt(10));
			numStr += randomStr;
		}
		
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("to", to);
		params.put("from", "01065587973");
		params.put("type", "sms");
		params.put("text", "인증번호는 ["+ numStr +"] 입니다.");
		
		message.send(params);
		
		
		return numStr;
	}
	
	

}
