package com.project.worksout.service.cart;

import java.util.List;

import com.project.worksout.web.dto.cart.AddCartReqDto;
import com.project.worksout.web.dto.cart.GetCartRespDto;
import com.project.worksout.web.dto.cart.UpdateCartReqDto;

public interface CartService {

	public boolean addCart(AddCartReqDto addCartReqDto) throws Exception;
	public List<GetCartRespDto> getCartList(String username) throws Exception;
	
	public boolean updateCartAmount(UpdateCartReqDto updateCartReqDto) throws Exception;
	
	public boolean removeCart(int cartCode) throws Exception;
}
