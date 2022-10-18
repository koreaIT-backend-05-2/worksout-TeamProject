package com.project.worksout.web.dto.cart;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetCartRespDto {

	private int userCode;
	private int cartCode;
	private String cartProductBrand;
	private String cartProductName;
	private String cartProductSize;
	private int cartProductAmount;
	private int cartProductPrice;
	private String cartProductFileName;
	

	
}
