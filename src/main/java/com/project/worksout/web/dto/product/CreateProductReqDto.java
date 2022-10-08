package com.project.worksout.web.dto.product;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.project.worksout.domain.product.Product;

import lombok.Data;

@Data
public class CreateProductReqDto {
	private int productCode;
	private int productGroup;
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

}
