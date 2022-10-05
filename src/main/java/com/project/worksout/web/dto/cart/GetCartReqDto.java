package com.project.worksout.web.dto.cart;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetCartReqDto {

	private String productImg;
	private String productName;
	private String productKind;
	private String productSize;
	private int productAmount;
	private int productPrice;
	
}
