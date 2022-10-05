package com.project.worksout.domain.cart;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CartRepository {

	public int saveCart(Cart cart) throws Exception;
}
