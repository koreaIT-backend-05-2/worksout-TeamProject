package com.project.worksout.domain.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductSize {

	private int product_code;
	private String size_name;
	private int size_amount;
	
}
