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
	private String productBrand;
	private String productKind;
	private String productName;
	private String productInfo;
	private int productPrice;
	private int productAmount;
	private String productSize;
	private String productGender;
	private int importanceFlag;
	private int totalCount;
	private LocalDateTime createDate;
	private LocalDateTime updateDate;
	private List<Map<String, Object>> files;
	
}
