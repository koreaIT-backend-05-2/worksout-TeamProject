package com.project.worksout.domain.cart;

import com.project.worksout.web.dto.cart.GetCartRespDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Cart {

	private int cart_code;
	private int user_code;
	private int product_group;
	private String product_size;
	private int product_code;
	private int cart_amount;
	
	
	private String product_brand;
	private String product_detail_name;
	private int product_price;
	
	private int file_code;
	private String file_name;
	
	public GetCartRespDto toCartListDto() {
		return GetCartRespDto.builder()
					.userCode(user_code)
					.cartCode(cart_code)
					.cartProductBrand(product_brand)
					.cartProductName(product_detail_name)
					.cartProductSize(product_size)
					.cartProductAmount(cart_amount)
					.cartProductPrice(product_price)
					.cartProductFileName(file_name)
					.build();
				
					
	}
	
}
