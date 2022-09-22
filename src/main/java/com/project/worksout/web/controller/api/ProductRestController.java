package com.project.worksout.web.controller.api;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.worksout.service.product.ProductService;
import com.project.worksout.web.dto.CMRespDto;
import com.project.worksout.web.dto.product.CreateProductReqDto;
import com.project.worksout.web.dto.product.ProductListRespDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
public class ProductRestController {
	
//	@Value("${file.path}")
//	private String filePath;

	private final ProductService productService;
	// 상품 등록
	@PostMapping("/insert")
	public ResponseEntity<?> insertProduct(CreateProductReqDto createProductReqDto){
		//boolean status = false;
		int status = 0;
		
		log.info("{}", createProductReqDto);
		log.info("fileName: = {}", createProductReqDto.getFile().get(0).getOriginalFilename());
//		log.info("filePath: = {}", filePath);
		
		
		try {
			status = productService.createProduct(createProductReqDto);
			System.out.println(status);
//			if(!status) {
//				throw new RuntimeException("DB ERROR!!");
//			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().body(new CMRespDto<>(-1,"failed", status));
		}
		
		return ResponseEntity.ok().body(new CMRespDto<>(1,"success", status));
		//return null;
	}
	
	// 상품 조회
	@PostMapping("/search")
	public ResponseEntity<?> searchProduct(){
		
		return ResponseEntity.ok().body(new CMRespDto<>(1,"",""));
	}
	
	// 상품 수정
	@PostMapping("/edit")
	public ResponseEntity<?> editProduct(){
		
		return ResponseEntity.ok().body(new CMRespDto<>(1,"",""));
	}
	
	// 상품 삭제
	@PostMapping("/delete")
	public ResponseEntity<?> deleteProduct(){
		
		return ResponseEntity.ok().body(new CMRespDto<>(1,"",""));
	}
	
	// test
	@PostMapping("/testing")
	public ResponseEntity<?> testing(@RequestBody ProductListRespDto productRegisterReqDto){
		return ResponseEntity.ok().body(new CMRespDto<>(1,"success", productRegisterReqDto));
	}
}
