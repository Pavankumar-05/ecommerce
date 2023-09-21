package com.dnb.ecommerce.utils;

import org.springframework.stereotype.Component;

import com.dnb.ecommerce.dto.Product;
import com.dnb.ecommerce.payload.request.ProductRequest;
/*
 * The RequestToEntityMapper class is responsible for mapping ProductRequest objects to Product entities.
 */
@Component
public class RequestToEntityMapper {
	
	public Product getProductEntityObject(ProductRequest productRequest) {
		Product product = new Product();
		
		product.setProductName(productRequest.getProductName());
		product.setPrice(productRequest.getPrice());
		product.setProductCategory(productRequest.getProductCategory());
		product.setProductDescription(productRequest.getProductDescription());
		product.setProductExpiryDate(productRequest.getProductExpiryDate());
		return product;
		
	}

}
