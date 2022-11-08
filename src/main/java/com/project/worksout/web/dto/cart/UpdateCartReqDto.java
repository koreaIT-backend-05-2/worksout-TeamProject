package com.project.worksout.web.dto.cart;

import com.project.worksout.domain.cart.Cart;

import lombok.Data;

@Data
public class UpdateCartReqDto {
	private int cartCode;
	private int cartAmount;
	
	public Cart toEntity() {
		return Cart.builder()
				.cart_code(cartCode)
				.cart_amount(cartAmount)
				.build();
	}
}
