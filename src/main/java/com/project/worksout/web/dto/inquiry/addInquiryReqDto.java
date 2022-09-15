package com.project.worksout.web.dto.inquiry;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class addInquiryReqDto {
	private List<MultipartFile> file;
	
}
