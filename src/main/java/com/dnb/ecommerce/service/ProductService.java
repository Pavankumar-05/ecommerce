package com.dnb.ecommerce.service;

import java.util.List;
import java.util.Optional;

import com.dnb.ecommerce.dto.Product;
import com.dnb.ecommerce.exceptions.ProductIdNotFoundException;
import com.dnb.ecommerce.exceptions.UniqueProductNameExpection;


/*
 * The ProductService interface defines methods for performing CRUD operations on products in the e-commerce system.
 */
public interface ProductService {
	
	public Product createProduct(Product product) throws ProductIdNotFoundException;
	
	public List<Product> getAllProducts();
	
	public Optional<Product> getProductById(String productId)throws ProductIdNotFoundException;
		
	public boolean checkExistsById(String productId);
	
	public boolean deleteProductById(String productId)throws ProductIdNotFoundException;
	
	public Product updateProduct(String productId, Product updatedProduct) throws UniqueProductNameExpection, ProductIdNotFoundException;

	public boolean isProductNameUnique(String productName);
	
}
