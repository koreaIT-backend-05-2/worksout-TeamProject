package com.project.worksout.web.dto.product;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.project.worksout.domain.product.Product;

import lombok.Data;

@Data
public class CreateProductReqDto {
	private int productCode;
	private String productBrand;
	private String productKind;
	private String productName;
	private String productDetailName;
	private String productKorName;
	private String productInfo;
	private int productPrice;
	private int productAmount;
	private String productSize;
	private String productGender;
	private int importanceFlag;
	private int totalCount;
	private List<MultipartFile> file;
	
//	public Product toEntity() {
//		return Product.builder()
//				.product_brand(productBrand)
//				.product_kind(productKind)
//				.product_name(productName)
//				.product_info(productInfo)
//				.product_price(productPrice)
//				.product_amount(productAmount)
//				.product_size(productSize)
//				.importance_flag(importanceFlag)
//				.total_count(totalCount)
//				.build();
//	}
}
