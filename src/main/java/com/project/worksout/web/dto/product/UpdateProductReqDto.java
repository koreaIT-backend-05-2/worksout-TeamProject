package com.project.worksout.web.dto.product;

import com.project.worksout.domain.product.Product;

import lombok.Data;

@Data
public class UpdateProductReqDto {
	private int productCode;
	private int productGroup;
	private String productBrand;
	private String productName;
	private String productDetailName;
	private String productKorName;
	private String productKind;
	private String productInfo;
	private int productPrice;
	private String productSize;
	private int productAmount;
	private String productGender;
	
	public Product toEntity() {
		return Product.builder()
				.product_code(productCode)
				.product_group(productGroup)
				.product_brand(productBrand)
				.product_name(productName)
				.product_detail_name(productDetailName)
				.product_kor_name(productKorName)
				.product_kind(productKind)
				.product_info(productInfo)
				.product_price(productPrice)
				.product_size(productSize)
				.product_amount(productAmount)
				.product_gender(productGender)
				.build();
	}
	
	
}
