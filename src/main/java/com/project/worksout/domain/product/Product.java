package com.project.worksout.domain.product;

import java.time.LocalDateTime;

import com.project.worksout.web.dto.product.ProductListRespDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Product {
	private int product_code;
	private String product_brand;
	private String product_kind;
	private String product_name;
	private String product_info;
	private int product_price;
	private int product_amount;
	private String product_size;
	private int importance_flag;
	private int total_count;
	private int file_code;
	private String file_name;
	private LocalDateTime create_date;
	private LocalDateTime update_date;	
	
	public ProductListRespDto toListDto() {
		return ProductListRespDto.builder()
				.productCode(product_code)
				.productBrand(product_brand)
				.productKind(product_kind)
				.productName(product_name)
				.productInfo(product_info)
				.productPrice(product_price)
				.productAmount(product_amount)
				.productSize(product_size)
				.importanceFlag(importance_flag = 0)
				.totalCount(total_count = 0)
				.createDate(create_date)
				.updateDate(update_date)
				.build();
	}
}
