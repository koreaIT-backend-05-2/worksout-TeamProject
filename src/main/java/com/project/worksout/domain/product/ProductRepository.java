package com.project.worksout.domain.product;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProductRepository {
	public int save(Product product) throws Exception;
	public int saveProductFiles(List<ProductFile> list) throws Exception;
	public Product getProduct(Map<String, Object> map) throws Exception;
	public List<Product> getProductList(Map<String, Object> map) throws Exception;
	
	//detail
	public List<Product>  getProductDetail(Map<String, Object> map) throws Exception;
	public int updateProduct(Product product) throws Exception;
	public int deleteProduct(int product_code) throws Exception;
}
