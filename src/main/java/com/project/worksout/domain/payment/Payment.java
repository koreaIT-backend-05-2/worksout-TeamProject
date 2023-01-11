package com.project.worksout.domain.payment;

import java.time.LocalDateTime;

import com.project.worksout.web.dto.payment.AddPaymentProductReqDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Payment {

	private int payment_code;
	private int user_code;
	private int product_group;
	private int product_code;
	private int cart_code;
	private String payment_request;
	private LocalDateTime create_date;
	
	private int payment_state_code;
	private int shipping_state;
	private int payment_state;
	
	private int payment_state_dtl_code;
	private int cancel;
	private int change;
	private int refund;
	private int completion;
	private int confirmation;
	
//	public AddPaymentProductReqDto toPaymentEntity() {
//		return AddPaymentProductReqDto.builder()
//					.userCode(user_code)
//					.productCode(product_code)
//					.cartCode(cart_code)
//					.paymentRequest(payment_request)
//					.createDate(create_date)
//					.build();
//	}
	

	
}
