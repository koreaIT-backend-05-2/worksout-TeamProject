package com.project.worksout.web.dto.payment;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddPaymentProductReqDto {

	private int userCode;
	private int productCode;
	private int cartCode;
	private int productprice;
	private int productAmount;
	private String paymentRequest;
	private LocalDateTime createDate;
	
}
