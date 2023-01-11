package com.project.worksout.web.dto.payment;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetPaymentProductRespDto {

	private int userCode;
	
	private int cartCode;
	private int cartProductAmount;
	private int productAmount;
	private int cartProductPrice;
	
	private int productCode;
	private int productGroup;
	private String productBrand;
	private String productName;
	private String productSize;
	private String productFileName;
	
}
