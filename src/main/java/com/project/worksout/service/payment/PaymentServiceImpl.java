package com.project.worksout.service.payment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.project.worksout.domain.payment.Payment;
import com.project.worksout.domain.payment.PaymentRepositiory;
import com.project.worksout.web.dto.payment.AddPaymentProductReqDto;
import com.project.worksout.web.dto.payment.GetPaymentProductRespDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService{
	
	private final PaymentRepositiory paymentRepositiory;

	@Override
	public List<GetPaymentProductRespDto> getPaymentProductList(String paymentType, String keyCode) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("payment_type", paymentType);
		map.put("key_code", keyCode);
		
		System.out.println(">>>>>>>>>>paymentType: " + paymentType);
		System.out.println(">>>>>>>>>>keyCode: " + keyCode);
		
		List<GetPaymentProductRespDto> productList = new ArrayList<GetPaymentProductRespDto>();
		
		paymentRepositiory.getPaymentProductList(map).forEach(product -> {
			productList.add(product.toPaymentProductListDto());
		});
		
		System.out.println("?????: " + productList);
		
		return productList;
	}
	
	@Override
	public boolean addPaymentProduct(List<AddPaymentProductReqDto> addPaymentProductReqDto) throws Exception {
		boolean status = false;
		
		List<Payment> payments = new ArrayList<Payment>();
		
		addPaymentProductReqDto.forEach(paymentList -> {
			payments.add(paymentList.toPaymentEntity());
		});
		
		System.out.println("test" + payments);
		System.out.println("size" + payments.size());
		
		status = paymentRepositiory.savePaymentProduct(payments) > 0;
		

		if(status) {
			status = paymentRepositiory.savePaymentState(payments) > 0;
			
		}
				
		return status;
	}
}
