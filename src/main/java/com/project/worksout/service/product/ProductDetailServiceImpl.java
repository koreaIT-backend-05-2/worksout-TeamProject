package com.project.worksout.service.product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
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
	public ProductDetailRespDto getProductDetail(int productGroup) throws Exception {
		ProductDetailRespDto productDetailRespDto = null;
		
		Map<String, Object> reqMap = new HashMap<String, Object>();
		reqMap.put("product_group", productGroup);
		
		List<Product> products = productRepository.getProductDetail(reqMap);
		
		log.info("group {}", products);
		
		
//		List<Map<String, Object>> productFile = new ArrayList<Map<String,Object>>();
//		
//		products.forEach(product -> {
//			Map<String, Object> fileMap = new HashMap<String, Object>();
//			
//			fileMap.put("fileCode", product.getFile_code());
//			fileMap.put("fileName", product.getFile_name());
//			
//			productFile.add(fileMap);
//		});
//		
//		List<Map<String, Object>> productSizeList = new ArrayList<Map<String,Object>>();
//		
//		products.forEach(size -> {
//			Map<String, Object> sizeAmount = new HashMap<String, Object>();
//			
//			sizeAmount.put("productSize", size.getProduct_size());
//			
//			productSizeList.add(sizeAmount);
//			
//		});
				
		if(products.size() != 0) {
			Product detailProduct = products.get(0);
			
			productDetailRespDto = ProductDetailRespDto.builder()
					.productCode(detailProduct.getProduct_code())
					.productGroup(detailProduct.getProduct_group())
					.productBrand(detailProduct.getProduct_brand())
					.productName(detailProduct.getProduct_name())
					.productDetailName(detailProduct.getProduct_detail_name())
					.productKorName(detailProduct.getProduct_kor_name())
					.productPrice(detailProduct.getProduct_price())
//					.productAmount(detailProduct.getProduct_amount())
					.productSizeList(detailProduct.getProduct_size_list())
					.productInfo(detailProduct.getProduct_info())
					.productFileList(detailProduct.getProduct_files())
					.build();
			
//			productDetailRespDto = ProductDetailRespDto.builder()
//					.productCode(detailProduct.getProduct_code())
//					.productGroup(detailProduct.getProduct_group())
//					.productBrand(detailProduct.getProduct_brand())
//					.productName(detailProduct.getProduct_name())
//					.productDetailName(detailProduct.getProduct_detail_name())
//					.productKorName(detailProduct.getProduct_kor_name())
//					.productPrice(detailProduct.getProduct_price())
//					.productSizeList(productSizeList)
//					.productInfo(detailProduct.getProduct_info())
//					.productImgFiles(productFile)
//					.build();
		}
				
			
			log.info(">>>>>{} " ,productDetailRespDto);
		
		return productDetailRespDto;
		
		
	}
	
	
	
}
