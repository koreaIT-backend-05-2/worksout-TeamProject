package com.project.worksout.web.dto.cart;

import com.project.worksout.domain.cart.Cart;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetCartRespDto {

	private int userCode;
	private int cartCode;
	private String productFileName;
	private String productName;
	private String productDetailName;
	private String productSize;
	private int productAmount;
	private int productPrice;
	
	public Cart getCartToEntity() {
		return Cart.builder()
				.cart_code(cartCode)
				.user_code(userCode)
				.file_name(productFileName)
				.product_name(productName)
				.product_detail_name(productDetailName)
				.product_size(productSize)
				.product_amount(productAmount)
				.product_price(productPrice)
				.build();
	}
	
}
