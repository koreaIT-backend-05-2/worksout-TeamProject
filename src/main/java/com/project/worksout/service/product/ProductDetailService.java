package com.project.worksout.service.product;

import com.project.worksout.web.dto.product.ProductDetailRespDto;

public interface ProductDetailService {

	public ProductDetailRespDto getProductDetail(int productGroup) throws Exception;
	
}
