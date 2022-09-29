package com.project.worksout.web.dto.inquiry;

import java.util.List;
import java.util.Map;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class GetInquiryRespDto {

	private int inquiryCode;
	private String inquiryTitle;
	private String inquiryKind;
	private String inquiryName;
	private String inquiryEmail;
	private String createDate;
	private String inquiryContent;
	private List<Map<String, Object>> downloadFiles;
}
