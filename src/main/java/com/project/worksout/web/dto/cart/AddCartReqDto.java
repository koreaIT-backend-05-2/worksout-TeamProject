package com.project.worksout.web.dto.cart;

import com.project.worksout.domain.cart.Cart;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddCartReqDto {

	private int userCode;
	private int productCode;
	private int productGroup;
	private String productSize;
	private int cartAmount;
	
	public Cart cartEntity() {
		return Cart.builder()
				.user_code(userCode)
				.product_code(productCode)
				.product_group(productGroup)
				.product_size(productSize)
				.cart_amount(1)
				.build();
	}
	
}
