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
		
		map.put("user_id", username);
		
		List<Cart> cartLists = cartRepository.getCartList(map);

		log.info("map!!! {}", map);
		
		
		List<Map<String, Object>> cartFile = new ArrayList<Map<String,Object>>();
		
		cartLists.forEach(cartList -> {
			Map<String, Object> fileMap = new HashMap<String, Object>();
			
			fileMap.put("fileCode", cartList.getFile_code());
			fileMap.put("fileName", cartList.getFile_name());
			
			cartFile.add(fileMap);
		});
		
		log.info(">> {}", cartFile);
		
		log.info("cart {}", cartLists);
			Cart getCartList = cartLists.get(0);
		
			getCartListRespDto.add(GetCartRespDto.builder()
					.cartCode(getCartList.getCart_code())
					.productImg(cartFile)
					.productName(getCartList.getProduct_name())
					.productDetailName(getCartList.getProduct_detail_name())
					.productSize(getCartList.getProduct_size())
					.productPrice(getCartList.getProduct_price())
					.build()); 
		
		
		return getCartListRespDto;
	}
	
}
