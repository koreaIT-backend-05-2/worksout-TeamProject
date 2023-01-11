package com.project.worksout.service.product;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.project.worksout.domain.product.Product;
import com.project.worksout.domain.product.ProductFile;
import com.project.worksout.domain.product.ProductRepository;
import com.project.worksout.domain.product.ProductSize;
import com.project.worksout.web.dto.product.CreateProductReqDto;
import com.project.worksout.web.dto.product.ProductListRespDto;
import com.project.worksout.web.dto.product.ProductRespDto;
import com.project.worksout.web.dto.product.UpdateProductReqDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

	@Value("${file.path}")
	private String filePath;
	
	private final ProductRepository productRepository;
	
	// 추가
	@Override
	public int createProduct(CreateProductReqDto createProductReqDto) throws Exception {		
		Predicate<String> predicate = (filename) -> !filename.isBlank();
		
		Product product = null;
		
		System.out.println(createProductReqDto);
		log.info(createProductReqDto.getProductBrand());
		
		product = Product.builder()
				.product_code(createProductReqDto.getProductCode())
				.product_brand(createProductReqDto.getProductBrand())
				.product_kind(createProductReqDto.getProductKind())
				.product_name(createProductReqDto.getProductName())
				.product_detail_name(createProductReqDto.getProductDetailName())
				.product_kor_name(createProductReqDto.getProductKorName())
				.product_info(createProductReqDto.getProductInfo())
				.product_price(createProductReqDto.getProductPrice())
				.product_size(createProductReqDto.getProductSize())
				.product_amount(createProductReqDto.getProductAmount()) 
				.product_gender(createProductReqDto.getProductGender())
				.build();
		
		
		productRepository.save(product);
		
		if(predicate.test(createProductReqDto.getFile().get(0).getOriginalFilename())) {
			List<ProductFile> productFiles = new ArrayList<ProductFile>();
			
			for(MultipartFile file : createProductReqDto.getFile()) {
				String originalFilename = file.getOriginalFilename();
//				String tempFilename = UUID.randomUUID().toString().replaceAll("-", "") + "_" + originalFilename;
				String tempFilename = originalFilename;
				log.info(tempFilename);
				Path uploadPath = Paths.get(filePath, "product/" + tempFilename);
				
				File f = new File(filePath + "product");
				if(!f.exists()) {
					f.mkdirs();
				}
				
				try {
					Files.write(uploadPath, file.getBytes());					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				productFiles.add(ProductFile.builder().product_code(product.getProduct_code()).file_name(tempFilename).build());
			}
			productRepository.saveProductFiles(productFiles);
			
		}
		
		return product.getProduct_code();
	}

	@Override
	public ProductRespDto getProduct(int productCode) throws Exception {
	
		ProductRespDto productRespDto = null;
			
		Map<String, Object> reqMap = new HashMap<String, Object>();
		reqMap.put("product_code", productCode);
		
		Product product = productRepository.getProduct(reqMap);
		
		if(product != null) {
			List<Map<String, Object>> files = new ArrayList<Map<String,Object>>();
			
			product.getProduct_files().forEach(file -> {
				Map<String, Object> fileMap = new HashMap<String, Object>();
				fileMap.put("fileCode", file.getFile_code());
				fileMap.put("fileName", file.getFile_name());
				files.add(fileMap);
			});
			
			productRespDto = product.toProductRespDto(files);
		}
		log.info("{}", productRespDto);
		
		
		return productRespDto;
	}
	@Override
	public List<ProductListRespDto> getProductList(int page, String searchFlag, String searchValue) throws Exception {
		int index = (page - 1) * 10;
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("index", index);
		map.put("search_flag", searchFlag);
		map.put("search_value", searchValue == null ? "" : searchValue);
		
		
		List<ProductListRespDto> productList = new ArrayList<ProductListRespDto>();
		
		productRepository.getProductList(map).forEach(product -> {
			List<Map<String, Object>> files = new ArrayList<Map<String,Object>>();
			
//			log.info("<<<<<<<<<<{}>>>>>>>>>", product);
			
			product.getProduct_files().forEach(file -> {
				Map<String, Object> fileMap = new HashMap<String, Object>();
				fileMap.put("fileCode", file.getFile_code());
				fileMap.put("fileName", file.getFile_name());
				files.add(fileMap);
			});
			
			productList.add(product.toProductListRespDto(files));
		});
		
		return productList;
	}

	@Override
	public boolean updateProduct(UpdateProductReqDto updateProductReqDto) throws Exception {
		
		return productRepository.updateProduct(updateProductReqDto.toEntity()) > 0;
	}

	@Override
	public boolean removeProduct(int productCode) throws Exception {
		
		return productRepository.deleteProduct(productCode) > 0;
	}

}
