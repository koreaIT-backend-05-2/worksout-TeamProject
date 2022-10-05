package com.project.worksout.web.dto.product;

import java.util.List;
import java.util.Map;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductDetailRespDto {

	private int productCode;
	private String productBrand;
	private String productName;
	private String productDetailName;
	private String productKorName;
	private int productPrice;
	private String productSize; 
	private String productInfo;
	
	private List<Map<String, Object>> productImgFiles;
	
}
