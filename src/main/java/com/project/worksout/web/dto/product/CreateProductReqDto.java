package com.project.worksout.web.dto.product;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.project.worksout.domain.product.Product;
import com.project.worksout.domain.product.ProductSize;

import edu.emory.mathcs.backport.java.util.Arrays;
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
	private String productGender;
	private int importanceFlag;
	private int totalCount;
	private List<MultipartFile> file;
	
	private String productSize;
	private int productAmount;
	
}
