package com.project.worksout.service.product;

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

//	@Value("${file.path}")
//	private String filePath;
	
	private final ProductRepository productRepository;
	// 추가
	@Override
	public boolean createProduct(CreateProductReqDto createProductReqDto) throws Exception {
		Product productEntity = createProductReqDto.toEntity();
//		
		return productRepository.save(productEntity) > 0;
		
//		Predicate<String> predicate = (filename) -> !filename.isBlank();
//		
//		productRepository.save(productEntity);
//		
//		// file이 없는경우
//		if(predicate.test(createProductReqDto.getFile().get(0).getOriginalFilename())) {
//			List<ProductFile> productFiles = new ArrayList<ProductFile>();
//			
//			for(MultipartFile file : createProductReqDto.getFile()) {
//				String originalFilename = file.getOriginalFilename();
//				String tempFilename = UUID.randomUUID().toString().replaceAll("-", "") + "_" + orginalFilename;
//				log.info(tempFilename);
//				//Path uploadPath = Paths.get(tempFilename, null)
//			}
//		}
		
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