package com.project.worksout.domain.cart;

import com.project.worksout.web.dto.cart.GetCartRespDto;
import com.project.worksout.web.dto.payment.GetPaymentProductRespDto;

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
	private int product_code;
	private int product_group;
	private String product_size;
	private int cart_price;
	private int cart_amount;
	private int pay_flag;
	
	
	private String product_brand;
	private String product_detail_name;
	private int product_price;
	
	private int file_code;
	private String file_name;
	
	public GetCartRespDto toCartListDto() {
		return GetCartRespDto.builder()
					.userCode(user_code)
					.cartCode(cart_code)
					.productCode(product_code)
					.productGroup(product_group)
					.cartProductBrand(product_brand)
					.cartProductName(product_detail_name)
					.cartProductSize(product_size)
					.cartProductAmount(cart_amount)
					.cartProductPrice(product_price)
					.cartProductFileName(file_name)
					.payFlag(pay_flag)
					.build();
				
	}
	
	public GetPaymentProductRespDto toPaymentProductListDto() {
		return GetPaymentProductRespDto.builder()
				.userCode(user_code)
				.cartCode(cart_code)
				.productAmount(1)
				.cartProductAmount(cart_amount)
				.cartProductPrice(product_price)
				.productCode(product_code)
				.productGroup(product_group)
				.productBrand(product_brand)
				.productName(product_detail_name)
				.productSize(product_size)
				.productFileName(file_name)
				.build();
	}
	
}
