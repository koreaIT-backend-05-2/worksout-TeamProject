package com.project.worksout.web.dto.mailto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MailToReqDto {

	private String address;
	private String title;
	private String message;
	
}
