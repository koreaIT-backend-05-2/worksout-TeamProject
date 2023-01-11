package com.project.worksout.service.payment;

import java.util.List;

import com.project.worksout.web.dto.payment.AddPaymentProductReqDto;
import com.project.worksout.web.dto.payment.GetPaymentProductRespDto;

public interface PaymentService {

	public List<GetPaymentProductRespDto> getPaymentProductList(String paymentType, String keyCode) throws Exception;
	
	public boolean addPaymentProduct(List<AddPaymentProductReqDto> addPaymentProductReqDto) throws Exception;
}
