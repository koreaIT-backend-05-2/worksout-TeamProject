package com.project.worksout.service.cart;

import org.springframework.stereotype.Service;

import com.project.worksout.domain.cart.Cart;
import com.project.worksout.domain.cart.CartRepository;
import com.project.worksout.web.dto.cart.AddCartReqDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService{

	private final CartRepository  cartRepository;
	
	@Override
	public boolean addCart(AddCartReqDto addCartReqDto) throws Exception {
		Cart cartEntity = addCartReqDto.cartEntity();
		
		log.info(">>>>>>>>>>> {}", cartEntity);
		
		return cartRepository.saveCart(cartEntity) > 0;
	}
	
}
