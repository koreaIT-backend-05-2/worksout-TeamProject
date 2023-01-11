package com.project.worksout.service.cart;

import java.util.List;

import com.project.worksout.web.dto.cart.AddCartReqDto;
import com.project.worksout.web.dto.cart.GetCartRespDto;
import com.project.worksout.web.dto.cart.UpdateCartRespDto;

public interface CartService {

	public boolean addCart(AddCartReqDto addCartReqDto) throws Exception;
	
	public List<GetCartRespDto> getCartList(int userCode) throws Exception;
	
	public boolean updateCart(UpdateCartRespDto updateCartRespDto) throws Exception;
	
	public boolean updateCartFlag(int cartCode) throws Exception;
	
	public boolean updateFirstCartFlag(int userCode)throws Exception;
	
	public boolean updateAllCartFlag(int userCode) throws Exception;
	
	public boolean removeCart(int cartCode) throws Exception;
	
	public boolean removeChoiceCart(int userCode) throws Exception;
	
}
