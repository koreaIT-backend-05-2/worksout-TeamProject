package com.project.worksout.web.controller.mailto;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.worksout.web.dto.CMRespDto;

@RestController
@RequestMapping("/mail")
public class MailToRestController {

	
	@GetMapping("/send")
	public ResponseEntity<?> sendMail(@RequestParam String email) {
		return ResponseEntity.ok().body(new CMRespDto<>(1, "test", "test"));
	}
	
}
