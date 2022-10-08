package com.project.worksout.domain.cart;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Cart {

	private int cart_code;
	private String user_id;
	private int product_group;
	private String product_size;
	
	private int cart_amount;
	
	private int product_code;
	private String product_name;
	private String product_detail_name;
	private String product_amount;
	private int product_price;
	
	private int file_code;
	private String file_name;
	
}
