package com.project.worksout.web.dto.inquiry;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class GetinquiryListRespDto {
	private int inquiryCode;
	private String inquiryKind;
	private String inquiryTitle;
	private String inquiryName;
	private String inquiryEmail;
	private String createDate;
	private int totalInquiryCount;

	
}
