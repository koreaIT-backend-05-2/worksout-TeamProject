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
	

	
}
