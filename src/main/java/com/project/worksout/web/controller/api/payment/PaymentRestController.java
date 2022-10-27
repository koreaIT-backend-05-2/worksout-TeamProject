package com.project.worksout.web.controller.api.payment;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentRestController {

	
	@PostMapping("")
	public ResponseEntity<?> paymentResult(@RequestBody String imp_uid) {
		
		
		return ResponseEntity.ok(null);
	}
}
