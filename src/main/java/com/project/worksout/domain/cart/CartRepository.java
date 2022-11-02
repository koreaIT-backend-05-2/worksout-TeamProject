package com.project.worksout.domain.cart;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CartRepository {

	public int saveCart(Cart cart) throws Exception;
	
	public List<Cart> getCartList(Map<String, Object> map) throws Exception;
	
	public int updateCartByCartCode(Cart cart) throws Exception;
	
	public int updateCartFlag(int cartCode) throws Exception;
	
	public int updateFirstCartFlag(int userCode) throws Exception;
	
	public int updateAllCartFlag(int userCode) throws Exception;
	
	public int removeCartByCartCode(int cartCode) throws Exception;
	
	public int removeChoiceCartByPayFlag(int userCode) throws Exception;
	
}
