package com.project.worksout.service.cart;

import java.util.List;

import com.project.worksout.web.dto.cart.AddCartReqDto;
import com.project.worksout.web.dto.cart.GetCartRespDto;

public interface CartService {

	public boolean addCart(AddCartReqDto addCartReqDto) throws Exception;
	public List<GetCartRespDto> getCartList(int userCode) throws Exception;
	
}
