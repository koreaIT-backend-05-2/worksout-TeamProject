package com.project.worksout.service.product;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.function.Predicate;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.project.worksout.domain.product.Product;
import com.project.worksout.domain.product.ProductFile;
import com.project.worksout.domain.product.ProductRepository;
import com.project.worksout.web.dto.product.CreateProductReqDto;
import com.project.worksout.web.dto.product.ProductListRespDto;
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
//		Product productEntity = createProductReqDto.toEntity();
//		
//		return productRepository.save(productEntity) > 0;
		
		Predicate<String> predicate = (filename) -> !filename.isBlank();
		
		Product product = null;
		
		//log.info(createProductReqDto);
		System.out.println(createProductReqDto);
		log.info(createProductReqDto.getProductBrand());
		
		product = Product.builder()
				.product_code(createProductReqDto.getProductCode())
				.product_brand(createProductReqDto.getProductBrand())
				.product_kind(createProductReqDto.getProductKind())
				.product_name(createProductReqDto.getProductName())
				.product_info(createProductReqDto.getProductInfo())
				.product_price(createProductReqDto.getProductPrice())
				.product_amount(createProductReqDto.getProductAmount())
				.product_size(createProductReqDto.getProductSize())
				.build();
		
		
		productRepository.save(product);
		
		// file이 없는경우
		if(predicate.test(createProductReqDto.getFile().get(0).getOriginalFilename())) {
			List<ProductFile> productFiles = new ArrayList<ProductFile>();
			
			for(MultipartFile file : createProductReqDto.getFile()) {
				String originalFilename = file.getOriginalFilename();
				String tempFilename = UUID.randomUUID().toString().replaceAll("-", "") + "_" + originalFilename;
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
	public List<ProductListRespDto> getProductList(String type, int page, int contentCount) throws Exception {
		
		return null;
	}

	@Override
	public boolean updateProduct(UpdateProductReqDto updateProductReqDto) throws Exception {
		
		return false;
	}

	@Override
	public boolean removeProduct(int productCode) throws Exception {
		
		return false;
	}

}
