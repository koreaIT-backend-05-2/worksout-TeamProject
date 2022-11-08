package com.project.worksout.web.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.worksout.service.inquiry.InquiryService;
import com.project.worksout.web.dto.CMRespDto;
import com.project.worksout.web.dto.inquiry.AddInquiryReqDto;
import com.project.worksout.web.dto.inquiry.GetInquiryRespDto;
import com.project.worksout.web.dto.inquiry.GetinquiryListRespDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/v1/inquiry")
@RequiredArgsConstructor
public class InquiryRestController {
	
	@Value("${file.downloadPath}")
	private String downloadFilepath;
	
	private final InquiryService  inquiryService;
	
	@PostMapping("")
	public ResponseEntity<?> addInquiry(AddInquiryReqDto addInquiryReqDto) {
		log.info(">>>>> {}", addInquiryReqDto);
		log.info(">>>>> filename: {}", addInquiryReqDto.getInquiryFile().get(0).getOriginalFilename());
		
		int inquiryCode = 0;
		
		try {
			inquiryCode = inquiryService.addInquiry(addInquiryReqDto);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().body(new CMRespDto<>(-1, "문의하기 실패", inquiryCode));
		}
		
		return ResponseEntity.ok().body(new CMRespDto<>(1, "문의하기 성공", inquiryCode));
	}
	
	@GetMapping("/list/{page}")
	public ResponseEntity<?> getInquiryList(@PathVariable int page, @RequestParam String searchFlag) {
		List<GetinquiryListRespDto> listDto = null;
		
		try {
			listDto = inquiryService.getinquiryList(page, searchFlag);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().body(new CMRespDto<>(-1, "database error", listDto));
		}
		
		return ResponseEntity.ok().body(new CMRespDto<>(1, "request success", listDto));
	}
	
	
	
	@GetMapping("/{inquiryCode}")
	public ResponseEntity<?> getInquiry(@PathVariable int inquiryCode) {
		GetInquiryRespDto getInquiryRespDto = null;
		
		try {
			getInquiryRespDto = inquiryService.getInquiry(null, inquiryCode);
			if(getInquiryRespDto == null) {
				return ResponseEntity.badRequest().body(new CMRespDto<>(-1, "request failed", null));
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().body(new CMRespDto<>(-1, "database error", getInquiryRespDto));
		}
		return ResponseEntity.ok().body(new CMRespDto<>(1, "lookup success", getInquiryRespDto));
	}
	
	@GetMapping("/{flag}/{inquiryCode}")
	public ResponseEntity<?> getInquiry(@PathVariable String flag, @PathVariable int inquiryCode) {
		GetInquiryRespDto getInquiryRespDto = null;
		
		if(flag.equals("pre") || flag.equals("next")) {
			try {
				getInquiryRespDto = inquiryService.getInquiry(flag, inquiryCode);
			} catch (Exception e) { 
				e.printStackTrace();
				return ResponseEntity.badRequest().body(new CMRespDto<>(-1, "request failed", null));
			}
		}else {
			return ResponseEntity.internalServerError().body(new CMRespDto<>(-1, "request failed", getInquiryRespDto));
		}
		return ResponseEntity.ok().body(new CMRespDto<>(1, "lookup success", getInquiryRespDto));
	}
	
	
}
