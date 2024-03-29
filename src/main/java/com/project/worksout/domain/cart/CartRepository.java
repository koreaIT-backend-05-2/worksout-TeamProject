package com.project.worksout.domain.cart;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CartRepository {

	public int saveCart(Cart cart) throws Exception;
	public List<Cart> getCartList(Map<String, Object> map) throws Exception;
	
}
