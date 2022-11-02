package com.project.worksout.service.payment;

import java.util.List;

import com.project.worksout.web.dto.payment.GetPaymentProductRespDto;

public interface PaymentService {

	public List<GetPaymentProductRespDto> getPaymentProductList(int userCode) throws Exception;
	
}
