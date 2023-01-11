package com.project.worksout.web.dto.payment;

import java.time.LocalDateTime;

import com.project.worksout.domain.payment.Payment;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddPaymentProductReqDto {

	private int userCode;
	private int productCode;
	private int productGroup;
	private int cartCode;
	private String paymentRequest;
	private LocalDateTime createDate;
	
	public Payment toPaymentEntity() {
		return Payment.builder()
				.user_code(userCode)
				.product_code(productCode)
				.product_group(productGroup)
				.cart_code(cartCode)
				.payment_request(paymentRequest)
				.create_date(createDate)
				.build();
	}
	
}
