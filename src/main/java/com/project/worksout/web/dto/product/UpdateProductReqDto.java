package com.project.worksout.web.dto.product;

import com.project.worksout.domain.product.Product;

import lombok.Data;

@Data
public class UpdateProductReqDto {
	private int productCode;
	private String productBrand;
	private String productName;
	private String productKind;
	private String productInfo;
	private int productPrice;
	private int productAmount;
	private String productSize;
	
	public Product toEntity() {
		return Product.builder()
				.product_code(productCode)
				.product_brand(productBrand)
				.product_kind(productKind)
				.product_info(productInfo)
				.product_price(productPrice)
				.product_amount(productAmount)
				.product_size(productSize)
				.build();
	}
	
}
