package com.project.worksout.service.cart;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.project.worksout.domain.cart.Cart;
import com.project.worksout.domain.cart.CartRepository;
import com.project.worksout.web.dto.cart.AddCartReqDto;
import com.project.worksout.web.dto.cart.GetCartRespDto;
import com.project.worksout.web.dto.cart.UpdateCartRespDto;

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
	
	@Override
	public List<GetCartRespDto> getCartList(int userCode) throws Exception {
		
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("user_code", userCode);
	
		List<GetCartRespDto> cartList = new ArrayList<GetCartRespDto>();
		
		cartRepository.getCartList(map).forEach(cart -> {
			cartList.add(cart.toCartListDto());
		});
		
		
		
//		List<Map<String, Object>> cartFile = new ArrayList<Map<String,Object>>();
//		
//		cartLists.forEach(cartList -> {
//			Map<String, Object> fileMap = new HashMap<String, Object>();
//			
//			fileMap.put("fileCode", cartList.getFile_code());
//			fileMap.put("fileName", cartList.getFile_name());
//			
//			cartFile.add(fileMap);
//		});
//		
//		
//		log.info("cart {}", cartLists);
			

		
//			getCartListRespDto.add(GetCartRespDto.builder()
//					.userCode(getCartList.getUser_code())
//					.cartCode(getCartList.getCart_code())
//					.productFileName(getCartList.getFile_name())
//					.productName(getCartList.getProduct_name())
//					.productDetailName(getCartList.getProduct_detail_name())
//					.productSize(getCartList.getProduct_size())
//					.productPrice(getCartList.getProduct_price())
//					.build()); 
//		
//	
//		log.info("getCartList {}", getCartList);
		
		return cartList;
	}
	
	@Override
	public boolean updateCart(UpdateCartRespDto updateCartRespDto) throws Exception {
		
		return cartRepository.updateCartByCartCode(updateCartRespDto.updateCartToEntity()) > 0;
	}

	@Override
	public boolean updateCartFlag(int cartCode) throws Exception {
//		List<GetCartRespDto> cartList = new ArrayList<GetCartRespDto>();
//		Map<String, Object> map = new HashMap<String, Object>();
//		
//		map.put("user_code", userCode);
//		
//		if(cartRepository.updateCartFlag(cartCode, userCode) > 0) {
//			cartRepository.getCartList(map).forEach(cart -> {
//				cartList.add(cart.toCartListDto());
//			});
//		}
//		return cartList;
		
		return cartRepository.updateCartFlag(cartCode) > 0;
	}
	
	@Override
	public boolean updateFirstCartFlag(int userCode) throws Exception {
		
		return cartRepository.updateFirstCartFlag(userCode) > 0;
	}
	
	@Override
	public boolean updateAllCartFlag(int userCode) throws Exception {
		
		return cartRepository.updateAllCartFlag(userCode) > 0;
	}
	
	
	@Override
	public boolean removeCart(int cartCode) throws Exception {
		
		return cartRepository.removeCartByCartCode(cartCode) > 0;
	}
	
	@Override
	public boolean removeChoiceCart(int userCode) throws Exception {
		
		return cartRepository.removeChoiceCartByPayFlag(userCode) > 0;
	}
	
	
}
