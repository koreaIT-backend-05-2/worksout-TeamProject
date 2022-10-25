package com.project.worksout.web.dto.cart;

import java.util.List;
import java.util.Map;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetCartRespDto {

	private int cartCode;
	private int userCode;
	
	private int productCode;
	private Map<String, Object> productImg;
	private String productName;
	private String productDetailName;
	private String productSize;
	private int productAmount;
	private int productPrice;
	
	private int cartAmount;
	
	private int pay_flag;
	
}
