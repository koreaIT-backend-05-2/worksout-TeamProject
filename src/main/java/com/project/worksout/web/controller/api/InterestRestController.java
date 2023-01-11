package com.project.worksout.web.controller.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.worksout.service.interest.InterestService;
import com.project.worksout.web.dto.CMRespDto;
import com.project.worksout.web.dto.interest.GetinterestRespDto;
import com.project.worksout.web.dto.interest.InterestRespdto;

import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/api/v1/interest")
@RequiredArgsConstructor
public class InterestRestController {
	
	private final InterestService interestService;

	@PostMapping("/add")
	public ResponseEntity<?> addInterestProduct(@RequestBody InterestRespdto interestRespdto) {
		boolean status = false;
		
		try {
			status = interestService.addInterestProduct(interestRespdto);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.ok().body(new CMRespDto<>(-1, "failed interestProduct", status));
		}
		
		
		return ResponseEntity.ok().body(new CMRespDto<>(1, "add interestProduct", status));
	}
	
	@GetMapping("/flag/{productGroup}")
	public ResponseEntity<?> checkInterestProduct(@PathVariable int productGroup) {
	
		List<GetinterestRespDto> listDto = new ArrayList<GetinterestRespDto>();
		
		try {
			listDto =interestService.checkInterestProduct(productGroup);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.ok().body(new CMRespDto<>(-1, "failed load", listDto));
		}
		
		return ResponseEntity.ok().body(new CMRespDto<>(1, "success load", listDto));
	}
	
}
