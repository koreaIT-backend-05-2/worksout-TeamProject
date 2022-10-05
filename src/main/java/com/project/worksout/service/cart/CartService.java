package com.project.worksout.service.cart;

import com.project.worksout.web.dto.cart.AddCartReqDto;

public interface CartService {

	public boolean addCart(AddCartReqDto addCartReqDto) throws Exception;

}
