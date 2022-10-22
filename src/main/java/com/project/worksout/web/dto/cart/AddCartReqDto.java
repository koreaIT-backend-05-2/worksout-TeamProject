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
	private int cartPrice;
	private int cartAmount;
	private int payFlag;
	
	public Cart cartEntity() {
		return Cart.builder()
				.user_code(userCode)
				.product_code(productCode)
				.product_group(productGroup)
				.product_size(productSize)
				.cart_price(cartPrice)
				.cart_amount(1)
				.pay_flag(1)
				.build();
	}
	
}
