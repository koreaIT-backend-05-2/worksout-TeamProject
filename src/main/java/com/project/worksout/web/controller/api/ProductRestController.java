package com.project.worksout.web.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.worksout.service.product.ProductService;
import com.project.worksout.web.dto.CMRespDto;
import com.project.worksout.web.dto.product.CreateProductReqDto;
import com.project.worksout.web.dto.product.ProductListRespDto;
import com.project.worksout.web.dto.product.ProductRespDto;
import com.project.worksout.web.dto.product.UpdateProductReqDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
public class ProductRestController {

	private final ProductService productService;
	// 상품 등록
	@PostMapping("/insert")
	public ResponseEntity<?> insertProduct(CreateProductReqDto createProductReqDto){
		int status = 0;
		
//		log.info("{}", createProductReqDto);
//		log.info("fileName: = {}", createProductReqDto.getFile().get(0).getOriginalFilename());
//		log.info("filePath: = {}", filePath);
				
		try {
			status = productService.createProduct(createProductReqDto);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().body(new CMRespDto<>(-1,"failed", status));
		}
		
		return ResponseEntity.ok().body(new CMRespDto<>(1,"success", status));
	}
	
	// 상품 조회
	@GetMapping("/list/{type}")
	public ResponseEntity<?> getProductList(@PathVariable int type, @RequestParam String searchFlag, @RequestParam String searchValue){
		List<ProductListRespDto> listDto = null;
		
		try {
			listDto = productService.getProductList(type, searchFlag, searchValue);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().body(new CMRespDto<>(-1,"DB error", listDto));			
		}
		
		return ResponseEntity.ok().body(new CMRespDto<>(1,"searching success", listDto));
	}
	
	// 상품 업데이트를 위한 조회
	@GetMapping("/{productCode}")
	public ResponseEntity<?> getProductForUpdate(@PathVariable int productCode){
		ProductRespDto productRespDto = null;
		
		System.out.println("productCode : " + productCode);
		
		try {
			productRespDto = productService.getProduct(productCode);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().body(new CMRespDto<>(-1,"DB error", productRespDto));			
		}
		
		return ResponseEntity.ok().body(new CMRespDto<>(1,"searching success", productRespDto));
	}
	
	// 상품 수정
	@PutMapping("/edit/{productCode}")
	public ResponseEntity<?> editProduct(@PathVariable int productCode, @RequestBody UpdateProductReqDto updateProductReqDto){
		boolean status = false;
		
		try {
			updateProductReqDto.setProductCode(productCode);
			status = productService.updateProduct(updateProductReqDto);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().body(new CMRespDto<>(-1,"failed update",status));			
		}
		return ResponseEntity.ok().body(new CMRespDto<>(1,"success update",status));
	}
	
	// 상품 삭제
	@DeleteMapping("/delete/{productCode}")
	public ResponseEntity<?> deleteProduct(@PathVariable int productCode){
		boolean status = false;
		try {
			status = productService.removeProduct(productCode);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().body(new CMRespDto<>(-1,"failed delete",status));			
		}
		return ResponseEntity.ok().body(new CMRespDto<>(1,"success delete",status));
	}
	
	// test
	@PostMapping("/testing")
	public ResponseEntity<?> testing(@RequestBody ProductListRespDto productRegisterReqDto){
		return ResponseEntity.ok().body(new CMRespDto<>(1,"success", productRegisterReqDto));
	}
}
