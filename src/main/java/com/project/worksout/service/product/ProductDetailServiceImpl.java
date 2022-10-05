package com.project.worksout.service.product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.project.worksout.domain.product.Product;
import com.project.worksout.domain.product.ProductRepository;
import com.project.worksout.web.dto.product.ProductDetailRespDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductDetailServiceImpl implements ProductDetailService{

	private final ProductRepository productRepository;
	
	@Override
	public ProductDetailRespDto getProductDetail(int productCode) throws Exception {
		ProductDetailRespDto productDetailRespDto = null;
		
		Map<String, Object> reqMap = new HashMap<String, Object>();
		reqMap.put("product_code", productCode);
		
		List<Product> products = productRepository.getProductDetail(reqMap);
		
		List<Map<String, Object>> productFile = new ArrayList<Map<String,Object>>();
		
		products.forEach(product -> {
			Map<String, Object> fileMap = new HashMap<String, Object>();
			
			fileMap.put("fileCode", product.getFile_code());
			fileMap.put("fileName", product.getFile_name());
			
			productFile.add(fileMap);
		});
		
		
		Product detailProduct = products.get(0);
		
		productDetailRespDto = ProductDetailRespDto.builder()
				.productCode(detailProduct.getProduct_code())
				.productBrand(detailProduct.getProduct_brand())
				.productName(detailProduct.getProduct_name())
				.productDetailName(detailProduct.getProduct_detail_name())
				.productKorName(detailProduct.getProduct_kor_name())
				.productPrice(detailProduct.getProduct_price())
				.productSize(detailProduct.getProduct_size())
				.productInfo(detailProduct.getProduct_info())
				.productImgFiles(productFile)
				.build();
		
		return productDetailRespDto;
		
	}
	
	
	
}
