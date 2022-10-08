package com.project.worksout.web.dto.cart;

import com.project.worksout.domain.cart.Cart;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddCartReqDto {

	private String username;
	private int productCode;
	private int productGroup;
	private String productSize;
	
	
	public Cart cartEntity() {
		return Cart.builder()
				.user_id(username)
				.product_code(productCode)
				.product_group(productGroup)
				.product_size(productSize)
				.build();
	}
	
}
