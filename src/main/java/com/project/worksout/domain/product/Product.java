package com.project.worksout.domain.product;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.project.worksout.web.dto.product.ProductListRespDto;
import com.project.worksout.web.dto.product.ProductRespDto;

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
	private int product_group;
	private String product_brand;
	private String product_kind;
	private String product_name;
	private String product_detail_name;
	private String product_kor_name;
	private String product_info;
	private int product_price;
	private int product_amount;
	private String product_size;
	private String product_gender;
	private int importance_flag;
	private int total_count;
	private LocalDateTime create_date;
	private LocalDateTime update_date;
	
	private int file_code;
	private String file_name;
	
	private List<ProductFile> product_files;
	private List<ProductSize> product_size_list;
	
	public ProductListRespDto toProductListRespDto(List<Map<String, Object>> files) {
		return ProductListRespDto.builder()
				.productCode(product_code)
				.productGroup(product_group)
				.productBrand(product_brand)
				.productKind(product_kind)
				.productName(product_name)
				.productDetailName(product_detail_name)
				.productKorName(product_kor_name)
				.productInfo(product_info)
				.productPrice(product_price)
				.productSize(product_size)
				.productAmount(product_amount)
//				.productAmountList(product_amount_list)
//				.productSizeList(product_size_list)
				.productGender(product_gender)
				.createDate(create_date)
				.updateDate(update_date)
				.files(files)
				.build();
	}
	
	public ProductRespDto toProductRespDto(List<Map<String, Object>> files) {
		return ProductRespDto.builder()
				.productCode(product_code)
				.productGroup(product_group)
				.productBrand(product_brand)
				.productKind(product_kind)
				.productName(product_name)
				.productDetailName(product_detail_name)
				.productKorName(product_kor_name)
				.productInfo(product_info)
				.productPrice(product_price)
				.productSize(product_size)
				.productAmount(product_amount)
//				.productSizeList(product_size_list)
//				.productAmountList(product_amount_list)
				.createDate(create_date)
				.updateDate(update_date)
				.files(files)
				.build();
	}
}
