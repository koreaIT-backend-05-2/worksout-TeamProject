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
import com.project.worksout.web.dto.cart.UpdateCartReqDto;

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
	public List<GetCartRespDto> getCartList(String username) throws Exception {
		List<GetCartRespDto> getCartListRespDto = new ArrayList<GetCartRespDto>();
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("user_code", username);
		
		List<Cart> cartLists = cartRepository.getCartList(map);

		log.info("map!!! {}", map);
		
		
		
//		cartLists.forEach(cartList -> {
//			Map<String, Object> fileMap = new HashMap<String, Object>();
//			
//			fileMap.put("fileCode", cartList.getFile_code());
//			fileMap.put("fileName", cartList.getFile_name());
//			
//			cartFile.add(fileMap);
//		});
		

		
		//log.info(">> {}", cartFile);
		
		log.info("cartLists {}", cartLists);
		
		cartLists.forEach(cart -> {
			
			Map<String, Object> fileMap = new HashMap<String, Object>();
			
			fileMap.put("fileCode", cart.getFile_code());
			fileMap.put("fileName", cart.getFile_name());
			
			Cart getCartList = cart;
			
			getCartListRespDto.add(GetCartRespDto.builder()
					.cartCode(getCartList.getCart_code())
					.userCode(getCartList.getUser_code())
					.productCode(getCartList.getProduct_code())
					.productImg(fileMap)
					.productName(getCartList.getProduct_name())
					.productDetailName(getCartList.getProduct_detail_name())
					.productSize(getCartList.getProduct_size())
					.productPrice(getCartList.getProduct_price())
					.cartAmount(getCartList.getCart_amount())
					.pay_flag(getCartList.getPay_flag())
					.build()); 
			
			log.info("cart {}", cart);
		});
		System.out.println(getCartListRespDto);
		
		return getCartListRespDto;
	}
	
	public boolean updateCartAmount(UpdateCartReqDto updateCartReqDto) throws Exception {
		log.info("ServiceImpl updateCartAmount >>> " + updateCartReqDto);
		return cartRepository.updateCartAmount(updateCartReqDto.toEntity()) > 0;
	}

	@Override
	public boolean removeCart(int cartCode) throws Exception {
		
		return false;
	}
	
}
