package com.project.worksout.service.product;

import java.util.List;

import com.project.worksout.web.dto.product.CreateProductReqDto;
import com.project.worksout.web.dto.product.ProductListRespDto;
import com.project.worksout.web.dto.product.ProductRespDto;
import com.project.worksout.web.dto.product.UpdateProductReqDto;

public interface ProductService {
	//추가 (Create)
	public int createProduct(CreateProductReqDto createProductReqDto) throws Exception;
	
	//조회 (Select)
	public List<ProductListRespDto> getProductList(int type, String searchFlag, String searchValue) throws Exception;
	public ProductRespDto getProduct(int productCode) throws Exception;

	//수정 (Update)
	public boolean updateProduct(UpdateProductReqDto updateProductReqDto) throws Exception;
	
	//삭제 (Delete)
	public boolean removeProduct(int productCode) throws Exception;
}
