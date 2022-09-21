package com.project.worksout.web.dto.inquiry;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class AddInquiryReqDto {

	private String inquiryKind;
	private String inquiryTitle;
	private String inquiryName;
	private String inquiryEmail;
	private String inquiryContent;
	//private List<MultipartFile> inquiryFile;
}
