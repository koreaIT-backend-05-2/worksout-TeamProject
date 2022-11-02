package com.project.worksout.service.payment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.project.worksout.domain.payment.PaymentRepositiory;
import com.project.worksout.web.dto.payment.GetPaymentProductRespDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService{
	
	private final PaymentRepositiory paymentRepositiory;

	@Override
	public List<GetPaymentProductRespDto> getPaymentProductList(int userCode) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("user_code", userCode);
		
		List<GetPaymentProductRespDto> productList = new ArrayList<GetPaymentProductRespDto>();
		
		paymentRepositiory.getPaymentProductList(map).forEach(product -> {
			productList.add(product.toPaymentProductListDto());
		});
		
		return productList;
	}
}
