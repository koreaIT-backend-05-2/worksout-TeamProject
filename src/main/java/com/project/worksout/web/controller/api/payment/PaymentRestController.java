package com.project.worksout.web.controller.api.payment;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.worksout.service.payment.PaymentService;
import com.project.worksout.web.dto.CMRespDto;
import com.project.worksout.web.dto.payment.GetPaymentProductRespDto;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/payment")
@RequiredArgsConstructor
public class PaymentRestController {
	
	private final PaymentService paymentService;

	@GetMapping("/{userCode}")
	public ResponseEntity<?> getPaymentProduct(@PathVariable int userCode) {
		List<GetPaymentProductRespDto> listDto = new ArrayList<GetPaymentProductRespDto>();
		
				
		try {
			listDto = paymentService.getPaymentProductList(userCode);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().body(new CMRespDto<>(-1, "failedrequest", listDto));
		}
		
		return ResponseEntity.ok().body(new CMRespDto<>(1, "success request", listDto));
	}
	
}
