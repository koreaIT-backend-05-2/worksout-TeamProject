package com.project.worksout.web.controller.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.worksout.web.dto.CMRespDto;
import com.project.worksout.web.dto.inquiry.AddInquiryReqDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/v1/inquiry")
public class InquiryRestController {

	@PostMapping("")
	public ResponseEntity<?> addInquiry(AddInquiryReqDto addInquiryReqDto) {
		
		
		return ResponseEntity.ok().body(new CMRespDto<>(1, "test", addInquiryReqDto));
	}
	
}
