package com.project.worksout.web.dto.cart;

import com.project.worksout.domain.cart.Cart;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UpdateCartRespDto {

	private int cartCode;
	private int productCode;
	private int productGroup;
	private int cartProductPrice;
	private int cartProductAmount;
	
	public Cart updateCartToEntity() {
		return Cart.builder()
				.cart_code(cartCode)
				.product_code(productCode)
				.product_group(productGroup)
				.cart_price(cartProductPrice)
				.cart_amount(cartProductAmount)
				.build();
	}
	
}
