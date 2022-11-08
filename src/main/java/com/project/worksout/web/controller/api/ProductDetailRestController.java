package com.project.worksout.web.controller.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.worksout.service.product.ProductDetailService;
import com.project.worksout.web.dto.CMRespDto;
import com.project.worksout.web.dto.product.ProductDetailRespDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductDetailRestController {
	

	private final ProductDetailService productDetailService;
	
	@GetMapping("/{productGroup}")
	public ResponseEntity<?> getDetailProduct(@PathVariable int productGroup) {
		ProductDetailRespDto productDetailRespDto = null;
		
		try {
			productDetailRespDto = productDetailService.getProductDetail(productGroup);
			if(productDetailRespDto == null) {
				return ResponseEntity.badRequest().body(new CMRespDto<>(-1, "request failed", productDetailRespDto));
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().body(new CMRespDto<>(-1, "database error", productDetailRespDto));
		}
		
		
		return ResponseEntity.ok().body(new CMRespDto<>(1, "request success", productDetailRespDto));
	}
	
	
	
	
}
