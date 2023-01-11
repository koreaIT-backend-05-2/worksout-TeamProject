package com.project.worksout.web.controller.api.payment;

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

import com.project.worksout.service.payment.PaymentService;
import com.project.worksout.web.dto.CMRespDto;
import com.project.worksout.web.dto.payment.AddPaymentProductReqDto;
import com.project.worksout.web.dto.payment.GetPaymentProductRespDto;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/payment")
@RequiredArgsConstructor
public class PaymentRestController {
	
	private final PaymentService paymentService;

	@GetMapping("/list")
	public ResponseEntity<?> getPaymentProduct(@RequestParam String paymentType, @RequestParam String keyCode) {
		List<GetPaymentProductRespDto> listDto = new ArrayList<GetPaymentProductRespDto>();
		
		System.out.println("controllerType: " + paymentType);
		System.out.println("controller keyCode: " + keyCode);
				
		try {
			listDto = paymentService.getPaymentProductList(paymentType, keyCode);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().body(new CMRespDto<>(-1, "failedrequest", listDto));
		}
		
		return ResponseEntity.ok().body(new CMRespDto<>(1, "success request", listDto));
	}

	@PostMapping("/addPayment")
	public ResponseEntity<?> addPaymentProduct(@RequestBody List<AddPaymentProductReqDto> addPaymentProductReqDto) {

		System.out.println(addPaymentProductReqDto);
		
		boolean dto = false;
		
		try {
			dto = paymentService.addPaymentProduct(addPaymentProductReqDto);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().body(new CMRespDto<>(-1, "failed add paymentList", dto));
		}
		
		
		return ResponseEntity.ok().body(new CMRespDto<>(1, "success add paymentList",  dto));
	}
	
}
