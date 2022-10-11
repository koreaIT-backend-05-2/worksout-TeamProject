package com.project.worksout.web.dto.product;

import java.util.List;
import java.util.Map;

import com.project.worksout.domain.product.ProductFile;
import com.project.worksout.domain.product.ProductSize;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductDetailRespDto {

	private int productCode;
	private int productGroup;
	private String productBrand;
	private String productName;
	private String productDetailName;
	private String productKorName;
	private int productPrice;
	private String productInfo;
	
	private List<ProductSize> productSizeList;
	private List<ProductFile> productFileList;
	
}
