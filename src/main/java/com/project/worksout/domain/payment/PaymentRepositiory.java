package com.project.worksout.domain.payment;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.project.worksout.domain.cart.Cart;

@Mapper
public interface PaymentRepositiory {

	public List<Cart> getPaymentProductList(Map<String, Object> map) throws Exception;

}
