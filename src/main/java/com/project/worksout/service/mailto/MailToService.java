package com.project.worksout.service.mailto;

import com.project.worksout.web.dto.mailto.MailToReqDto;

public interface MailToService {
	public void sendMail(MailToReqDto mailTo) throws Exception;
	
}
