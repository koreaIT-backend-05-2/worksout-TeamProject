package com.project.worksout.web.dto.product;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ProductListRespDto {
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
	private int importanceFlag;
	private int totalCount;
	private LocalDateTime createDate;
	private LocalDateTime updateDate;
	
}
