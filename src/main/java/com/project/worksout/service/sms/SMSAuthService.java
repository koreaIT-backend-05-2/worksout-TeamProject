package com.project.worksout.service.sms;

import net.nurigo.java_sdk.exceptions.CoolsmsException;

public interface SMSAuthService {

	public boolean authPhoneNumberCheck(String sendMsg) throws CoolsmsException;
}
