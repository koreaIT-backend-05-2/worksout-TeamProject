package com.project.worksout.web.dto.product;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ProductListRespDto {
	private int productCode;
	private int productGroup;
	private String productBrand;
	private String productKind;
	private String productName;
	private String productDetailName;
	private String productKorName;
	private String productInfo;
	private int productPrice;
	private List<Integer> productAmountList;
	private List<String> productSizeList;
	private int productAmount;
	private String productSize;
	private String productGender;
	private LocalDateTime createDate;
	private LocalDateTime updateDate;
	private List<Map<String, Object>> files;
	
}
